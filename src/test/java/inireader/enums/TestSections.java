package inireader.enums;

import reader.settings.Section;

public enum TestSections implements Section {
    TestSection("section1");

    private final String section;

    TestSections(String section) {
        this.section = section;
    }

    @Override
    public String getSection() {
        return section;
    }
}
