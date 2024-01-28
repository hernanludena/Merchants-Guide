package com.hludena.parsers;

import com.hludena.converters.IAlienLanguageConverter;
import com.hludena.converters.IRomanToArabicConverter;
import com.hludena.processors.CommodityPriceProcessor;
import com.hludena.processors.HowManyCreditsQuestionProcessor;
import com.hludena.processors.HowMuchQuestionProcessor;
import com.hludena.romans.RomanNumeralUtils;


/**
 * Se encarga de analizar y procesar diferentes tipos de expresiones o consultas.
 * Dependiendo del formato de la línea de entrada, delega la tarea a diferentes procesadores o conversores.
 */
public class ExpressionParser {

    public static final String HOW_MUCH_IS = "how much is ";
    public static final String HOW_MANY_CREDITS_IS = "how many Credits is ";
    public static final String IS = " is ";
    public static final String CREDITS = " Credits";
    public static final String NO_IDEA = "I have no idea what you are talking about";

    private final IAlienLanguageConverter languageConverter;
    private final CommodityPriceProcessor commodityPriceProcessor;
    private final HowMuchQuestionProcessor howMuchQuestionProcessor;
    private final HowManyCreditsQuestionProcessor howManyCreditsQuestionProcessor;
   
    private boolean processed = false;
    

    public ExpressionParser(IAlienLanguageConverter languageConverter,
                            IRomanToArabicConverter romanConverter, 
                            CommodityPriceProcessor commodityPriceProcessor, 
                            HowManyCreditsQuestionProcessor howManyCreditsQuestionProcessor,
                             HowMuchQuestionProcessor howMuchQuestionProcessor) {
        this.languageConverter = languageConverter;
        this.commodityPriceProcessor = commodityPriceProcessor;
        this.howManyCreditsQuestionProcessor = howManyCreditsQuestionProcessor;
        this.howMuchQuestionProcessor = howMuchQuestionProcessor;

    }

    /**
     * Procesa una línea de entrada y toma la acción correspondiente según el tipo de expresión.
     *
     * @param line La línea de entrada a procesar.
     */
    public void processLine(String line) {
        String[] parts = line.split(IS);

        //Procesamiento de Diccionarios
        processed = handleDictionaries(parts,line);

        //Procesamiento de Preguntas
        if(!processed){
            handleQuestions(line);
        }

      
    }

    public boolean handleDictionaries(String[] parts, String line){
        boolean processed = false;
        // Crea un Diccionario(Map) de palabras de idioma alienígena (key) a números romanos (value) (glob,I)
        // Y Construye un número romano a partir de un arreglo de palabras alienígenas
        if (parts.length == 2 && RomanNumeralUtils.getInstance().isValidRomanNumeral(parts[1].trim()) ) {
            languageConverter.addAlienWordToDictionary(parts);
            processed = true;
        } 
        // Convierte los precios de mercancías y los guarda en un diccionario(Map)de precios de mercancías (Silver,3334)
        else if (line.contains(CREDITS) && !line.startsWith(HOW_MANY_CREDITS_IS)) {
            commodityPriceProcessor.processCommodityPrice(line);
            processed = true;
        } 

        return processed;
    }


    private void handleQuestions(String line) {
         // Procesar preguntas sobre valores numéricos en idioma alienígena.
         if (line.startsWith(HOW_MUCH_IS)) {
            howMuchQuestionProcessor.processHowMuchQuestion(line);
            processed = true;
        } 
        // Procesar preguntas sobre el valor total en créditos de una mercancía.
        else if (line.startsWith(HOW_MANY_CREDITS_IS)) {
            howManyCreditsQuestionProcessor.processHowManyCreditsQuestion(line); 
            processed = true;
        } 
        // Manejar entradas desconocidas o no válidas.
        else if(!processed){
            System.out.println(NO_IDEA);
        }
    }


 



    
}
