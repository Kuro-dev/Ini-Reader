package datatype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import kurodev.inireader.settings.datatype.DataType;

public class DataTypeTest {

    @Test
    public void testRegexForIntegerType() {
        final String line = "25 ";
        Assertions.assertTrue(DataType.INTEGER.matches(line));
    }

    @Test
    public void testNotMatchingIntegerType() {
        final String line = "not a number";
        Assertions.assertFalse(DataType.INTEGER.matches(line));
    }

    @Test
    public void testMatchingBooleanType() {
        final String line = "true";
        Assertions.assertTrue(DataType.BOOLEAN.matches(line));
    }

    @Test
    public void testNotMatchingBooleanType() {
        final String line = "not a boolean";
        Assertions.assertFalse(DataType.BOOLEAN.matches(line));
    }
}
