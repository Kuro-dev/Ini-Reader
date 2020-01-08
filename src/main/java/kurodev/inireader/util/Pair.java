package kurodev.inireader.util;

public class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {

        this.key = key;
        this.value = value;
    }

    public static Pair<String, String> parsePair(String keyValuePair) {
        final String[] keyValue = keyValuePair.split("=");
        final int keyIndex = 0;
        final int valueIndex = 1;
        if (keyValue.length > 1) {
            final String whiteSpace = "\\s";
            final String key = keyValue[keyIndex].replaceAll(whiteSpace, "");
            final String value = keyValue[valueIndex].replaceAll(whiteSpace, "");
            return new Pair<>(key, value);
        }
        throw new IllegalArgumentException("Cannot parse" + keyValuePair);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
