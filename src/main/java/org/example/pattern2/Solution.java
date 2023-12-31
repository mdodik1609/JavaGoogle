package org.example.pattern2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args){
//        Arrays.stream(twoSum(new int[]{2, 7, 11, 15}, 9)).forEach( it -> System.out.print(it));
//        System.out.println("\n====================");
//        Arrays.stream(twoSum(new int[]{3,3}, 6)).forEach( it -> System.out.print(it));
//        System.out.println("\n====================");
//        Arrays.stream(twoSum(new int[]{3,2,4}, 6)).forEach( it -> System.out.print(it));

//        Arrays.stream(sortedSquares(new int[]{-7,-3,2,3,11})).forEach( it -> System.out.print(it + ","));

//        System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1));

//        System.out.println(numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));
//        System.out.println(numSubarrayProductLessThanK(new int[]{1,2,3}, 0));

//        System.out.println(backspaceCompare("cat", "catt#"));
//        System.out.println(backspaceCompare("ab#c", "ad#c"));
//        System.out.println(backspaceCompare("ab##", "c#d#"));
//        System.out.println(backspaceCompare("a#c", "b"));

        System.out.println(findLengthOfShortestSubarray(new int[]{
                1,2,3,10,4,2,3,5
        }));
        System.out.println(findLengthOfShortestSubarray(new int[]{
                5, 4, 3, 2, 1
        }));
        System.out.println(findLengthOfShortestSubarray(new int[]{
                1,2,3
        }));
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

    /**
     *  https://leetcode.com/problems/3sum-closest/description/
     *
     *  16. 3Sum Closest
     * */
    public static int threeSumClosest(int[] nums, int target) {
        int result = Integer.MAX_VALUE;
        int l = nums.length;

        Arrays.sort(nums);

        for(int i = 0; i < l - 2; i++) {
            int pivotL = i + 1;
            int pivotR = l - 1;
            while (pivotL < pivotR) {
                var sum = nums[i] + nums[pivotL] + nums[pivotR];
                if (sum > target) pivotR--;
                else pivotL++;
                if (Math.abs(sum - target) < Math.abs(result - target)) result = sum;
            }
        }

        return result;
    }

    /**
     *  https://leetcode.com/problems/subarray-product-less-than-k/
     *
     *  713. Subarray Product Less Than K
     * */
    public static int numSubarrayProductLessThanK(int[] nums, int target) {
        if(target<=1) return 0;

        int result=0;
        int mul=nums[0];
        if(mul<target)  result++;
        int left=0;
        int right=1;
        while(right!=nums.length){
            int val=nums[right];
            mul=mul*val;
            if (mul >= target) {
                while (mul >= target) {
                    mul = mul / nums[left];
                    left++;
                }
            }
            result+=right-left+1;
            right++;
        }
        return result;
    }

    /**
     *  https://leetcode.com/problems/sort-colors/
     *
     *  75. Sort Colors
     *
     * */
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

    /**
     *  https://leetcode.com/problems/backspace-string-compare/
     *
     *  844. Backspace String Compare
     * */
    public static boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        while(i >= 0 || j >= 0) {
            int indexS = getNextCharIndex(s, i);
            int indexT = getNextCharIndex(t, j);

            if(indexS < 0 && indexT < 0) return true;
            else if (indexS < 0 || indexT < 0) return false;

            char sChar = s.charAt(indexS);
            char tChar = t.charAt(indexT);
            if(sChar != tChar) return false;

            i = indexS - 1;
            j = indexT - 1;
        }
        return true;
    }
    public static int getNextCharIndex(String s, int i) {
        int numOfHashtags = 0;
        while(i>= 0) {
            if(s.charAt(i) == '#') {
                numOfHashtags++;
            } else if(numOfHashtags > 0) {
                numOfHashtags--;
            } else {
                break;
            }
            i--;
        }
        return i;
    }

    /**
     *  https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
     *
     *  1574. Shortest Subarray to be Removed to Make Array Sorted
     * */
    public static int findLengthOfShortestSubarray(int[] nums) {
        int l = nums.length;
        if(l == 0) return 0;

        int low = 0;
        int high = l - 1;

        while (low < l - 1 && nums[low] <= nums[low + 1]) {
            low++;
        }

        if(low == l) return 0; //sorted array

        while(high > 0 && nums[high] >= nums[high - 1]) {
            high--;
        }


        int minLength = Math.min (l - low - 1, high);
        int k = low;
        low = 0;

        while (low <= k && high < l) {
            if (nums[low] <= nums[high]) {
                minLength = Math.min (minLength, high - low - 1);
                low++;
            }
            else {
                high++;
            }
        }

        if(minLength >= 0 ) return minLength;
        return 0;
    }
}
