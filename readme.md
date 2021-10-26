# Ini Reader

what can this ini reader do? By invoking

```java myJavaTab
    InputStream in=Files.newInputStream(iniFile);
    IniInstance instance=IniInstance.newInstance(in);
```
this is all thats needed to parse an INI file.

#special feature: inheritance
by using ``[sectionName] (inheritedSectionName)``
you can inherit all the settings in the given parentsection.
This also supports overriding them.

example:
```ini
[section1]
    setting1 = iniValue
[iniSection](section3)
    hasWorked = true
    key1 = value1
    key2 = value2
[section3](section1)
    setting2=differentValue
    setting1=someOverride
```
[this](https://github.com/Kuro-dev/Ini-Reader/blob/master/src/test/java/kurodev/reader/UnknownSettingsIniParsing.java)
simple test should clear up remaining questions