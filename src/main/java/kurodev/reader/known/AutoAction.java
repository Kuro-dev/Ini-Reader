package kurodev.reader.known;

import kurodev.reader.IniInstance;

import java.util.function.BiConsumer;

public enum AutoAction implements BiConsumer<Setting, IniInstance> {
    /**
     * Will attempt to set a default value for the given setting.
     * if none exists, will throw an exception instead.
     */
    SET_DEFAULT {
        @Override
        public void accept(Setting setting, IniInstance iniInstance) {
            if (setting.hasDefault())
                iniInstance.set(setting, setting.getDefault());
            else
                THROW_EX.accept(setting, iniInstance);
        }
    },
    THROW_EX {
        @Override
        public void accept(Setting setting, IniInstance iniInstance) {
            throw new ValidationException("section " + setting.getSection() + " does not contain " + setting.getSetting());
        }
    },
    WARN {
        @Override
        public void accept(Setting setting, IniInstance iniInstance) {
            System.err.println("section " + setting.getSection() + " does not contain " + setting.getSetting());
        }
    },
}
