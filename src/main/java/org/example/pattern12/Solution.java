package org.example.pattern12;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2012%3A%20%20Bitwise%20XOR.md
     *
     *  Pattern 12: Bitwise XOR
     * */
    public static void main(String[] args) {

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

}
