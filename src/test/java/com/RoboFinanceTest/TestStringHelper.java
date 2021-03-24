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
        //System.out.println(StringHelper.isLetters(string));
        assertTrue(StringHelper.isLetters(string));
    }

    @Test
    public void isLetters_expectedFalse() {
        String string = "zaycev.net";
        //System.out.println(StringHelper.isLetters(string));
        assertFalse(StringHelper.isLetters(string));
    }

    @Test
    public void isLettersNumbersAndHyphen_expectedTrue() {
        String string = "Ivan-Банан";
        //System.out.println(StringHelper.isLettersNumbersAndHyphen(string));
        assertTrue(StringHelper.isLettersNumbersAndHyphen(string));
    }

    @Test
    public void isLettersNumbersAndHyphen_expectedFalse() {
        String string = "Ivan!=Банан";
        //System.out.println(StringHelper.isLettersNumbersAndHyphen(string));
        assertFalse(StringHelper.isLettersNumbersAndHyphen(string));
    }

    @Test
    public void isLettersNumbersAndSlash_expectedTrue() {
        String string = "1a/3";
        //System.out.println(StringHelper.isLettersNumbersAndSlash(string));
        assertTrue(StringHelper.isLettersNumbersAndSlash(string));
    }

    @Test
    public void isLettersNumbersAndSlash_expectedFalse() {
        String string = "2/2=1";
        //System.out.println(StringHelper.isLettersNumbersAndSlash(string));
        assertFalse(StringHelper.isLettersNumbersAndSlash(string));
    }

    @Test
    public void isNumbers_expectedTrue() {
        String string = "88005553535";
        //System.out.println(StringHelper.isLettersNumbersAndSlash(string));
        assertTrue(StringHelper.isNumbers(string));
    }

    @Test
    public void isNumbers_expectedFalse() {
        String string = "1o1";
        //System.out.println(StringHelper.isLettersNumbersAndSlash(string));
        assertFalse(StringHelper.isNumbers(string));
    }
}
