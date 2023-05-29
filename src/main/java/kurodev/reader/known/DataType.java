package kurodev.reader.known;

import java.util.regex.Pattern;

/**
 * Contains all valid DataTypes with Regex to check if Strings match the desired data type.
 * Use String if content does not matter.
 */
public enum DataType {
    BOOLEAN("(true)|(false)"),
    INTEGER("[+-]?\\d+"),
    FLOAT("[+-]?([0-9]*[.])?[0-9]+"),
    STRING(".+");

    private final Pattern regex;

    DataType(String regex) {
        this.regex = Pattern.compile(regex);
    }

    public boolean matches(String string) {
        return regex.matcher(string).matches();
    }

    public Pattern getRegex() {
        return regex;
    }
}
