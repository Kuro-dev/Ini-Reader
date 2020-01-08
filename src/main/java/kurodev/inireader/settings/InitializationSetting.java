package kurodev.inireader.settings;

import java.util.HashMap;

/**
 * Used to create customizable objects using ini file parameters.
 * <p>Recommended use:</p>
 * <p>Use this method like a factory method. Let it parse the settings from the HashMap
 * and create a new Object instance using those parameters.</p>
 * @author Kuro
 */
public interface InitializationSetting extends Section {
    void init(HashMap<String, String> settings);
}
