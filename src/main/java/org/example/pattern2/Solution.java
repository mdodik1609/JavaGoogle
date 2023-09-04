package org.example.pattern2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;

public class Solution {
    public static void main(String[] args){
//        Arrays.stream(twoSum(new int[]{2, 7, 11, 15}, 9)).forEach( it -> System.out.print(it));
//        System.out.println("\n====================");
//        Arrays.stream(twoSum(new int[]{3,3}, 6)).forEach( it -> System.out.print(it));
//        System.out.println("\n====================");
//        Arrays.stream(twoSum(new int[]{3,2,4}, 6)).forEach( it -> System.out.print(it));

        Arrays.stream(sortedSquares(new int[]{-7,-3,2,3,11})).forEach( it -> System.out.print(it + ","));
    }

    /**
     *  https://leetcode.com/problems/two-sum/
     *
     *  1. Two Sum
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> table = new HashMap<>();

        int l = nums.length;;
        for(int i = 0; i < l; i++) {
            int currentNum = nums[i];
            if(table.containsKey(target-currentNum)){
                return new int[]{i, table.get(target - currentNum)};
            }
            table.put(nums[i], i);
        }
        throw new IllegalArgumentException("Should have found solution.");
    }

    /**
     *  https://leetcode.com/problems/squares-of-a-sorted-array/
     *
     *  977. Squares of a Sorted Array
     * */
    public static int[] sortedSquares(int[] nums) {
        int l = nums.length;
        int[] result = new int[l];

        int endPointer = l - 1;
        int lastPlace = l - 1;

        for(int startPointer = 0; startPointer < l; startPointer++) {
            if(Math.pow(nums[startPointer], 2) < Math.pow(nums[endPointer], 2) ) {
                result[lastPlace] = (int) Math.pow(nums[endPointer], 2);
                endPointer--;
                startPointer--;
            } else {
                result[lastPlace] = (int) Math.pow(nums[startPointer], 2);
            }
            lastPlace--;

            if(startPointer == endPointer) break;
        }
        return result;
    }
}
