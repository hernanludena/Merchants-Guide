package com.hludena.converters;

/**
 * Interfaz para convertir palabras de un idioma alienígena a números romanos.
 * Esta interfaz define los métodos necesarios para agregar palabras alienígenas a un diccionario
 * y para construir números romanos a partir de palabras en idioma alienígena.
 */
public interface IAlienLanguageConverter {
    public void addAlienWordToDictionary(String[] parts);
    public String buildRomanNumberFromAlienWords(String[] alienWords); 
}