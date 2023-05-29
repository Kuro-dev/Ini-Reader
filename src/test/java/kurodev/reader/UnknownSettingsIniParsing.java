package kurodev.reader;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UnknownSettingsIniParsing {
    /**
     * Contents of the file:
     * <p>
     * [section1]
     * setting1 = iniValue
     * [iniSection](section3)
     * hasWorked = true
     * key1 = value1
     * key2 = value2
     * [section3](section1)
     * setting2=differentValue
     * setting1=overridden
     */
    InputStream in = fetch("/inherited.ini");

    public static InputStream fetch(String name) {
        var out = UnknownSettingsIniParsing.class.getResourceAsStream(name);
        if (out != null)
            return out;
        throw new RuntimeException("Resource not found:" + name);
    }

    @Test
    void parse() {
        var instance = IniInstance.createNew(in);
        assertEquals(6, instance.getSection("iniSection").size());
        assertEquals("iniValue", instance.get("section1", "setting1").orElse(null));
        assertEquals("overridden", instance.get("section3", "setting1").orElse(null));
        assertEquals("overridden", instance.get("section3.setting1").orElse(null));
    }

    @Test
    void serializationTest() throws IOException {
        var instance = IniInstance.createNew(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        instance.write(out);
        var original = out.toString(StandardCharsets.UTF_8);

        var instance2 = IniInstance.createNew(new ByteArrayInputStream(original.getBytes(StandardCharsets.UTF_8)));
        out = new ByteArrayOutputStream();
        instance2.write(out);
        var second = out.toString(StandardCharsets.UTF_8);

        assertEquals(original.strip(), second.strip());
    }
}
