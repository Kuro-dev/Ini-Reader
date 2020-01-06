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
        String result = sections.get(setting.getSection()).get(setting.getSetting());
        if (result == null) {
            result = setting.getDefaultValue();
        }
        if (!setting.getType().matches(result)) {
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
        while ((line = reader.readLine()) != null) {
            HashMap<String, String> content = new HashMap<>();
            if (isSection(line)) {
                final boolean skipFirst = section == null;
                section = line;
                if (skipFirst) {
                    sections.put(section, content);
                    content = new HashMap<>();
                }
            } else {
                final String subKey = line;
                final String[] keyValue = subKey.split("=");
                final int keyIndex = 0;
                final int valueIndex = 1;
                if (keyValue.length > 1) {
                    content.put(keyValue[keyIndex], keyValue[valueIndex]);
                } else {
                    System.err.println("cant parse sub key: " + subKey + " in " + section + " section");
                }
            }
        }
    }

    private boolean isSection(String line) {
        return line.matches(".*\\[.+\\].*");
    }
}
