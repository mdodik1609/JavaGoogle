package org.example.pattern5;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2005%3A%20Cyclic%20Sort.md
     *
     *  Pattern 5: Cyclic Sort
     *
     * */
    public static void main(String[] args) {
        System.out.println(missingNumber(
                new int[]{
                        3, 0, 1
                }
        ));
        System.out.println(missingNumber(
                new int[]{
                        3, 0, 1, 6, 4, 5
                }
        ));
        System.out.println(missingNumber(
                new int[]{
                        1
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
}
