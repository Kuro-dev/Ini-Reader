package kurodev.reader;

import kurodev.reader.util.Pair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IniParser {
    private static final Pattern SECTION = Pattern.compile("(\\[.+])\\s*(\\(.+\\))?");
    private static final Pattern SECTION_MAIN = Pattern.compile("\\[(.+)]");
    private static final Pattern SECTION_INHERITANCE = Pattern.compile("\\((.+)\\)");
    private final Map<String, SectionData> map = new HashMap<>();
    private final List<Pair<SectionData, String>> missingParents = new ArrayList<>();

    public Map<String, SectionData> parse(InputStream stream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        var lines = reader.lines().collect(Collectors.toList());
        readSections(lines);
        assignMissingParents();
        map.forEach((s, sectionData) -> sectionData.init());

        return map;
    }

    private void readSections(List<String> lines) {
        int lastSectionFound = -1;
        SectionData lastSection = null;
        for (int i = 0; i < lines.size(); i++) {
            if (SECTION.matcher(lines.get(i).trim()).matches()) {
                lastSection = onSectionFound(lines, i, lastSectionFound, lastSection);
                lastSectionFound = i;
                map.put(lastSection.getName(), lastSection);
            }
        }
        assert lastSection != null;
        map.put(lastSection.getName(), lastSection);
        for (int i = lastSectionFound + 1; i < lines.size(); i++) {
            parseSetting(lastSection, lines.get(i));
        }
    }

    private void assignMissingParents() {
        //assign missing parents to their children
        for (int i = 0, missingParentsMapSize = missingParents.size(); i < missingParentsMapSize; i++) {
            Pair<SectionData, String> missing = missingParents.get(i);
            SectionData parent = map.get(missing.getValue());
            if (parent == null) {
                throw new RuntimeException("parent \"" + missing.getValue() + "\" does not exist");
            }
            missing.getKey().setParent(parent);
            missingParents.remove(i);
        }
    }

    private SectionData onSectionFound(List<String> lines, int index, int lastSectionIndex, SectionData lastSection) {
        String line = lines.get(index).replaceAll("\\s{2,}", " ");
        var main = SECTION_MAIN.matcher(line);
        var parent = SECTION_INHERITANCE.matcher(line);
        if (main.find()) {
            var group = main.group().replaceAll("[\\[\\]]", "");
            SectionData section = new SectionData(group);
            if (parent.find()) {
                String parentString = parent.group().replaceAll("[()]", "");
                SectionData parentSection = map.get(parentString);
                if (parentSection != null) {
                    section.setParent(parentSection);
                } else {
                    missingParents.add(new Pair<>(section, parentString));
                }
            }
            if (lastSectionIndex == -1) return section;
            for (int i = lastSectionIndex + 1; i < index; i++) {
                parseSetting(lastSection, lines.get(i));
            }
            return section;
        }
        throw new RuntimeException("Could not parse line \"" + line + "\" in line " + index);
    }

    private void parseSetting(SectionData section, String keyValuePair) {
        final String[] keyValue = keyValuePair.split("=");
        final int keyIndex = 0;
        final int valueIndex = 1;
        if (keyValue.length > 1) {
            final String key = keyValue[keyIndex].trim();
            final String value = keyValue[valueIndex].trim();
            section.getSettings().put(key, value);
        } else
            throw new IllegalArgumentException("Cannot parse: " + keyValuePair);
    }
}
