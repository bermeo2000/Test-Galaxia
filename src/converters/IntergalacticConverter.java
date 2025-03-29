package converters;

import java.util.HashMap;
import java.util.Map;

public class IntergalacticConverter {
    private final Map<String, String> intergalacticToRoman = new HashMap<>();

    public void addMapping(String intergalactic, String roman) {
        if (!roman.matches("[IVXLCDM]")) {
            System.out.println("Error: '" + roman + "' no es un símbolo romano válido.");
            return;
        }
        if (intergalacticToRoman.containsValue(roman)) {
            System.out.println("ya existe el romano " + roman);
            return;
        } else if (intergalacticToRoman.containsKey(intergalactic)) {
            System.out.println("ya existe la moneda intergalactic " + intergalactic);
            return;
        }

        intergalacticToRoman.put(intergalactic, roman);
        System.out.println("Asignado: " + intergalactic + " = " + roman);
    }

    public int convertToInteger(String[] words) {
        StringBuilder romanNumber = new StringBuilder();
        for (String word : words) {
            String roman = intergalacticToRoman.get(word);
            if (roman == null) {
                System.out.println("Error: No se encontró el valor para " + word);
                return -1;
            }
            romanNumber.append(roman);
        }
        System.out.println("Conversión: " + String.join(" ", words) + " → " + romanNumber);


        if (!RomanNumeralConverter.isValidRoman(romanNumber.toString())) {
            return -1;
        }

        // Si todo está bien, realizar la conversión.
        return RomanNumeralConverter.romanToInteger(romanNumber.toString());
    }
}
