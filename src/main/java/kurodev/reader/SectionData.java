package kurodev.reader;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SectionData {
    private final String name;
    private final Map<String, String> settings;
    private SectionData parent;

    public SectionData(String name) {
        this(name, new HashMap<>(), null);
    }

    public SectionData(String name, SectionData parent) {
        this(name, new HashMap<>(), parent);
    }

    public SectionData(String name, Map<String, String> settings) {
        this(name, settings, null);
    }

    public SectionData(String name, Map<String, String> settings, SectionData parent) {
        this.name = name;
        this.settings = settings;
        this.parent = parent;
        if (this.equals(parent)) {
            throw new IllegalArgumentException("A section cannot be the parent of itself");
        }
    }

    void setParent(SectionData parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionData that = (SectionData) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String get(String key) {
        return get(key, null);
    }

    public String get(String key, String defaultVal) {
        String result = settings.get(key);
        if (parent != null && result == null) {
            result = parent.get(key);
        }
        return result != null ? result : defaultVal;
    }

    public int size() {
        return this.settings.size() + ((parent != null) ? parent.size() : 0);
    }

    @Override
    public String toString() {
        return "Section: " + sectionString();
    }

    private String sectionString() {
        String inherited = parent != null ? "(" + parent.name + ")" : "";
        return "[" + name + ']' + inherited;
    }

    public String stringify() {
        StringBuilder builder = new StringBuilder(this.sectionString()).append(System.lineSeparator());
        settings.forEach((key, val) -> builder.append(key).append(" = ").append(val).append(System.lineSeparator()));
        return builder.toString();
    }

    Map<String, String> getSettings() {
        return settings;
    }

    public String getName() {
        return name;
    }

    public void setValue(String setting, String value) {
        settings.put(setting, value);
    }
}
