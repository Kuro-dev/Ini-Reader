package kurodev.reader.initializableSettings;

import kurodev.reader.IniReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author Kuro
 */
public class InitializableSettingsTest {
    private static final File ini = new File(System.getProperty("user.dir") + "/testResources/settings.ini");
    private static final IniReader reader = new IniReader(ini);

    @Test
    public void testSettingIsInitialized() throws IOException {
        InitiliazableSettingTestClass testSetting = new InitiliazableSettingTestClass();
        reader.addSettings(testSetting);
        reader.read();
        Assertions.assertTrue(testSetting.getHasWorked());
    }
}
