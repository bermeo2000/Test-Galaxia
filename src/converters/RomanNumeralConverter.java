package converters;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeralConverter {
    private static final Map<Character, Integer> ROMAN_VALUES = new HashMap<>();
    static {
        ROMAN_VALUES.put('I', 1);
        ROMAN_VALUES.put('V', 5);
        ROMAN_VALUES.put('X', 10);
        ROMAN_VALUES.put('L', 50);
        ROMAN_VALUES.put('C', 100);
        ROMAN_VALUES.put('D', 500);
        ROMAN_VALUES.put('M', 1000);
    }

    public static boolean isValidRoman(String roman) {
        if (roman == null || roman.isEmpty()) return false;

        if (roman.matches(".*(IIII|XXXX|CCCC|MMMM).*")) return false;

        if (roman.matches(".*(VV|LL|DD).*")) return false;

        if (roman.matches(".*(IL|IC|ID|IM|VX|VL|VC|VD|VM|XD|XM|LC|LD|LM|DM).*")) return false;

        return true;
    }

    public static int romanToInteger(String roman) {
        if (!isValidRoman(roman)) {
            System.out.println("Error: No se puede sumar esto valores" + roman);
        }

        int total = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int currentValue = ROMAN_VALUES.get(roman.charAt(i));

            if (currentValue < prevValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }

            prevValue = currentValue;
        }

        return total;
    }

}
