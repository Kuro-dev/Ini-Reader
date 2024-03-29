package kurodev.reader;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Settings are not known at runtime
 */
public class UnknownSettingsImpl implements IniInstance {
    protected final Map<String, SectionData> iniMap;

    public UnknownSettingsImpl() {
        iniMap = new HashMap<>();
    }

    public UnknownSettingsImpl(Map<String, SectionData> parsed) {
        iniMap = parsed;
    }

    @Override
    public Map<String, SectionData> getAll() {
        return iniMap;
    }

    @Override
    public SectionData getSection(String section) {
        return iniMap.get(section);
    }

    @Override
    public Optional<String> get(String section, String setting, String defaultVal) {
        var sectionData = iniMap.get(section);
        if (sectionData != null) {
            String data = sectionData.get(setting, defaultVal);
            if (sectionData.isPointer(section, setting)) {
                data = data.replace("%", "").trim();
                if (data.contains(".")) {
                    return get(data);
                }
                return get(section, data);
            } else {
                return Optional.ofNullable(data);
            }

        }
        return Optional.ofNullable(defaultVal);
    }

    @Override
    public void set(String section, String setting, String value) {
        var sectionData = iniMap.get(section);
        if (sectionData == null) {
            sectionData = new SectionData(section);
            iniMap.put(section, sectionData);
        }
        sectionData.setValue(setting, value);
    }
}
