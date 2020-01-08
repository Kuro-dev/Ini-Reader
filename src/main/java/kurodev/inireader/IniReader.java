package kurodev.inireader;

import kurodev.inireader.settings.InitializationSetting;
import kurodev.inireader.settings.Setting;
import kurodev.inireader.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Kuro
 */
public class IniReader {
    final HashMap<String, HashMap<String, String>> sections = new HashMap<>();
    private final File iniFile;
    private final ArrayList<InitializationSetting> initSettings = new ArrayList<>();

    public IniReader(File iniFile) {
        this.iniFile = iniFile;
    }

    /**
     * @param settings All the initializable settings.
     * @apiNote Must be invoked <b>before</b> {@link #read()} call
     */
    public void addSettings(InitializationSetting... settings) {
        initSettings.addAll(Arrays.asList(settings));
    }

    /**
     * Used to fetch the ini setting value from the file.
     *
     * @param setting The setting to be looked for
     * @return The value from the ini file or the default value if none could be found
     */
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
     * If {@link InitializationSetting}s are used please make sure to invoke {@link #addSettings(InitializationSetting...)}
     * before invoking this method
     */
    //TODO: 08.01.2020 Clean up this mess
    public void read() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(iniFile)));
        String line;
        String section = null;
        HashMap<String, String> content = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            if (isSection(line)) {
                final boolean skipFirst = section == null;
                section = stripSection(line);
                //TODO: remove this terrible workaround
                if (!skipFirst) {
                    sections.put(section, content);
                }
                content = new HashMap<>();
            } else {
                final Pair<String, String> setting = Pair.parsePair(line);
                content.put(setting.getKey(), setting.getValue());
            }
        }
        sections.put(section, content);
        initializeAll();
    }

    private void initializeAll() {
        sections.forEach((section, map) -> {
            if (isInitializable(section)) {
                getInitSetting(section).init(map);
            }
        });
    }

    private InitializationSetting getInitSetting(String section) {
        for (InitializationSetting setting : initSettings) {
            if (setting.getSection().equalsIgnoreCase(section)) {
                return setting;
            }
        }
        throw new RuntimeException("Initializable setting could not be found");
    }

    private boolean isInitializable(String section) {
        for (InitializationSetting setting : initSettings) {
            if (setting.getSection().equalsIgnoreCase(section)) {
                return true;
            }
        }
        return false;
    }

    private String stripSection(String string) {
        return string.replaceAll("[\\[|\\]]", "");
    }

    private boolean isSection(String line) {
        return line.matches(".*\\[.+\\].*");
    }
}
