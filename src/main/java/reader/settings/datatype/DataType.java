package reader.settings.datatype;

public enum DataType {
    BOOLEAN("(true)|(false)"),
    INTEGER("(-\\d+|\\d+)"),
    STRING(".+");

    private final String regex;

    DataType(String regex) {
        this.regex = regex;
    }

    boolean matches(String string) {
        return string.matches(regex);
    }
}
