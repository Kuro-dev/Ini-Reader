package inireader;

import org.junit.jupiter.api.Test;
import reader.IniReader;

import java.io.File;

public class readingTest {
    @Test
    public void readIni() {
        final File ini = new File(System.getProperty("user.dir") + "/testResources/settings.ini");
        final IniReader reader = new IniReader(ini);

    }
}
