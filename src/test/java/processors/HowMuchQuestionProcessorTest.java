package processors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hludena.converters.IAlienLanguageConverter;
import com.hludena.converters.IRomanToArabicConverter;
import com.hludena.processors.HowMuchQuestionProcessor;
import com.hludena.service.ExpressionProcessingService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HowMuchQuestionProcessorTest {

    @Mock
    private ExpressionProcessingService expressionProcessingService;

    @Mock
    private IRomanToArabicConverter romanConverter;

    @Mock
    private IAlienLanguageConverter alienLanguageConverter;

    private HowMuchQuestionProcessor processor;

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(expressionProcessingService.getRomanToArabicConverter()).thenReturn(romanConverter);
        when(expressionProcessingService.getLanguageConverter()).thenReturn(alienLanguageConverter);
        processor = new HowMuchQuestionProcessor(expressionProcessingService);

        // Simula la salida de System.out para capturar declaraciones impresas
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testProcessHowMuchQuestion() {
        // Configura los mocks/dependencias de los conversores
        when(alienLanguageConverter.buildRomanNumberFromAlienWords(any(String[].class))).thenReturn("X");
        when(romanConverter.convertRomanToArabic("X")).thenReturn(10);

        // Ejecuta el método a probar
        processor.processHowMuchQuestion("how much is glob prok ?");

        // Verifica la salida impresa
        String expectedOutput = "glob prok es 10"; // Incluye el salto de línea al final
        assertEquals(expectedOutput, outContent.toString().trim());

        // Verifica que se llamen los métodos de las dependencias
        verify(alienLanguageConverter).buildRomanNumberFromAlienWords(any(String[].class));
        verify(romanConverter).convertRomanToArabic("X");
    }

    @org.junit.jupiter.api.AfterEach
    public void restoreStreams() {
        // Restaura System.out después de las pruebas
        System.setOut(originalOut);
    }
}
