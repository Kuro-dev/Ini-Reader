package kurodev.inireader.settings.datatype;

/**
 * Contains all valid DataTypes with Regex to check if Strings match the desired data type.
 * Use String if content does not matter.
 */
public enum DataType {
    BOOLEAN("(true)|(false)"),
    INTEGER("(-\\d+|\\d+)"),
    STRING(".+");

    private final String regex;

    DataType(String regex) {
        this.regex = regex;
    }

    public boolean matches(String string) {
        return string.replaceAll("\\s","").matches(regex);
    }
}
