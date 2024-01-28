package com.hludena.converters;

import java.util.Map;

import com.hludena.romans.RomanNumeralRules;

/**
 * La clase convierte números romanos en números arábigos.
 */
public class RomanToArabicConverter implements IRomanToArabicConverter {
    private final Map<Character, Integer> romanNumerals;
    private final RomanNumeralRules romanNumeralRules;

    public RomanToArabicConverter(Map<Character, Integer> romanNumerals) {
        this.romanNumerals = romanNumerals;
        this.romanNumeralRules = new RomanNumeralRules();
    }

    /**
     * Convierte un número romano a su equivalente en número arábigo.
     * Utiliza reglas definidas en RomanNumeralRules para validar el formato de los números romanos.
     * 
     * @param roman Cadena representando el número romano a convertir.
     * @return El valor arábigo equivalente al número romano.
     * @throws IllegalArgumentException Si el formato del número romano es inválido.
     */
    public int convertRomanToArabic(String roman) {
        for (char ch : roman.toCharArray()) {
            if (!romanNumerals.containsKey(ch)) {
                throw new IllegalArgumentException("Invalid Roman numeral character: " + ch);
            }
        }
        
        if (!romanNumeralRules.isRepetitionValid(roman) || 
            !romanNumeralRules.isValidSubtraction(roman, romanNumerals)) {
            throw new IllegalArgumentException("Invalid Roman numeral format.");
        }

        int arabic = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char ch = roman.charAt(i);
            Integer value = romanNumerals.get(ch);
            if (value == null) {
                throw new IllegalArgumentException("Invalid Roman numeral character: " + ch);
            }
            arabic += value < prevValue ? -value : value;
            prevValue = value;
        }

        return arabic;
    }
}
