package com.hludena.processors;

import com.hludena.converters.IAlienLanguageConverter;
import com.hludena.converters.IRomanToArabicConverter;
import com.hludena.service.ExpressionProcessingService;

/**
 * Se encarga de procesar y responder a preguntas sobre el valor
 * de expresiones numéricas en un idioma alienígena.
 */
public class HowMuchQuestionProcessor {

    private final IRomanToArabicConverter romanConverter;
    private final IAlienLanguageConverter alienLanguageConverter;

    public HowMuchQuestionProcessor(ExpressionProcessingService expressionProcessingService) {
        this.romanConverter = expressionProcessingService.getRomanToArabicConverter();;
        this.alienLanguageConverter = expressionProcessingService.getLanguageConverter();
    }

    /**
     *  Procesa una pregunta sobre el valor de una expresión numérica en un idioma alienígena.
     *  Estas expresiones son convertidas primero a números romanos y luego a números arábigos para su evaluación.
     * 
     * @param line Línea de texto que contiene la pregunta en el formato especificado.
     * @param romanConverter 
     */
    public void processHowMuchQuestion(String line) {
        //how much is pish tegj glob glob ?

        //Extrae los numeros alienigenas pish tegj glob glob de la linea
        String alienNumbers = line.replace("how much is ", "").replace(" ?", "").trim();

        //Construye en Numero Romano los numeros alienigenas
        String[] words = alienNumbers.split(" ");
        String romanNumber = alienLanguageConverter.buildRomanNumberFromAlienWords(words);

        //Convierte los Numeros Romanos a Numeros Arabigos e imprime todo en consola
        if (!romanNumber.isEmpty()) {
            int arabicNumber = romanConverter.convertRomanToArabic(romanNumber);
            System.out.println(alienNumbers + " es " + arabicNumber);
        } else {
            System.out.println("I have no idea what you are talking about");
        }
    }
}
