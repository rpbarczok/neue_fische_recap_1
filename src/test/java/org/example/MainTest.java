package org.example;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    String pwGood = "sR12hcH:op";
    String pwGoodEscape = "sR12hcH\\op";
    String pwShort = "aR5%";
    String pwNoNum = "dtSRf&jlFK";
    String pwNoCap = "dtsr4fk&lfk";
    String pwNoLow = "DTRS4FKJ&SD";
    String pwNoSpecial = "sR12hcHdop";
    String pwCommon = "P@ssw0rd";
    String lineSeparator= System.lineSeparator();
    String specialChar = "!\"#$%&'*+,./:;=?@\\^`|~";

    @Test
    void valLen_shouldReturnTrue_WhenInputGE8() {
        boolean result = Main.valLen(pwGood);
        assertTrue(result);
    }

    @Test
    void valLen_shouldReturnFalse_WhenInputLT8() {
        boolean result = Main.valLen(pwShort);
        assertFalse(result);
    }

    @Test
    void valNum_shouldReturnTrue_WhenInputHasNumber() {
        boolean result = Main.valNum(pwGood);
        assertTrue(result);
    }

    @Test
    void valNum_shouldReturnFalse_WhenInputHasNoNum() {
        boolean result = Main.valNum(pwNoNum);
        assertFalse(result);
    }

    @Test
    void valCap_shouldReturnTrue_WhenInputHasCap() {
        boolean result = Main.valCap(pwGood);
        assertTrue(result);
    }

    @Test
    void valCap_shouldReturnFalse_WhenInputHasNoCap() {
        boolean result = Main.valCap(pwNoCap);
        assertFalse(result);
    }

    @Test
    void valLow_shouldReturnTrue_WhenInputHasLow() {
        boolean result = Main.valLow(pwGood);
        assertTrue(result);
    }

    @Test
    void valLow_shouldReturnFalse_WhenInputHasNoLow() {
        boolean result = Main.valLow(pwNoLow);
        assertFalse(result);
    }

    @Test
    void valCommon_shouldReturnTrue_WhenInputIsNotCommon() throws FileNotFoundException {
        boolean result = Main.valCommon(pwGood);
        assertTrue(result);
    }

    @Test
    void valCommon_shouldReturnFalse_WhenInputIsCommon() throws FileNotFoundException {
        boolean result = Main.valCommon(pwCommon);
        assertFalse(result);
    }

    @Test
    void valSpecial_shouldReturnTrue_WhenInputContainsSpecialChar() throws FileNotFoundException {
        boolean result = Main.valSpecial(pwGood);
        assertTrue(result);
    }

    @Test
    void valSpecial_shouldReturnTrue_WhenInputContainsSpecialCharWithNeedToEscape() throws FileNotFoundException {
        boolean result = Main.valSpecial(pwGoodEscape);
        assertTrue(result);
    }

    @Test
    void valSpecial_shouldReturnFalse_WhenInputContainsNotSpecialChar() throws FileNotFoundException {
        boolean result = Main.valSpecial(pwNoSpecial);
        assertFalse(result);
    }

    @Test
    void  valPW_shouldReturnCorrectMessage_WhenInputIspwGood() throws FileNotFoundException {
        String expected = "Passwort entspricht den Regeln"+lineSeparator;
        String result = Main.valPW(pwGood);
        assertEquals(expected, result);
    }

    @Test
    void  valPW_shouldReturnCorrectMessage_WhenInputIspwShort() throws FileNotFoundException {
        String expected = "Passwort ist zu kurz"+lineSeparator;
        String result = Main.valPW(pwShort);
        assertEquals(expected, result);
    }

    @Test
    void  valPW_shouldReturnCorrectMessage_WhenInputIspwNoNum() throws FileNotFoundException {
        String expected = "Passwort muss eine Ziffer enthalten"+lineSeparator;
        String result = Main.valPW(pwNoNum);
        assertEquals(expected, result);
    }

    @Test
    void  valPW_shouldReturnCorrectMessage_WhenInputIspwNoCap() throws FileNotFoundException {
        String expected = "Passwort muss einen Großbuchstaben enthalten"+lineSeparator;
        String result = Main.valPW(pwNoCap);
        assertEquals(expected, result);
    }

    @Test
    void  valPW_shouldReturnCorrectMessage_WhenInputIspwNoLow() throws FileNotFoundException {
        String expected = "Passwort muss einen Kleinbuchstaben enthalten"+lineSeparator;
        String result = Main.valPW(pwNoLow);
        assertEquals(expected, result);
    }

    @Test
    void  valPW_shouldReturnCorrectMessage_WhenInputIspwNoSpecial() throws FileNotFoundException {
        String expected = "Passwort muss ein Sonderzeichen (" + specialChar + ") enthalten"+lineSeparator;
        String result = Main.valPW(pwNoSpecial);
        assertEquals(expected, result);
    }

    @Test
    void  valPW_shouldReturnCorrectMessage_WhenInputIspwCommon() throws FileNotFoundException {
        String expected = "Passwort taucht in einer Liste der häufigsten Passwörter auf" +lineSeparator;
        String result = Main.valPW(pwCommon);
        assertEquals(expected, result);
    }

    @Test
    void  valPW_shouldReturnCorrectMessage_WhenInputIsasdf() throws FileNotFoundException {
        String expected = "Passwort ist zu kurz"+lineSeparator +
                "Passwort muss eine Ziffer enthalten"+lineSeparator +
                "Passwort muss einen Großbuchstaben enthalten"+lineSeparator +
                "Passwort muss ein Sonderzeichen (" + specialChar + ") enthalten"+lineSeparator ;
        String result = Main.valPW("asdf");
        assertEquals(expected, result);
    }

}