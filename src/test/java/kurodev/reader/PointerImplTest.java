package kurodev.reader;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointerImplTest {
    public static InputStream fetch(String name) {
        var out = UnknownSettingsIniParsing.class.getResourceAsStream(name);
        if (out != null)
            return out;
        throw new RuntimeException("Resource not found:" + name);
    }

    /**
     * [section1]
     * test = mein test
     * pointer = %test%
     * [section2]
     * pointer = %section1.test%
     * [section3]
     * pointer2pointer = %section2.pointer%
     */
    @Test
    public void testPointerDetection() {
        var in = fetch("/pointer.ini");
        var ini = IniInstance.createNew(in);
        assertEquals(ini.get("section1.test"), ini.get("section2.pointer"));
        assertEquals(ini.get("section1.test"), ini.get("section1.pointer"));
        assertEquals(ini.get("section1.test"), ini.get("section3.pointer2pointer"));

        //change value of test
        ini.set("section1", "test", "some other string");
        assertEquals(ini.get("section1.test").orElse(null), "some other string");
        //pointers should still resolve the same value
        assertEquals(ini.get("section1.test"), ini.get("section1.pointer"));
        assertEquals(ini.get("section1.test"), ini.get("section1.pointer"));
        assertEquals(ini.get("section1.test"), ini.get("section3.pointer2pointer"));
    }
}
