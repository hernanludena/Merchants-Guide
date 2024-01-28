package com.hludena.processors;

import java.util.Arrays;

import com.hludena.converters.IAlienLanguageConverter;
import com.hludena.converters.IRomanToArabicConverter;

/**
 * Se encarga de procesar y responder preguntas
 * sobre el valor total en créditos de una cantidad de mercancía, expresada en un idioma alienígena.
 */
public class HowManyCreditsQuestionProcessor{

    private final IRomanToArabicConverter romanConverter;
    private final IAlienLanguageConverter alienLanguageConverter;

    public HowManyCreditsQuestionProcessor(IRomanToArabicConverter romanConverter, IAlienLanguageConverter alienLanguageConverter) {
        this.romanConverter = romanConverter;
        this.alienLanguageConverter = alienLanguageConverter;
    }

    /**
     * Procesa una pregunta sobre el valor total en créditos de una mercancía.
     * Convierte las palabras alienígenas a números romanos y luego a números arábigos para calcular el total.
     * 
     * @param line Línea de texto que contiene la pregunta en el formato especificado.
     * @param romanConverter 
     */
    public void processHowManyCreditsQuestion(String line) {
        //how many Credits is glob prok Silver ?

       //Extrae los numeros alienigenas y mercancia ( glob prok Silver) de la linea
        String query = line.replace("how many Credits is ", "").replace(" ?", "").trim();
        String[] words = query.split(" ");
        String commodity = words[words.length - 1]; //Extrea la mercancia Silver

        //Construye en Numero Romano los numeros alienigenas
        String romanNumber = alienLanguageConverter.buildRomanNumberFromAlienWords(Arrays.copyOf(words, words.length - 1));

         //Convierte los Numeros Romanos a Numeros Arabigos e imprime todo en consola
        if (!romanNumber.isEmpty() && CommodityPriceProcessor.dictionaryCommodityPrices.containsKey(commodity)) {
            int quantity = romanConverter.convertRomanToArabic(romanNumber);
            double totalCredits = quantity * CommodityPriceProcessor.dictionaryCommodityPrices.get(commodity);
            System.out.println(query + " es " + (int) totalCredits + " Credits");
        } else {
            System.out.println("I have no idea what you are talking about");
        }
    }
}
