package com.RoboFinanceTest;

import com.RoboFinanceTest.util.StringHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestStringHelper {

    @Test
    public void isLetters_expectedTrue() {
        String string = "ИванIvanBanan";
        assertTrue(StringHelper.isLetters(string));
    }

    @Test
    public void isLetters_expectedFalse() {
        String string = "zaycev.net";
        assertFalse(StringHelper.isLetters(string));
    }

    @Test
    public void isLettersNumbersAndHyphen_expectedTrue() {
        String string = "Ivan-Банан";
        assertTrue(StringHelper.isLettersNumbersAndHyphen(string));
    }

    @Test
    public void isLettersNumbersAndHyphen_expectedFalse() {
        String string = "Ivan!=Банан";
        assertFalse(StringHelper.isLettersNumbersAndHyphen(string));
    }

    @Test
    public void isLettersNumbersAndSlash_expectedTrue() {
        String string = "1a/3";
        assertTrue(StringHelper.isLettersNumbersAndSlash(string));
    }

    @Test
    public void isLettersNumbersAndSlash_expectedFalse() {
        String string = "2/2=1";
        assertFalse(StringHelper.isLettersNumbersAndSlash(string));
    }

    @Test
    public void isNumbers_expectedTrue() {
        String string = "88005553535";
        assertTrue(StringHelper.isNumbers(string));
    }

    @Test
    public void isNumbers_expectedFalse() {
        String string = "1o1";
        assertFalse(StringHelper.isNumbers(string));
    }
}
