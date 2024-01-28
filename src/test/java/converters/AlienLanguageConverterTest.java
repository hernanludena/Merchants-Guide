package converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.hludena.converters.AlienLanguageConverter;

public class AlienLanguageConverterTest {

    private static AlienLanguageConverter converter;

    //Inicializo Dicccionario
    @BeforeAll
    public static void setUp() {
        converter = new AlienLanguageConverter();
        converter.addAlienWordToDictionary(new String[]{"glob", "I"});
        converter.addAlienWordToDictionary(new String[]{"prok", "V"});
    }

    //Convierte palabras alienigenas a romanos
    @Test
    public void testBuildRomanNumberFromSingleAlienWord() {
        assertEquals("I", converter.buildRomanNumberFromAlienWords(new String[]{"glob"}));
        assertEquals("V", converter.buildRomanNumberFromAlienWords(new String[]{"prok"}));
    }

    //Convertir multiples palabras
    @Test
    public void testBuildRomanNumberFromMultipleAlienWords() {
        assertEquals("IV", converter.buildRomanNumberFromAlienWords(new String[]{"glob", "prok"}));
    }

    //Convertir palabras desconocidas
    @Test
    public void testBuildRomanNumberFromUnknownAlienWords() {
        assertEquals("", converter.buildRomanNumberFromAlienWords(new String[]{"unknown"}));
    }


}
