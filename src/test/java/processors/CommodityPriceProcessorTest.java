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

public class CommodityPriceProcessorTest {

    //Simulamos Convertidores
    @Mock
    private IRomanToArabicConverter romanConverter;

    @Mock
    private IAlienLanguageConverter alienLanguageConverter;

    //Clase a Testear
    private CommodityPriceProcessor processor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        processor = new CommodityPriceProcessor(romanConverter, alienLanguageConverter);
    }

    @Test
    public void testProcessCommodityPrice() {
        // Simula el comportamiento de las dependencias/conversiones
        when(alienLanguageConverter.buildRomanNumberFromAlienWords(any(String[].class))).thenReturn("X"); //any(String[].class)) -->glob glob
        when(romanConverter.convertRomanToArabic("X")).thenReturn(10);

        // Define el precio en créditos
        String inputLine = "glob prok Gold is 1500 Credits";

        // Ejecuta el método a probar
        processor.processCommodityPrice(inputLine); //Al ejecutar crea el Diccionario dictionaryCommodityPrices

        // Verifica el precio por unidad calculado
        double expectedPricePerUnit = 150.0; // 1500 Credits / 10 (cantidad)
        assertEquals(expectedPricePerUnit, CommodityPriceProcessor.dictionaryCommodityPrices.get("Gold"), 0.01);

        // Verifica que se llamen los métodos de las dependencias
        verify(alienLanguageConverter).buildRomanNumberFromAlienWords(any(String[].class));
        verify(romanConverter).convertRomanToArabic("X");
    }
}
