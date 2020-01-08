package inireader.initializableSettings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reader.IniReader;

import java.io.File;
import java.io.IOException;

public class InitializableSettingsTest {
    private static final File ini = new File(System.getProperty("user.dir") + "/testResources/settings.ini");
    private static final IniReader reader = new IniReader(ini);

    @Test
    public void testSettingIsInitialized() throws IOException {
        TestInitSetting testSetting = new TestInitSetting();
        reader.addSettings(testSetting);
        reader.read();
        Assertions.assertTrue(testSetting.getHasWorked());
    }
}
