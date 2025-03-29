import converters.IntergalacticConverter;

import java.util.HashMap;
import java.util.Map;

public class TransactionProcessor {
    private final IntergalacticConverter converter = new IntergalacticConverter();
    private final Map<String, Double> metalValues = new HashMap<>();

    public void processInput(String input) {
        String[] words = input.split(" ");

        if (input.endsWith("?")) {
            processQuestion(input);
        } else if (input.contains("son") && input.contains("créditos")) {
            processMetalValue(words);
        } else if (words.length == 3 && words[1].equals("es")) {
            converter.addMapping(words[0], words[2]);
        } else {
            System.out.println("Error: Entrada inválida");
        }
    }

    private void processMetalValue(String[] words) {
        try {
            int credits = Integer.parseInt(words[words.length - 2]);
            String metal = words[words.length - 4];

            String[] intergalacticWords = new String[words.length - 4];
            System.arraycopy(words, 0, intergalacticWords, 0, words.length - 4);

            int quantity = converter.convertToInteger(intergalacticWords);
            if (quantity > 0) {
                double valuePerUnit = (double) credits / quantity;
                metalValues.put(metal, valuePerUnit);
                System.out.println("Guardado: " + metal + " = " + valuePerUnit + " créditos por unidad");
            } else {
                System.out.println("Error: No se pudo calcular el valor del metal");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: No se pudo leer los créditos");
        }
    }

    private void processQuestion(String input) {
        input = input.replace("?", "").trim();
        String[] words = input.split(" ");

        if (input.startsWith("¿cuanto cuesta")) {
            String[] intergalacticWords = extractIntergalacticWords(words, 2);
            int value = converter.convertToInteger(intergalacticWords);

            if (value != -1) {
                System.out.println(input.substring(14) + " es " + value);
            } else {
                System.out.println("Error: No se puede sumar estos valores");
            }

        }
        else if (input.toLowerCase().startsWith("¿cuántos créditos son") || input.toLowerCase().startsWith("¿cuantos créditos son")) {
            int lastIndex = input.lastIndexOf(" ");
            String metal = input.substring(lastIndex + 1).trim();

            String[] intergalacticWords = input.substring(22, lastIndex).trim().split(" ");
            int quantity = converter.convertToInteger(intergalacticWords);

            if (quantity != -1 && metalValues.containsKey(metal)) {
                double totalCredits = quantity * metalValues.get(metal);
                System.out.println(input.substring(22) + " son " + (int) totalCredits + " créditos");
            } else {
                System.out.println("Error: No se pudo calcular los créditos o el metal no está registrado.");
            }
        }
        else {
            System.out.println("No entiendo la pregunta.");
        }
    }

    private String[] extractIntergalacticWords(String[] words, int startIndex) {
        String[] intergalacticWords = new String[words.length - startIndex];
        System.arraycopy(words, startIndex, intergalacticWords, 0, words.length - startIndex);
        return intergalacticWords;
    }
}
