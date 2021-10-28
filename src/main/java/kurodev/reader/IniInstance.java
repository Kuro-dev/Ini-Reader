package kurodev.reader;

import kurodev.reader.settings.KnownSettingsImpl;
import kurodev.reader.settings.Section;
import kurodev.reader.settings.Setting;

import java.io.InputStream;
import java.util.EnumSet;
import java.util.Map;

public interface IniInstance {
    static IniInstance newInstance() {
        return new UnknownSettingsImpl();
    }

    static IniInstance newInstance(InputStream stream) {
        IniParser parser = new IniParser();
        var parsed = parser.parse(stream);
        return new UnknownSettingsImpl(parsed);
    }

    static IniInstance newInstance(InputStream stream, EnumSet<? extends Setting> settings) {
        return new KnownSettingsImpl(settings);
    }

    Map<String, SectionData> getAll();

    SectionData getSection(String section);

    default SectionData getSection(Section section) {
        return getSection(section.getSection());
    }

    String getSetting(String section, String setting, String defaultVal);

    default String getSetting(String section, String setting) {
        return getSetting(section, setting, null);
    }

    default String getSetting(Setting setting) {
        return getSetting(setting.getSection(), setting.getSetting(), setting.getDefaultValue());
    }

}
