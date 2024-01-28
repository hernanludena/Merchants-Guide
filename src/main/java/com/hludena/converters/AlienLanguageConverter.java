package com.hludena.converters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.hludena.romans.RomanNumeralUtils;

/**
 * Permite la conversión de palabras en un idioma alienígena a números romanos. Ejemplo: (glob is I)
 */
public class AlienLanguageConverter implements IAlienLanguageConverter{

    //Es un mapa (diccionario) que asocia palabras en idioma alienígena (key) con sus correspondientes números romanos (value).
    private final Map<String, String> dictionary;
    private final RomanNumeralUtils romanNumeralUtils;

    public AlienLanguageConverter() {
        this.dictionary = new HashMap<>();
        this.romanNumeralUtils = new RomanNumeralUtils();
    }

    public void addAlienWordToDictionary(String[] parts) {
        String alienWord = parts[0].trim();
        String romanNumeral= parts[1].trim();

        if(romanNumeralUtils.isValidRomanNumeral(parts[1].trim())){
            dictionary.put(alienWord, romanNumeral);
        }
       
    }

    /**
     * Construye un número romano a partir de un arreglo de palabras alienígenas.
     * 
     * @param alienWords Arreglo de palabras en idioma alienígena.
     * @return Una cadena que representa el número romano correspondiente.
     */
    public String buildRomanNumberFromAlienWords(String[] alienWords) { //glob glob
        //Recorro el arreglo, obtengo el valor y hago merge de los valores en una sola cadena
        return Arrays.stream(alienWords)
                     .map(alienWord -> dictionary.getOrDefault(alienWord, ""))
                     .collect(Collectors.joining()); //II
    }
    
}
