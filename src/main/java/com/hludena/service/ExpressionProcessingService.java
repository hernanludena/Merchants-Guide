package com.hludena.service;

import com.hludena.converters.AlienLanguageConverter;
import com.hludena.converters.IAlienLanguageConverter;
import com.hludena.converters.IRomanToArabicConverter;
import com.hludena.converters.RomanToArabicConverter;
import com.hludena.parsers.ExpressionParser;
import com.hludena.processors.CommodityPriceProcessor;
import com.hludena.processors.HowManyCreditsQuestionProcessor;
import com.hludena.processors.HowMuchQuestionProcessor;
import com.hludena.romans.RomanNumeralUtils;

public class ExpressionProcessingService {

    private final ExpressionParser parser;

    /**
     * Inicializa las dependencias necesarias para el procesamiento de expresiones,
     * incluyendo convertidores de lenguaje y numerales romanos, y el analizador de expresiones.
     */
    public ExpressionProcessingService() {
        IAlienLanguageConverter languageConverter = new AlienLanguageConverter();
        IRomanToArabicConverter romanToArabicConverter = new RomanToArabicConverter(RomanNumeralUtils.getInstance().getRomanNumerals());

        CommodityPriceProcessor commodityPriceProcessor = new CommodityPriceProcessor(romanToArabicConverter,languageConverter);
        HowManyCreditsQuestionProcessor howManyCreditsQuestionProcessor = new HowManyCreditsQuestionProcessor(romanToArabicConverter,languageConverter);
        HowMuchQuestionProcessor howMuchQuestionProcessor = new HowMuchQuestionProcessor(romanToArabicConverter,languageConverter);

        parser = new ExpressionParser(languageConverter, romanToArabicConverter,commodityPriceProcessor,howManyCreditsQuestionProcessor,howMuchQuestionProcessor);


    }

    public void processLine(String line) {
        parser.processLine(line);
    }

}
