package org.example.pattern12;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2012%3A%20%20Bitwise%20XOR.md
     *
     *  Pattern 12: Bitwise XOR
     * */
    public static void main(String[] args) {
        System.out.println(
               bitwiseComplement(10)
        );
    }
    /**
     * https://leetcode.com/problems/single-number/
     *
     *  136. Single Number
     * */
    public static int singleNumber(int[] nums) {
        int result = 0;
        for(int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }
    /**
     * https://leetcode.com/problems/single-number-iii/
     *
     *  260. Single Number III
     * */
    public static int[] singleNumberII(int[] nums) {
        int xor = 0;
        for(int i = 0; i < nums.length; i ++) {
            xor = xor ^ nums[i];
        }

        int rightBit = xor & (-xor);

        int xor1 = 0;
        int xor2 = 0;

        for(int i = 0; i < nums.length; i++) {
            if ((nums[i] & rightBit) == 0) {
                xor1 ^= nums[i];
            } else {
                xor2 ^= nums[i];
            }
        }
        return new int[]{xor1, xor2};
    }

    /**
     *  https://leetcode.com/problems/complement-of-base-10-integer/
     *
     *  1009. Complement of base 10 integer
     * */
    public static int bitwiseComplement(int n) {
        if(n == 0) return 1;
        int bitCount = 0;
        int temp = n;

        while(temp > 0) {
            bitCount++;
            temp = temp >> 1;
        }
        return n ^ (int) Math.pow(2, bitCount) - 1;
    }
}
