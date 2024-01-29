package processors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hludena.converters.IAlienLanguageConverter;
import com.hludena.converters.IRomanToArabicConverter;
import com.hludena.processors.CommodityPriceProcessor;
import com.hludena.service.ExpressionProcessingService;

public class CommodityPriceProcessorTest {

    //Simulamos Convertidores
    
    @Mock
    private ExpressionProcessingService expressionProcessingService;

    @Mock
    private IRomanToArabicConverter romanConverter;

    @Mock
    private IAlienLanguageConverter alienLanguageConverter;

    //Clase a Testear
    private CommodityPriceProcessor processor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(expressionProcessingService.getRomanToArabicConverter()).thenReturn(romanConverter);
        when(expressionProcessingService.getLanguageConverter()).thenReturn(alienLanguageConverter);
        processor = new CommodityPriceProcessor(expressionProcessingService);
    }

    @Test
    public void testProcessCommodityPriceII() {
        when(alienLanguageConverter.buildRomanNumberFromAlienWords(any(String[].class))).thenReturn("II");
        when(romanConverter.convertRomanToArabic("II")).thenReturn(2);

        String inputLine = "glob glob Silver is 34 Credits";
        processor.processCommodityPrice(inputLine);

        double expectedPricePerUnit = 17.0; // 34 Credits / 2
        assertEquals(expectedPricePerUnit, CommodityPriceProcessor.dictionaryCommodityPrices.get("Silver"), 0.01);

        verify(alienLanguageConverter).buildRomanNumberFromAlienWords(any(String[].class));
        verify(romanConverter).convertRomanToArabic("II");
    }

    @Test
    public void testProcessCommodityPriceX() {
        // Simula el comportamiento de las dependencias/conversiones
        when(alienLanguageConverter.buildRomanNumberFromAlienWords(any(String[].class))).thenReturn("X"); // Simula la conversión de palabras alienígenas a número romano
        when(romanConverter.convertRomanToArabic("X")).thenReturn(10); // Simula la conversión de número romano a árabe
    
        // Define el precio en créditos
        String inputLine = "glob prok Gold is 1500 Credits";
    
        // Ejecuta el método a probar
        processor.processCommodityPrice(inputLine); // Procesa la línea de entrada
    
        // Verifica el precio por unidad calculado
        double expectedPricePerUnit = 150.0; // 1500 Credits / 10 (cantidad)
        assertEquals(expectedPricePerUnit, CommodityPriceProcessor.dictionaryCommodityPrices.get("Gold"), 0.01); // Verifica que el precio calculado sea correcto
    
        // Verifica que se llamen los métodos de las dependencias
        verify(alienLanguageConverter).buildRomanNumberFromAlienWords(any(String[].class)); // Verifica la llamada a buildRomanNumberFromAlienWords
        verify(romanConverter).convertRomanToArabic("X"); // Verifica la llamada a convertRomanToArabic
    }
}
