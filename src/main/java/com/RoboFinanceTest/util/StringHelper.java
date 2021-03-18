package com.RoboFinanceTest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    // Является ли строка буквами
    public static boolean isLetters(String string) {
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я]");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    // Является ли строка цифрами, буквами и дефисом
    public static boolean isLettersNumbersAndHyphen(String string) {
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я\\-]");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    // Является ли строка цифраи, буквами и слэшем
    public static boolean isLettersNumbersAndSlash(String string) {
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я/]");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    // Является ли строка цифрами
    public static boolean isNumbers(String string) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}
