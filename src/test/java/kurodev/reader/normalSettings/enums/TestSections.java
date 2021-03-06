package kurodev.reader.normalSettings.enums;

import kurodev.reader.settings.Section;

public enum TestSections implements Section {
    SECTION1("section1");

    private final String section;

    TestSections(String section) {
        this.section = section;
    }

    @Override
    public String getSection() {
        return section;
    }
}
