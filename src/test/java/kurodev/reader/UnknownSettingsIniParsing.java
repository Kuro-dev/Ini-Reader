package kurodev.reader;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnknownSettingsIniParsing {
    Path iniFile = Path.of("./testResources/inherited.ini");

    @Test
    void parse() throws Exception {
        var in = Files.newInputStream(iniFile);
        var instance = IniInstance.newInstance(in);
        assertTrue(instance instanceof UnknownSettingsImpl);
        assertEquals(5, instance.getSection("iniSection").size());
    }
}