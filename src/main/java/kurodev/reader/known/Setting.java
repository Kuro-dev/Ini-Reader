package kurodev.reader.known;

import kurodev.reader.IniInstance;

import java.util.function.BiConsumer;

public interface Setting {
    /**
     * must not return null
     */
    String getSection();

    /**
     * must not return null
     */
    String getSetting();

    default DataType getType() {
        return DataType.STRING;
    }

    /**
     * determines whether or not a default value is allowed
     * to be written if the value is missing or missing verification
     * <p>
     * must not be null
     * </p>
     *
     * @see AutoAction
     */
    default BiConsumer<Setting, IniInstance> onNotFound() {
        return AutoAction.SET_DEFAULT;
    }

    default String getDefault() {
        return null;
    }

    default boolean hasDefault() {
        return getDefault() != null;
    }

    /**
     * Verifies the value of the given setting.
     */
    default boolean verify(String settingValue) {
        return getType().matches(settingValue);
    }
}
