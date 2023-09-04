package org.example.pattern1;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
//        System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));
//        System.out.println(findMaxAverage(new int[]{0,1,1,3,3}, 4));
//        System.out.println(findMaxAverage(new int[]{0,4,0,3,2}, 1));
//
//
//        System.out.println(longestSubstringWithKdistinct("araaci", 2));
//        System.out.println(longestSubstringWithKdistinct("araaci", 1));
//        System.out.println(longestSubstringWithKdistinct("cbbebi", 3));
//
//
//        System.out.println(totalFruit(new int[]{1,2,1}));

//        System.out.println(lengthOfLongestSubstring("1231"));

//        System.out.println(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));

//        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));

    }


    /**
     * https://leetcode.com/problems/maximum-average-subarray-i/
     *
     *  643. Maximum Average Subarray I
     *
     *
     *  https://leetcode.com/problems/largest-subarray-length-k/
     *
     *  If we return only sum without dividing with length of array, it is solved task.
     *
     * */


    public static double findMaxAverage(int[] nums, int k) {
        int l = nums.length;
        if(l == 1) return nums[0];

        double sum = 0;

        for(int i = 0; i < k; i++) {
            sum = sum + nums[i];
        }
        double tempSum = sum;

        for(int i = k; i < l; i++) {
            tempSum = tempSum - nums[i - k] + nums[i];
            if(tempSum > sum) sum = tempSum;
        }
        return sum / k;
    }

    /**
     *  https://leetcode.com/problems/minimum-size-subarray-sum/
     *
     *  209. Minimum Size Subarray Sum
     *
     * */

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int i = 0;
        int j = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        while (j < nums.length) {
            sum += nums[j++];

            while (sum >= target) {
                min = Math.min(min, j - i);
                sum -= nums[i++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }


    /**
     * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
     *
     * Longest substring with at most k distinct characters
     * */
    public static int longestSubstringWithKdistinct(String s, int k){
        int result = 0;
        if(s.isEmpty()) return 0;

        int l = s.length();
        int windowStart = 0;
        HashMap<Character, Integer> charFrequency = new HashMap<>();

        for(int i = 0; i < l; i++) {
            char endChar = s.charAt(i);
            if(!charFrequency.containsKey(endChar)) {
                charFrequency.put(endChar, 0);
            }
            charFrequency.replace(endChar, charFrequency.get(endChar) + 1);

            while (charFrequency.values().stream().anyMatch(it -> it > k)) {
                //removing chars from the start
                char startChar = s.charAt(windowStart);
                charFrequency.replace(startChar, charFrequency.get(startChar) - 1);
                if(charFrequency.get(startChar) == 0) {
                    charFrequency.remove(startChar);
                }
                windowStart++;
            }
            result = Math.max(result, i - windowStart);
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/fruit-into-baskets/
     *
     * 904. Fruit Into Baskets
     * */

    public static int totalFruit(int[] fruits) {
        int result = 0;

        int l = fruits.length;
        int windowStart = 0;
        HashMap<Integer, Integer> fruitFreq = new HashMap<>();

        for(int i = 0; i < l; i++) {
            int endInt = fruits[i];
            if(!fruitFreq.containsKey(endInt)) {
                fruitFreq.put(endInt, 0);
            }
            fruitFreq.replace(endInt, fruitFreq.get(endInt) + 1);

            while (fruitFreq.size() > 2) {
                //removing chars from the start
                int startInt = fruits[windowStart];
                fruitFreq.replace(startInt, fruitFreq.get(startInt) - 1);
                if(fruitFreq.get(startInt) == 0) {
                    fruitFreq.remove(startInt);
                }
                windowStart++;
            }
            result = Math.max(result, i - windowStart + 1);
        }

        return result;
    }

    /**
     *
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     *
     *
     * 3. Longest Substring Without Repeating Characters
     *
     * */

    public static int lengthOfLongestSubstring(String s) {
        int result = 0;

        int l = s.length();
        int windowStart = 0;
        HashMap<Character, Integer> charFrequency = new HashMap<>();

        for(int i = 0; i < l; i++) {
            char endChar = s.charAt(i);
            if(!charFrequency.containsKey(endChar)) {
                charFrequency.put(endChar, 0);
            }
            charFrequency.replace(endChar, charFrequency.get(endChar) + 1);

            while (charFrequency.values().stream().anyMatch(it -> it > 1)) {
                //removing chars from the start
                char startChar = s.charAt(windowStart);
                charFrequency.replace(startChar, charFrequency.get(startChar) - 1);
                if(charFrequency.get(startChar) == 0) {
                    charFrequency.remove(startChar);
                }
                windowStart++;
            }
            result = Math.max(result, i - windowStart + 1);
        }

        return result;
    }


    /**
     * https://leetcode.com/problems/max-consecutive-ones-iii/
     *
     * 1004. Max Consecutive Ones III
     * */
    public static int longestOnes(int[] nums, int k) {
        if(nums.length == 0) return 0;
        int result = 0;

        int l = nums.length;
        int windowStart = 0;
        int zeros = 0;

        for(int i = 0; i < l; i++) {
            if(nums[i] == 0) {
                zeros++;
            }
            while(zeros > k) {
                if(nums[windowStart] == 0) {
                    zeros--;
                }
                windowStart++;
            }
            result = Math.max(result, i - windowStart + 1);
        }

        return result;
    }


    /**
     * https://leetcode.com/problems/permutation-in-string/
     *
     * 567. Permutation in String
     * */
    public static boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> characterFrequency = new HashMap<>();

        for(int i = 0; i < s1.length(); i++) {
            if(!characterFrequency.containsKey(s1.charAt(i))) {
                characterFrequency.put(s1.charAt(i), 0);
            }
            characterFrequency.replace(
                    s1.charAt(i),
                    characterFrequency.get(s1.charAt(i)) + 1
            );
        }

        int start = 0;
        int match = 0;
        for(int i = 0; i < s2.length(); i++) {
            char currentChar = s2.charAt(i);
            if(characterFrequency.containsKey(currentChar)){
                characterFrequency.replace(currentChar, characterFrequency.get(currentChar) - 1);
                if(characterFrequency.get(currentChar) == 0) {
                    match++;
                }
            }

            if(match == characterFrequency.keySet().size()) {
                return true;
            }

            if(i >=  s1.length() - 1) {
                char tempChar = s2.charAt(start);
                start++;
                if(characterFrequency.containsKey(tempChar)){
                    if(characterFrequency.get(tempChar) == 0) {
                        match--;
                    }
                    characterFrequency.replace(tempChar, characterFrequency.get(tempChar) + 1);
                }
            }
        }
        return false;
    }

    /**
     * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
     *
     * 30. Substring with Concatenation of All Words
     * */
    public static List<Integer> findSubstring(String str, String[] words) {
        List<Integer> resultIndex = new ArrayList<>();
        if (words.length == 0 || words[0].length() == 0) {
            return resultIndex;
        }
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }
        int wordCount = words.length;
        int wordLength = words[0].length();
        for (int i = 0; i < str.length() - wordCount * wordLength + 1; i++) {
            Map<String, Integer> wordsSeen = new HashMap<>();
            for (int j = 0; j < wordCount; j++) {
                int nextWordIndex = i + j * wordLength;
                // Get the next word from the string
                String word = str.substring(nextWordIndex, nextWordIndex + wordLength);
                if (!wordFreq.containsKey(word)) {
                    // Break if we don't need this word
                    break;
                }
                // Add the word to the wordsSeen map
                wordsSeen.put(word, wordsSeen.getOrDefault(word, 0) + 1);
                // No need to process further if the word has a higher frequency than required
                if (wordsSeen.get(word) > wordFreq.getOrDefault(word, 0)) {
                    break;
                }
                if (j + 1 == wordCount) {
                    // Store index if we have found all the words
                    resultIndex.add(i);
                }
            }
        }
        return resultIndex;
    }

}
