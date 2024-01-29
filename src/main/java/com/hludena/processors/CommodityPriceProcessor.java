package com.hludena.processors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.hludena.converters.IAlienLanguageConverter;
import com.hludena.converters.IRomanToArabicConverter;
import com.hludena.service.ExpressionProcessingService;

/**
 * La clase se encarga de procesar líneas de texto
 * que contienen información sobre precios de mercancías en un lenguaje alienígena.
 */
public class CommodityPriceProcessor {

    private final IRomanToArabicConverter romanConverter;
    private final IAlienLanguageConverter alienLanguageConverter;

    //Diccionario de precios de mercancias
    public static Map<String, Double> dictionaryCommodityPrices = new HashMap<>();

    public CommodityPriceProcessor( ExpressionProcessingService expressionProcessingService) {
        this.romanConverter = expressionProcessingService.getRomanToArabicConverter();
        this.alienLanguageConverter = expressionProcessingService.getLanguageConverter();
    }

    /**
     * Procesa una línea de texto que contiene el precio de una mercancía y calcula su valor por unidad.
     *
     * @param line Línea de texto que contiene el precio de la mercancía en lenguaje alienígena y créditos.
     * @param romanConverter 
     */
    public void processCommodityPrice(String line) {
        //glob glob Silver is 34 Credits
        String[] parts = line.split(" is ");
        String[] words = parts[0].split(" "); //glob glob Silver
        String commodity = words[words.length - 1]; //Silver
        double credits = Double.parseDouble(parts[1].split(" ")[0]); //34
        String romanNumber = alienLanguageConverter.buildRomanNumberFromAlienWords(Arrays.copyOf(words, words.length - 1)); //glob glob --> II

        /**
         * glob glob Silver is 34 Credits indica que 2 globs de Plata valen 34 Créditos. 
         * Por lo tanto, 1 unidad de Plata = 34 / 2 = 17 Créditos.
         */
        int quantity = romanConverter.convertRomanToArabic(romanNumber); //Validar Reglas Numeros Romanos
        double valuePerUnit = credits / quantity;
        dictionaryCommodityPrices.put(commodity, valuePerUnit); //1 Silver = 34  //Crear Diccionario
    }
}
