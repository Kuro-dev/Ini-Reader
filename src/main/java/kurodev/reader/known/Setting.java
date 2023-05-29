package kurodev.reader.known;

import kurodev.reader.IniInstance;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public interface Setting {
    @NotNull
    String getSection();

    @NotNull
    String getSetting();

    default DataType getType() {
        return DataType.STRING;
    }

    /**
     * determines whether or not a default value is allowed
     * to be written if the value is missing or missing verification
     *
     * @see AutoAction
     */
    @NotNull
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
