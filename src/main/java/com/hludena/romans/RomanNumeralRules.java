package com.hludena.romans;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Proporciona métodos estáticos para validar las reglas de números romanos,
 * incluyendo la repetición de símbolos y las reglas de sustracción.
 */
public class RomanNumeralRules {

    private final List<Character> NON_REPEATING_SYMBOLS;

    public RomanNumeralRules() {
        this.NON_REPEATING_SYMBOLS = Arrays.asList('D', 'L', 'V');
    }

    /**
     * Verifica si la repetición de símbolos en un número romano es válida.
     * 
     * @param roman Cadena representando el número romano a validar.
     * @return true si la repetición de símbolos es válida, de lo contrario false.
     */
    public boolean isRepetitionValid(String roman) {
        int repeatCount = 0;
        char lastChar = ' ';

        for (int i = 0; i < roman.length(); i++) {
            char currentChar = roman.charAt(i);

            if (currentChar == lastChar) {
                repeatCount++;
                if (repeatCount > 2 || NON_REPEATING_SYMBOLS.contains(currentChar)) {
                    return false;
                }
            } else {
                repeatCount = 0;
                lastChar = currentChar;
            }
        }
        return true;
    }

    /**
     * Verifica si las sustracciones en un número romano son válidas según las reglas de numerales romanos.
     * 
     * @param roman Cadena representando el número romano a validar.
     * @param romanNumerals Mapa de numerales romanos a sus valores numéricos.
     * @return true si las sustracciones son válidas, de lo contrario false.
     */
    public boolean isValidSubtraction(String roman, Map<Character, Integer> romanNumerals) {
    if (roman.length() < 2) {
        return true; // No hay suficientes caracteres para una sustracción.
    }

    for (int i = 0; i < roman.length() - 1; i++) {
        char currentChar = roman.charAt(i);
        char nextChar = roman.charAt(i + 1);

        if (romanNumerals.get(currentChar) < romanNumerals.get(nextChar)) {
            if (!isSubtractionAllowed(currentChar, nextChar)) {
                return false;
            }
        }
    }
    return true;
}

    
    /**
     * Determina si la sustracción entre dos símbolos romanos es permitida.
     * 
     * @param subtracted Símbolo que se sustrae.
     * @param subtractFrom Símbolo del cual se sustrae.
     * @return true si la sustracción es permitida, de lo contrario false.
     */
    private boolean isSubtractionAllowed(char subtracted, char subtractFrom) {
        return (subtracted == 'I' && (subtractFrom == 'V' || subtractFrom == 'X')) ||
               (subtracted == 'X' && (subtractFrom == 'L' || subtractFrom == 'C')) ||
               (subtracted == 'C' && (subtractFrom == 'D' || subtractFrom == 'M'));
    }
}
