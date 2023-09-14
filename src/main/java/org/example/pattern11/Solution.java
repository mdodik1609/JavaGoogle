package org.example.pattern11;

import java.util.Arrays;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2011%3A%20Modified%20Binary%20Search.md
     *
     *  Pattern 11: Modified Binary Search
     *
     * */
    public static void main(String[] args) {
//        System.out.println(
//                search(
//                        new int[]{-1,0,3,5,7,9,12}, 9
//                )
//        );
//        System.out.println(
//                searchInsert(
//                        new int[]{1,3,5,6}, 7
//                )
//        );

//        System.out.println(
//                nextGreatestLetter(new char[]{'c','f','j'}, 'd')
//        );
//        System.out.println(
//                searchRange(new int[]{1}, 1)
//        );
        System.out.println(
                search2(
                        new int[]{4,5,6,7,0,1,2},
                        0
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
    /**
     *  https://leetcode.com/problems/search-insert-position/
     *
     *  35. Search Insert Position
     * */
    public static int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            int mid = (int) Math.floor( start + (end - start) / 2);
            if(nums[mid] < target) {
                start = mid + 1;
            } else if(nums[mid] > target) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return end + 1;
    }
    /**
     *  https://leetcode.com/problems/find-smallest-letter-greater-than-target/
     *
     *  744. Find Smallest Letter Greater Than Target
     * */
    public static char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {
            int mid = (int) Math.floor(start + (end - start) / 2);
            if(target < letters[mid] ) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return letters[start % letters.length];
    }
    /**
     * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     *
     *
     *  34. Find First and Last Position of Element in Sorted Array
     * */
    public static int[] searchRange(int[] nums, int target) {
        return new int[]{
                binarySearch(nums, target, false),
                binarySearch(nums, target, true)
        };
    }
    public static int binarySearch(int[] nums, int target, boolean lowHigh) {
        int start = 0;
        int end = nums.length - 1;
        int result = -1;
        while (start <= end) {
            int mid = (int) Math.floor(start + (end - start) / 2);
            if(target < nums[mid] ) {
                end = mid - 1;
            } else if(target > nums[mid]){
                start = mid + 1;
            } else {
                result = mid;
                if(lowHigh) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return result;
    }

    /**
     *  https://leetcode.com/problems/search-in-rotated-sorted-array/
     *
     *  33. Search in Rotated Sorted Array
     **/
    public static int search2(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while(start <= end) {
            int mid = (int) Math.floor(start + (end - start) / 2);
            if (target == nums[mid]) {
                return mid;
            }

            if(nums[start] <= nums[mid]) {
                if(target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if(target > nums[mid] && target <=  nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
