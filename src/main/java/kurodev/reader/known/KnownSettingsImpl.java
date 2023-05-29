package kurodev.reader.known;

import kurodev.reader.SectionData;
import kurodev.reader.UnknownSettingsImpl;

import java.util.*;

public class KnownSettingsImpl extends UnknownSettingsImpl {
    private final Set<? extends Setting> settings;

    public KnownSettingsImpl(EnumSet<? extends Setting> settings) {
        this(new HashMap<>(), settings);
    }

    public KnownSettingsImpl(Map<String, SectionData> parsed, Set<? extends Setting> settings) {
        super(parsed);
        this.settings = settings;
    }

    public void init() {
        for (Setting setting : settings) {
            Optional<String> result = get(setting);
            if (result.isPresent()) {
                if (!setting.verify(result.get())) {
                    throw new ValidationException("Value for setting " + setting + "was invalid. (value: '" + result + "')");
                }
            } else {
                setting.onNotFound().accept(setting, this);
            }
        }
    }
}
