package inireader.enums;

import reader.settings.Setting;
import reader.settings.datatype.DataType;

public enum TestSettings implements Setting {
    SETTING_NR1(TestSections.TestSection,DataType.STRING,"setting1","someValue");

    private final TestSections section;
    private final DataType type;
    private final String setting;
    private final String defaultValue;

    TestSettings(TestSections section, DataType type, String setting, String defaultValue) {
        this.section = section;
        this.type = type;
        this.setting = setting;
        this.defaultValue = defaultValue;
    }

    @Override
    public DataType getType() {
        return null;
    }

    @Override
    public String getSetting() {
        return null;
    }

    @Override
    public String getDefaultValue() {
        return null;
    }

    @Override
    public String getSection() {
        return null;
    }
}
