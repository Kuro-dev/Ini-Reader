package reader;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;

public class IniReader {
    final HashMap<String, HashMap<String, String>> sections = new HashMap<>();
    private final File iniFile;
    public IniReader(File iniFile) {

        this.iniFile = iniFile;
    }

    /**
     * Must be invoked after creation to actually read the file.
     */
    public void read() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(iniFile)));
        String line;
        while ((line = reader.readLine()) != null) {
            if (isSection(line)) {

            }
        }
    }

    private boolean isSection(String line) {
       return line.matches(".*\\[.+\\].*");
    }
}
