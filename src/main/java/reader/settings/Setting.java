package reader.settings;

import reader.settings.datatype.DataType;

/**
 * The Recommended way to use this Interface is to make an Enum implement it.
 */
public interface Setting {
    DataType getType();
    String getSetting();
    String getDefaultValue();
}