package org.example.take2;

import java.util.HashMap;

class Solution {

    public static void main(String[] args) {
        System.out.println(characterReplacement("AAAA", 0));
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
}
