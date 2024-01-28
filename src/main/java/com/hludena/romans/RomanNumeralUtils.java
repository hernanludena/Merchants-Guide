package com.hludena.romans;

import java.util.HashMap;
import java.util.Map;

/**
 * Proporciona utilidades para trabajar con números romanos.
 * Incluye una representación de los numeros romanos y funciones para validar
 * cadenas como numeros romanos.
 */
public class RomanNumeralUtils {

    private static RomanNumeralUtils instance = null;
    private final Map<Character, Integer> romanNumerals;

    public RomanNumeralUtils() {
        romanNumerals = new HashMap<>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);
        romanNumerals.put('C', 100);
        romanNumerals.put('D', 500);
        romanNumerals.put('M', 1000);
    }

    public static RomanNumeralUtils getInstance() {
        if (instance == null) {
            instance = new RomanNumeralUtils();
        }
        return instance;
    }

    /**
     * Obtiene una copia del mapa del numeros romanos.
     * 
     * @return Una copia del mapa de numeros romanos.
     */
    public Map<Character, Integer> getRomanNumerals() {
        return new HashMap<>(romanNumerals);
    }

    /**
     * Verifica si el valor es un numero romano válido, utilizando el Enum
     * 
     * @param value El valor a verificar.
     * @return true si el valor es un numero romano válido, de lo contrario false.
     */
    public boolean isValidRomanNumeral(String value) {
        for (RomanNumeral numeral : RomanNumeral.values()) {
            if (numeral.getSymbol().equals(value)) {
                return true;
            }
        }
        return false;
    }

}
