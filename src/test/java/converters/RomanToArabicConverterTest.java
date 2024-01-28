package converters;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.hludena.converters.RomanToArabicConverter;

import java.util.HashMap;
import java.util.Map;

public class RomanToArabicConverterTest {

    private RomanToArabicConverter converter;

    //Crear Diccionario
    @BeforeEach
    public void setUp() {
        Map<Character, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);
        romanNumerals.put('C', 100);
        romanNumerals.put('D', 500);
        romanNumerals.put('M', 1000);
        converter = new RomanToArabicConverter(romanNumerals);
    }

    //Numeros validos
    @Test
    public void testConvertValidRomanNumerals() {
        assertEquals(1, converter.convertRomanToArabic("I"));
        assertEquals(5, converter.convertRomanToArabic("V"));
        assertEquals(10, converter.convertRomanToArabic("X"));
        assertEquals(50, converter.convertRomanToArabic("L"));
        assertEquals(100, converter.convertRomanToArabic("C"));
        assertEquals(500, converter.convertRomanToArabic("D"));
        assertEquals(1000, converter.convertRomanToArabic("M"));
        assertEquals(2, converter.convertRomanToArabic("II"));
        assertEquals(4, converter.convertRomanToArabic("IV"));
        assertEquals(9, converter.convertRomanToArabic("IX"));
        assertEquals(40, converter.convertRomanToArabic("XL"));
        assertEquals(90, converter.convertRomanToArabic("XC"));
        assertEquals(400, converter.convertRomanToArabic("CD"));
        assertEquals(900, converter.convertRomanToArabic("CM"));
        assertEquals(1066, converter.convertRomanToArabic("MLXVI"));
        assertEquals(1989, converter.convertRomanToArabic("MCMLXXXIX"));
    }

    //Numeros invalidos
    @Test
    public void testConvertInvalidRomanNumerals() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabic("IIII"));
        assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabic("VV"));
        assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabic("LL"));
        assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabic("DD"));
        assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabic("MMMM"));
        assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabic("IC"));
        assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabic("IL"));
        assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabic("IM"));
        assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabic("XM"));
        assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabic("XD"));
        assertThrows(IllegalArgumentException.class, () -> converter.convertRomanToArabic("NonRoman"));
    }
}
