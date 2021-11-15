package kurodev.reader.known;

import org.jetbrains.annotations.NotNull;

public interface Setting {
    @NotNull
    String getSection();

    @NotNull
    String getSetting();

    /**
     * determines whether or not a default value is allowed
     * to be written if the value is missing or missing verification
     */
    @NotNull
    default AutoAction onNotFound() {
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
        return true;
    }
}
