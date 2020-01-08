package inireader.initializableSettings;

import reader.settings.InitializationSetting;

import java.util.HashMap;

public class TestInitSetting implements InitializationSetting {
    private boolean hasWorked = false;

    @Override
    public void init(HashMap<String, String> settings) {
        hasWorked = Boolean.parseBoolean(settings.get("hasWorked"));
    }

    public boolean getHasWorked() {
        return hasWorked;
    }

    @Override
    public String getSection() {
        return "initSection";
    }
}
