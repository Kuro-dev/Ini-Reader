package kurodev.reader;

import kurodev.reader.known.KnownSettingsImpl;
import kurodev.reader.known.Setting;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface IniInstance {
    static IniInstance createNew() {
        return new UnknownSettingsImpl();
    }

    static IniInstance createNew(InputStream in, Set<? extends Setting> settings) {
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

    static CompletableFuture<IniInstance> createAsync(InputStream stream, Set<? extends Setting> settings) {
        return new CompletableFuture<IniInstance>().completeAsync(() -> createNew(stream, settings));
    }

    static CompletableFuture<IniInstance> createAsync(InputStream stream) {
        return new CompletableFuture<IniInstance>().completeAsync(() -> createNew(stream));
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

    /**
     * @see #set(String, String, String)
     */
    default String get(String section, String setting) {
        return get(section, setting, null);
    }

    /**
     * @see #set(String, String, String)
     */
    default void set(Setting setting, String value) {
        set(setting.getSection(), setting.getSetting(), value);
    }

    /**
     * Writes/overrides a setting into this Ini instance (in memory only)
     *
     * @param section The section where this setting falls under
     * @param setting the name of the key
     * @param value   the value assigned to the key
     */
    void set(String section, String setting, String value);

    /**
     * Writes the specified ini data to the given output stream
     *
     * @param out the stream to write to.
     * @throws IOException if an I/O error occurs.
     */
    default void write(OutputStream out) throws IOException {
        for (Map.Entry<String, SectionData> entry : getAll().entrySet()) {
            String s = entry.getKey();
            SectionData data = entry.getValue();
            out.write(data.stringify().getBytes(StandardCharsets.UTF_8));
        }
    }
}
