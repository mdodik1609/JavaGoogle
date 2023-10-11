package org.example.take2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {

    public static void main(String[] args) {
//        System.out.println(characterReplacement("AAAA", 0));

        System.out.println(
                findAnagrams("baa", "aa")
        );
    }


    /**
     *  https://leetcode.com/problems/longest-repeating-character-replacement/description/
     *
     *  424. Longest Repeating Character Replacement
     * */
    static public int characterReplacement(String s, int k) {
        int start = 0;
        int end = 0;
        HashMap<Character, Integer> freqMap = new HashMap<>();
        int result = 0;
        int max = 1;
        int sum = 0;
        for(; end < s.length(); end++) {
            Character curr = s.charAt(end);

            if(!freqMap.containsKey(curr)) {
                freqMap.put(curr,  1);
            } else {
                freqMap.put(curr, freqMap.get(curr) + 1);
                if(freqMap.get(curr) > max) max = freqMap.get(curr);
            }
            sum++;

            while(sum - max > k) {
                Character temp = s.charAt(start);
                if(freqMap.get(temp) == 1) freqMap.remove(temp);
                else freqMap.put(temp, freqMap.get(temp) - 1);
                sum--;
                start++;
            }

            if(result < end - start + 1) result = end - start + 1;
        }
        return result;
    }

    /**
     *  438. Find All Anagrams in a String
     *
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
     * */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        HashMap<Character, Integer> freqMap = new HashMap<>();
        int pSize = p.length();


        for(int i = 0; i < pSize; i++) {
            Character curr = p.charAt(i);
            if(freqMap.containsKey(curr)) {
                freqMap.put(curr, freqMap.get(curr) + 1);
            } else {
                freqMap.put(curr, 1);
            }
        }

        for(int i = 0; i < s.length() - pSize + 1; i++) {
            String temp = s.substring(i, i+pSize);
            if(isAnagram(temp, new HashMap<>(freqMap))) result.add(i);
        }
        return result;
    }

    public static boolean isAnagram(String s, HashMap<Character, Integer> freqMap) {
        for(int i = 0; i < s.length(); i++) {
            if(freqMap.containsKey(s.charAt(i))) {
                if(freqMap.get(s.charAt(i)) == 1) {
                    freqMap.remove(s.charAt(i));
                } else {
                    freqMap.put(s.charAt(i), freqMap.get(s.charAt(i)) - 1);
                }
            } else return false;
        }
        return true;
    }
}
