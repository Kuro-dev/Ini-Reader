package reader;

import java.io.File;

public class IniReader {
    private final File iniFile;

    public IniReader(File iniFile) {

        this.iniFile = iniFile;
    }

    /**
     * Must be invoked after creation to actually read the file.
     */
    public void read() {
        
    }
}
