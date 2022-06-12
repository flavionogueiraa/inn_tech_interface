package utils;

import java.util.Date;

public class ValueToTest {
    static Integer[] intValues = { -1, 0, 100000, 1 };
    static String[] stringValues = { "", "Exemp!o de testes c#m v@lor gran$e e c@r@ct$re@ e$p*ciais",
            "Exemplo de teste" };
    static Double[] doubleValues = { -1.0, 0.0, 100000.0, 1.0 };
    static Date[] dateValues = { new Date() };
    static Boolean[] booleanValues = { false, true };

    private static Integer positionOrLast(Object[] array, Integer position) {
        Integer lastPosition = array.length - 1;
        return position > lastPosition ? lastPosition : position;
    }

    public static Integer getIntegerValue(Integer position) {
        return intValues[positionOrLast(intValues, position)];
    }

    public static String getStringValue(Integer position) {
        return stringValues[positionOrLast(stringValues, position)];
    }

    public static Double getDoubleValue(Integer position) {
        return doubleValues[positionOrLast(doubleValues, position)];
    }

    public static Date getDateValue(Integer position) {
        return dateValues[positionOrLast(dateValues, position)];
    }

    public static Boolean getBooleanValue(Integer position) {
        return booleanValues[positionOrLast(booleanValues, position)];
    }
}
