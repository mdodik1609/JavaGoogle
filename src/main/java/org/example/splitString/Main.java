package org.example.splitString;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        /**
         *  https://leetcode.com/problems/number-of-ways-to-split-a-string/
         *
         *
         * Number of one-s in evey string has to be same!
         * */
        System.out.println(numWays("10101"));
        System.out.println(numWays("1001"));
        System.out.println(numWays("0000"));

    }



    public static int numWays(String s) {
        long l = s.length();
        long modulo = 1_000_000_007;
        long count1 = 0;
        char[] string = s.toCharArray();
        for(int i=0;i<string.length;i++) {
            if(string[i]=='1')
                count1++;
        }

        if(count1 == 0) {
            return (int) ( (( (l - 1) * (l - 2) ) / 2) % modulo);
        }
        if(count1 % 3 != 0) return 0;

        long seg12 = 0;
        long seg23 = 0;

        long segment = count1 / 3;
        count1 = 0;

        for(int i=0;i<l;i++)
        {
            if(string[i]=='1')
                count1++;
            if(count1 == segment)
                seg12++;
            if(count1 == 2 * segment)
                seg23++;
        }
        return (int)((seg12*seg23)%modulo);
    }

    /**
     *  https://leetcode.com/problems/number-of-good-ways-to-split-a-string/description/
     *
     *  1525. Number of Good Ways to Split a String
     * */
    public int numSplits(String s) {
        HashMap<Character, Integer> right = new HashMap<Character, Integer>();

        for(int i = 0; i < s.length();i++) {
            right.put(s.charAt(i), right.getOrDefault(s.charAt(i), 0) + 1);
            // if(left.get(s.charAt(i)) == null) left.add(s.chartAt(i), 0);
            // left.replace(s.charAt(i), left.get(s.chartAt(i)));
        }

        HashMap<Character, Integer> left = new HashMap<Character, Integer>();
        int countGoodSplit = 0;

        for(int i = 0; i < s.length(); i++) {
            left.put(s.charAt(i), left.getOrDefault(s.charAt(i), 0) + 1);

            right.put(s.charAt(i), right.get(s.charAt(i)) - 1);
            if(right.get(s.charAt(i)) == 0) {
                right.remove(s.charAt(i));
            }

            if(left.size() == right.size()) {
                countGoodSplit++;
            }
        }

        return countGoodSplit;
    }
}
