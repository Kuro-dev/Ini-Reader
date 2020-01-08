package kurodev.reader.normalSettings;

import kurodev.reader.IniReader;
import kurodev.reader.normalSettings.enums.TestSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class TestNormalSettingsFetching {
    private static final File ini = new File(System.getProperty("user.dir") + "/testResources/settings.ini");
    private static final IniReader reader = new IniReader(ini);

    @Test()
    public void throwExceptionFromBadIniFile() {
        final File ini = new File(System.getProperty("user.dir") + "/testResources/badSettings.ini");
        try {
            new IniReader(ini).read();
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void readIniReturnIniValue() throws IOException {
        reader.read();
        Assertions.assertEquals(reader.getSetting(TestSettings.SETTING_NR1), "iniValue");
    }

    @Test
    public void readIniReturnCodeValue() throws IOException {
        reader.read();
        Assertions.assertEquals(reader.getSetting(TestSettings.SETTING_NR2), "codeValue");
    }
}
