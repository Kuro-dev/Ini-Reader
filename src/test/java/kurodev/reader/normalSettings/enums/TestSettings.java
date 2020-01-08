package kurodev.reader.normalSettings.enums;

import kurodev.reader.settings.Setting;
import kurodev.reader.settings.datatype.DataType;

public enum TestSettings implements Setting {
    SETTING_NR1(TestSections.SECTION1, DataType.STRING, "setting1", "codeValue"),
    SETTING_NR2(TestSections.SECTION1, DataType.STRING, "setting2", "codeValue");

    private final String section;
    private final DataType type;
    private final String setting;
    private final String defaultValue;

    TestSettings(TestSections section, DataType type, String setting, String defaultValue) {
        this.section = section.getSection();
        this.type = type;
        this.setting = setting;
        this.defaultValue = defaultValue;
    }

    @Override
    public DataType getType() {
        return type;
    }

    @Override
    public String getSetting() {
        return setting;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String getSection() {
        return section;
    }

    @Override
    public String toString() {
        return setting;
    }
}
