package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    String pwGood = "sR12hcH:op";
    String pwShort = "alös";
    String pwNoNum = "dtSRfkjlFK";
//    String pwNoCap = "dtsr4fkjlfk";
//    String pwNoLow = "DTRS4FKJASD";

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
}