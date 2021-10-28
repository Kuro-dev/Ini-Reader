package kurodev.reader.settings;

import kurodev.reader.IniInstance;
import kurodev.reader.SectionData;

import java.util.EnumSet;
import java.util.Map;

public class KnownSettingsImpl implements IniInstance {

    KnownSettingsImpl(EnumSet<? extends Setting> settings) {

    }

    @Override
    public Map<String, SectionData> getAll() {
        return null;
    }

    @Override
    public SectionData getSection(String section) {
        return null;
    }

    @Override
    public String getSetting(String section, String setting, String defaultVal) {
        return null;
    }
}
