package reader.settings;

import java.util.HashMap;

public interface InitializableSetting extends Section {
    void init(HashMap<Setting, String> subSettings);
}
