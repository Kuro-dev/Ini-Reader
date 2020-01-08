package reader;

import reader.settings.InitializationSetting;
import reader.settings.Setting;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IniReader {
    final HashMap<String, HashMap<String, String>> sections = new HashMap<>();
    private final File iniFile;
    private final ArrayList<Setting> settings = new ArrayList<>();
    private final ArrayList<InitializationSetting> initSettings = new ArrayList<>();

    public IniReader(File iniFile, Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
        this.iniFile = iniFile;
    }

    public IniReader(File iniFile) {
        this.iniFile = iniFile;
    }

    public void addSettings(InitializationSetting... settings) {
        initSettings.addAll(Arrays.asList(settings));
    }

    public String getSetting(Setting setting) {
        HashMap<String, String> resultMap = sections.get(setting.getSection());
        if (resultMap == null) {
            return setting.getDefaultValue();
        }
        String result = resultMap.get(setting.getSetting());
        if (result == null || !setting.getType().matches(result)) {
            return setting.getDefaultValue();
        }
        return result;
    }

    /**
     * Must be invoked after creation to actually read the file.
     */
    public void read() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(iniFile)));
        String line;
        String section = null;
        HashMap<String, String> content = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            if (isSection(line)) {
                final boolean skipFirst = section == null;
                section = stripSection(line);
                if (!skipFirst) {
                    sections.put(section, content);
                    content = new HashMap<>();
                }
            } else {
                final String subKey = line;
                final String[] keyValue = subKey.split("=");
                final int keyIndex = 0;
                final int valueIndex = 1;
                if (keyValue.length > 1) {
                    final String whiteSpace = "\\s";
                    final String key = keyValue[keyIndex].replaceAll(whiteSpace, "");
                    final String value = keyValue[valueIndex].replaceAll(whiteSpace, "");
                    content.put(key, value);
                } else {
                    System.err.println("cant parse sub key: " + subKey + " in " + section + " section");
                }
            }
        }
        sections.put(section, content);
    }

    private String stripSection(String string) {
        return string.replaceAll("[\\[|\\]]", "");
    }

    private boolean isSection(String line) {
        return line.matches(".*\\[.+\\].*");
    }
}
