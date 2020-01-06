package reader.settings.maps;

import reader.settings.Section;

import java.util.HashMap;

public class SettingHashMap<K extends Section,V> extends HashMap {
    public SettingHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public SettingHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public SettingHashMap() {
        super();
    }
}
