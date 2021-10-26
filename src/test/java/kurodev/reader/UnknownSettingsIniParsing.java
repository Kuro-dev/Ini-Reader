package kurodev.reader;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
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
class UnknownSettingsIniParsing {
    Path iniFile = Path.of("./testResources/inherited.ini");

    @Test
    void parse() throws Exception {
        var in = Files.newInputStream(iniFile);
        var instance = IniInstance.newInstance(in);
        assertTrue(instance instanceof UnknownSettingsImpl);
        assertEquals(6, instance.getSection("iniSection").size());
        assertEquals("iniValue", instance.getSetting("setting1", "setting1"));
        assertEquals("overridden", instance.getSetting("section3", "setting1"));
    }
}