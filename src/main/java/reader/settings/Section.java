package reader.settings;

/**
 * The base of all settings.
 * <B>All</B> settings must have a section in the Ini file.
 */
public interface Section {
    String getSection();
}
