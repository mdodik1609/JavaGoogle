package org.example.pattern11;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2011%3A%20Modified%20Binary%20Search.md
     *
     *  Pattern 11: Modified Binary Search
     *
     * */
    public static void main(String[] args) {
        System.out.println(
                search(
                        new int[]{-1,0,3,5,7,9,12}, 9
                )
        );
    }

    /**
     *  https://leetcode.com/problems/binary-search/
     *
     *  704. Binary Search
     * */
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while(start <= end) {
            int mid = (int) Math.floor(start + (end - start) / 2);
            if(target == nums[mid]) {
                return mid;
            }
            if(target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
    /***
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2011%3A%20Modified%20Binary%20Search.md#ceiling-of-a-number-medium
     *
     *  Ceiling of a Number
     */
    public static int searchCeilingOfNumber(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        if(nums[end] > target) return -1;

        while(start <= end) {
            int mid = (int) Math.floor(start + (end - start) / 2);
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return start;
    }
}
