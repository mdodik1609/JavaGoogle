package org.example.splitString;

import java.util.Arrays;

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
}
