package org.example.pattern5;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2005%3A%20Cyclic%20Sort.md
     *
     *  Pattern 5: Cyclic Sort
     *
     * */
    public static void main(String[] args) {
//        System.out.println(missingNumber(
//                new int[]{
//                        3, 0, 1
//                }
//        ));
//        System.out.println(missingNumber(
//                new int[]{
//                        3, 0, 1, 6, 4, 5
//                }
//        ));
//        System.out.println(missingNumber(
//                new int[]{
//                        1
//                }
//        ));

//        System.out.println(findDisappearedNumbers(
//                new int[]{
//                        2, 3, 1, 8, 2, 3, 5, 1
//                }
//        ));
        System.out.println(findDisappearedNumbers(
                new int[]{
                        4,3,2,7,8,2,3,1
                }
        ));
    }

    /**
     *  https://leetcode.com/problems/missing-number/
     *
     *  268. Missing Number
     * */
    public static int missingNumber(int[] nums) {
        int l = nums.length;
        int result = l;
        for(int i = 0; i < l; i++) {
            if(nums[i] != i && nums[i] < l) {
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
                i--;
            } else if(nums[i] == l) {
                result = i;
            }
        }
        return result;
    }

    /**
     *  https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
     *
     *  448. Find all numbers disappeared in an Array
     * */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> missing = new ArrayList<>();

        for(int i = 0; i < nums.length; i++) {
            int currNum = nums[i];
            int swapNum = nums[currNum - 1];
            if(currNum != i + 1 && currNum != swapNum) {
                nums[i] = swapNum;
                nums[currNum - 1] = currNum;
                i--;
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                missing.add(i + 1);
            }
        }

        return missing;
    }
}
