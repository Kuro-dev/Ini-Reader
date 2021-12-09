package kurodev.reader;

import kurodev.reader.known.KnownSettingsImpl;
import kurodev.reader.known.Setting;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.Map;

public interface IniInstance {
    static IniInstance createNew() {
        return new UnknownSettingsImpl();
    }

    static IniInstance createNew(EnumSet<? extends Setting> settings, InputStream in) {
        IniParser parser = new IniParser();
        var parsed = parser.parse(in);
        var out = new KnownSettingsImpl(parsed, settings);
        out.init();
        return out;
    }

    static IniInstance createNew(InputStream stream) {
        IniParser parser = new IniParser();
        var parsed = parser.parse(stream);
        return new UnknownSettingsImpl(parsed);
    }

    Map<String, SectionData> getAll();

    SectionData getSection(String section);

    default SectionData getSection(Setting setting) {
        return getSection(setting.getSection());
    }

    default String get(Setting setting) {
        return get(setting.getSection(), setting.getSetting(), setting.getDefault());
    }

    String get(String section, String setting, String defaultVal);

    /**
     * @param query the setting to be fetched. allowed string syntax: section.setting or null if none could be found
     */
    default String get(String query) {
        if (query.contains(".")) {
            int index = query.indexOf(".");
            var section = query.substring(0, index);
            var setting = query.substring(index + 1);
            return get(section, setting);
        } else {
            throw new IllegalArgumentException("query string must conform to 'section.setting' syntax");
        }
    }

    default String get(String section, String setting) {
        return get(section, setting, null);
    }

    default void set(Setting setting, String value) {
        set(setting.getSection(), setting.getSetting(), value);
    }

    void set(String section, String setting, String value);

    default void write(OutputStream out) throws IOException {
        for (Map.Entry<String, SectionData> entry : getAll().entrySet()) {
            String s = entry.getKey();
            SectionData data = entry.getValue();
            out.write(data.stringify().getBytes(StandardCharsets.UTF_8));
        }
    }
}
