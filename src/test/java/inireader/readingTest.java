package inireader;

import inireader.enums.TestSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reader.IniReader;

import java.io.File;
import java.io.IOException;

public class readingTest {
    @Test
    public void readIniReturnIniValue() throws IOException {
        final File ini = new File(System.getProperty("user.dir") + "/testResources/settings.ini");
        final IniReader reader = new IniReader(ini);
        reader.read();
        Assertions.assertEquals(reader.getSetting(TestSettings.SETTING_NR1),"iniValue");
    }
    @Test
    public void readIniReturnCodeValue() throws IOException {
        final File ini = new File(System.getProperty("user.dir") + "/testResources/settings.ini");
        final IniReader reader = new IniReader(ini);
        reader.read();
        Assertions.assertEquals(reader.getSetting(TestSettings.SETTING_NR2),"codeValue");
    }
}
