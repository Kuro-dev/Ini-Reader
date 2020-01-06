package reader.settings;

import java.util.HashMap;

public interface InitializationSetting extends Section {
    void init(HashMap<Setting, String> subSettings);
}
