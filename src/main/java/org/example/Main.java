package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main (String[] args) {


        System.out.println(letterCombinations("23"));
        System.out.println(letterCombinations("232"));

    }

    public static List<String> letterCombinations(String digits) {
        if(digits.isBlank()) { return Collections.emptyList(); }

        HashMap<Integer, String> dictionary = new HashMap<>();
        dictionary.put(2, "abc");
        dictionary.put(3, "def");
        dictionary.put(4, "ghi");
        dictionary.put(5, "jkl");
        dictionary.put(6, "mno");
        dictionary.put(7, "pqrs");
        dictionary.put(8, "tuv");
        dictionary.put(9, "wxyz");

        List<String> result = new ArrayList<>();
        result.add("");

        for(char digit: digits.toCharArray()) {
            String letters = dictionary.get(digit - '0');
            List<String> tempResult = new ArrayList<>();

            for(String part : result) {
                for (char tempDigit : letters.toCharArray()) {
                    tempResult.add(part + tempDigit);
                }
            }
            result = tempResult;

        }

        return result;
    }
}
