# Ini Reader
### Maven:
```xml
<dependency>
  <groupId>org.kurodev</groupId>
  <artifactId>ini-reader</artifactId>
  <version>1.1.1</version>
</dependency>
```

### Example:
This is all that's needed to parse an INI file.
```java myJavaTab
public class Example {
    public static void main(String[] args) {
        Path iniFile = Path.of('./my/path.ini');
        InputStream in = Files.newInputStream(iniFile);
        IniInstance instance = IniInstance.newInstance(in);
    }
}

```


# special feature: inheritance
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
