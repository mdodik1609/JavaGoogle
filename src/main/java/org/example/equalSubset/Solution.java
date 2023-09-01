package org.example.equalSubset;

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        /**
         *  https://leetcode.com/problems/partition-equal-subset-sum/solutions/
         *
         *
         * */


        System.out.println(canPartition(new int[]{1,5,11,5}));
        System.out.println( canPartition(new int[]{1,2,3,5}));
    }




    public static boolean canPartition(int[] nums) {
        int l = nums.length;
        if (l < 2) return false;

        int sum = Arrays.stream(nums).sum();
        if(sum % 2 != 0) return false;

        sum = sum / 2;

        boolean[][] cache = new boolean[l + 1][sum + 1];

        for(int i = 0; i <= l; i++) {
            for(int j = 0; j <= sum; j++) {
                if(i == 0 || j == 0) cache[i][j] = false;
                else if(nums[i-1] > j) cache[i][j] = cache[i-1][j];
                else if(nums[i-1]==j) cache[i][j] = true;
                else cache[i][j] = cache[i-1][j] || cache[i-1][j-nums[i-1]];
            }
        }
        return cache[l][sum];
    }

}
