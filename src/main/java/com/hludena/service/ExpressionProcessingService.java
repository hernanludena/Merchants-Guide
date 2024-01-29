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
    private IAlienLanguageConverter languageConverter;
    private IRomanToArabicConverter romanToArabicConverter;
    private CommodityPriceProcessor commodityPriceProcessor;
    private HowManyCreditsQuestionProcessor howManyCreditsQuestionProcessor;
    private HowMuchQuestionProcessor howMuchQuestionProcessor;


    /**
     * Inicializa las dependencias necesarias para el procesamiento de expresiones,
     * incluyendo convertidores de lenguaje y numerales romanos, y el analizador de expresiones.
     */
    public ExpressionProcessingService() {
        languageConverter = new AlienLanguageConverter();
        romanToArabicConverter = new RomanToArabicConverter(RomanNumeralUtils.getInstance().getRomanNumerals());

        commodityPriceProcessor = new CommodityPriceProcessor(this);
        howManyCreditsQuestionProcessor = new HowManyCreditsQuestionProcessor(this);
        howMuchQuestionProcessor = new HowMuchQuestionProcessor(this);

        parser = new ExpressionParser(this);

    }

    public IAlienLanguageConverter getLanguageConverter() {
        return languageConverter;
    }

    public ExpressionParser getParser() {
        return parser;
    }

    public IRomanToArabicConverter getRomanToArabicConverter() {
        return romanToArabicConverter;
    }

    public CommodityPriceProcessor getCommodityPriceProcessor() {
        return commodityPriceProcessor;
    }

    public HowManyCreditsQuestionProcessor getHowManyCreditsQuestionProcessor() {
        return howManyCreditsQuestionProcessor;
    }

    public HowMuchQuestionProcessor getHowMuchQuestionProcessor() {
        return howMuchQuestionProcessor;
    }

    public void processLine(String line) {
        parser.processLine(line);
    }

}
