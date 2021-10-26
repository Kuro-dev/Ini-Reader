package kurodev.reader;

import java.util.HashMap;
import java.util.Map;

public class UnknownSettingsImpl implements IniInstance {
    protected final Map<String, SectionData> iniMap;

    public UnknownSettingsImpl() {
        iniMap = new HashMap<>();
    }

    public UnknownSettingsImpl(Map<String, SectionData> parsed) {
    iniMap=parsed;
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
    public String getSetting(String section, String setting, String defaultVal) {
        return iniMap.get(section).get(section,defaultVal);
    }
}
