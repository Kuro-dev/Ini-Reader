package IniReading;

import reader.settings.Setting;
import reader.settings.datatype.DataType;

import static reader.settings.datatype.DataType.*;

public enum FixedIniEntries implements Setting {
    WINDOW_TITLE("windowTitle", "Ausbildungsnachweis"),
    CSS_STYLE_SHEET("StyleSheet", "darkMode.css"),
    WORK_TIME_SPLITTER("workTimeSplitter", "\"/\""),
    DAILY_GEN_AUTO_FILL_CHECKBOX_TICK_STARTUP("dailyAutoFill_General", false),
    DAILY_DAY_AUTO_FILL_CHECKBOX_TICK_STARTUP("dailyAutoFill_Weekdays", false),
    VACATION_TEXT("VacationText", "Urlaub"),
    SICK_TEXT("sickDayText", "Krank"),
    COMPANY_NAME("companyName", "QUERY.Please_Enter_Your_Companies_Name"),
    COMPANY_DEPARTMENT("department", "QUERY.PLEASE_ENTER_YOUR_DEPARTMENT"),
    TRAINEE_NAME("traineeName", "QUERY.Please_Enter_Your_Name"),
    EDUCATION_YEAR("educationYear", "QUERYNumeral.What_Year_Are_You_In_Your_Apprenticeship?"),
    TOTAL_WEEK_HOURS("totalWeekHours", "QUERY.Please_Enter_Your_Weekhours"),
    AUTO_LOAD("autoLoadSaveThisWeek", true),
    ENABLE_LOGGING("enableLogging", true),
    LOG_DELETE_TIME("deleteLogTimeInDays", 30),
    MIN_LOG_SIZE("minLogSize", 3),
    SAVE_ON_CLOSE("saveOnClose", true),
    OVERWRITE_EXISTING("overwriteExistingFiles", false),
    SAVE_FILE_PATH("saveFilePath", "/saves"),
    CSS_STYLE_PATH("cssFilePath", "/styles");

    private final DataType type;
    private final String setting;
    private final String defaultValue;

    FixedIniEntries(DataType type, String setting, String defaultValue) {
        this.type = type;
        this.setting = setting;
        this.defaultValue = defaultValue;
    }

    FixedIniEntries(String setting, boolean defaultValue) {
        this(BOOLEAN, setting, String.valueOf(defaultValue));
    }

    FixedIniEntries(String setting, String defaultValue) {
        this(STRING, setting, String.valueOf(defaultValue));
    }

    FixedIniEntries(String setting, int defaultValue) {
        this(INTEGER, setting, String.valueOf(defaultValue));
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

}
