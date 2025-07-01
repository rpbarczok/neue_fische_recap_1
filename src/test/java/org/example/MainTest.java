package org.example;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    String pwGood = "sR12hcH:op";
    String pwShort = "alös";
    String pwNoNum = "dtSRfkjlFK";
    String pwNoCap = "dtsr4fkjlfk";
    String pwNoLow = "DTRS4FKJASD";
    String pwCommon = "P@ssw0rd";


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
}