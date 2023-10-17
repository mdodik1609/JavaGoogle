package org.example.take2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {

    public static void main(String[] args) {
//        System.out.println(characterReplacement("AAAA", 0));

//        System.out.println(
//                findAnagrams("baa", "aa")
//        );

//        System.out.println(
//                minWindow(
//                        "ADOBECODEBANC", "ABC"
//                )
//        );

        System.out.println(
                removeDuplicates(
                        new int[]{0,0,1,1,1,2,2,3,3,4}
                )
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

    /**
     *  https://leetcode.com/problems/minimum-window-substring/description/
     *
     *
     *  76. Minimum Window Substring
     * */
    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> freqMap = new HashMap<Character, Integer>();
        for(int i = 0; i < t.length(); i++) {
            Character curr = t.charAt(i);
            if(freqMap.containsKey(curr)) {
                freqMap.put(curr, freqMap.get(curr) + 1);
            } else {
                freqMap.put(curr, 1);
            }
        }

        int start = 0;
        int end = 0;
        boolean match = false;
        int matchNum = 0;
        String result = "";
        HashMap<Character, Integer> helperMap = new HashMap<>(freqMap);

        for(;end < s.length(); end++) {
            Character curr = s.charAt(end);
            if(helperMap.containsKey(curr)) {
                helperMap.put(curr, helperMap.get(curr) - 1);
                if(helperMap.get(curr) == 0) matchNum++;
                if(matchNum == freqMap.size()) match = true;
            }

            if((result.equals("") || result.length() > end - start + 1) && match) {
                result = s.substring(start, end + 1);
            }
            while(match) {
                Character tempStart = s.charAt(start);
                if(helperMap.containsKey(tempStart)) {
                    helperMap.put(tempStart, helperMap.get(tempStart) + 1);
                    if(helperMap.get(tempStart) == 1) matchNum--;
                    if(matchNum != freqMap.size()) match = false;
                }
                start++;
                if((result.equals("") || result.length() > end - start + 1) && match) {
                    result = s.substring(start, end + 1);
                }
            }
        }

        return result;
    }

    public static int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 1;
        ArrayList<Integer> result = new ArrayList<>();
        result.add(nums[i]);
        for(; j < nums.length; j++) {
            if(result.get(i) != nums[j]){
                i++;
                result.add(nums[j]);
            }
        }
        nums = result.stream().mapToInt(a -> a).toArray();
        return result.size();
    }

    class Solution {
        public static void sortColors(int[] nums) {
            int i = 0;
            int zeros = 0;
            int twos = nums.length - 1;
            while(i <= twos) {
                if(nums[i] == 0) {
                    nums[i] = nums[zeros];
                    nums[zeros] = 0;
                    i++; zeros++;
                } else if(nums[i] == 1) {
                    i++;
                } else {
                    nums[i] = nums[twos];
                    nums[twos] = 2;
                    twos--;
                }
            }
        }
    }

    public static int findLengthOfShortestSubarray(int[] nums) {
        int l = nums.length;
        if (l == 0) return 0;

        int low = 0;
        int high = l - 1;

        while (low < l - 1 && nums[low] <= nums[low + 1]) {
            low++;
        }

        if (low == l) return 0; //sorted array

        while (high > 0 && nums[high] >= nums[high - 1]) {
            high--;
        }


        int minLength = Math.min(l - low - 1, high);
        int k = low;
        low = 0;

        while (low <= k && high < l) {
            if (nums[low] <= nums[high]) {
                minLength = Math.min(minLength, high - low - 1);
                low++;
            } else {
                high++;
            }
        }

        if (minLength >= 0) return minLength;
        return 0;
    }

    public static int[][] insert(int[][] intervals, int[] interval) {
        ArrayList<int[]> result = new ArrayList<>();
        int i = 0;

        while (i < intervals.length && intervals[i][1] < interval[0]) {
            result.add(intervals[i]);
            i++;
        }
        while (i < intervals.length && intervals[i][0] <= interval[1]) {
            interval[0] = Math.min(intervals[i][0], interval[0]);
            interval[1] = Math.max(intervals[i][1], interval[1]);
            i++;
        }
        result.add(interval);
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }

    public static int missingNumber(int[] nums) {
        int l = nums.length;
        int result = l;
        for (int i = 0; i < l; i++) {
            if (nums[i] != i && nums[i] < l) {
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
                i--;
            } else if (nums[i] == l) {
                result = i;
            }
        }
        return result;
    }
}
