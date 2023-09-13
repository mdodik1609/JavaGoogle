package org.example.pattern10;

import java.util.*;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2010%3A%20Subsets.md
     *
     *  Pattern 10: Subset
     * */
    public static void main(String[] args) {
        System.out.println(
                subsets(
                        new int[]{1,2}
                )
        );
    }
    /**
     *  https://leetcode.com/problems/subsets/
     *
     *  78. Subsets
     * */
    public static List<List<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for(int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            int currentLen = result.size();
            ArrayList<ArrayList<Integer>> tempResult = new ArrayList<>();

            for(int j = 0; j < currentLen; j++) {
                tempResult.add(new ArrayList<>(result.get(j)));
            }

            for(int j = 0; j < currentLen; j++) {
                ArrayList<Integer> tempList = result.get(j);
                if(tempList.contains(currentNum)) { continue; }
                tempList.add(currentNum);
                tempResult.add(tempList);
            }
            result = tempResult;
        }
        return new ArrayList<>(result);
    }
}
