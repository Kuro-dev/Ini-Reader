package kurodev.reader.settings;

import kurodev.reader.settings.datatype.DataType;

/**
 * Standard Settings for use in an Ini file.
 * The Recommended way to use this Interface is to make an Enum implement it.
 *
 * @author Kuro
 */
public interface Setting extends Section {
    /**
     * @return The data type of the setting.
     * This can be used to check the corresponding Regex and find out whether or not the Ini file has an actually matching value
     * @see DataType
     */
    DataType getType();

    String getSetting();

    /**
     * @return The DefaultValue of the corresponding setting. Make this return <code>null</code>
     * if no default is wanted/needed
     */
    String getDefaultValue();
}
