package reader.settings;

import reader.settings.datatype.DataType;

/**
 * Standard Settings for use in an Ini file.
 * The Recommended way to use this Interface is to make an Enum implement it.
 */
public interface Setting extends Section {
    DataType getType();

    String getSetting();

    String getDefaultValue();
}
