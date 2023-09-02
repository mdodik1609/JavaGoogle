package org.example.pattern1;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));
        System.out.println(findMaxAverage(new int[]{0,1,1,3,3}, 4));
        System.out.println(findMaxAverage(new int[]{0,4,0,3,2}, 1));


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
}
