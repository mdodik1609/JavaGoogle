package org.example.pattern10;

import java.util.*;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2010%3A%20Subsets.md
     *
     *  Pattern 10: Subset
     * */
    public static void main(String[] args) {
//        System.out.println(
//                subsets(
//                        new int[]{1,2}
//                )
//        );
//        System.out.println(
//                permute(
//                        new int[]{1,2,3}
//                )
//        );
        System.out.println(
                letterCasePermutation(
                        "a1bCd"
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

    /**
     *  https://leetcode.com/problems/subsets-ii/description/
     *
     *  90. Subsets II
     * */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
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
                if(tempResult.contains(tempList)) { continue; }
                tempResult.add(tempList);
            }
            result = tempResult;
        }
        return new ArrayList<>(result);
    }

    /**
     *  https://leetcode.com/problems/permutations/
     *
     *  46. Permutations
     * */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        permuteRecursive(new ArrayList<>(), nums, list);
        return list;
    }
    public static void permuteRecursive(List<Integer> temp, int[] nums, List<List<Integer>> subsets) {
        if(temp.size() == nums.length){
            subsets.add(new ArrayList<>(temp));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(temp.contains(nums[i])) continue;
                temp.add(nums[i]);
                permuteRecursive(temp, nums, subsets);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /**
     *  https://leetcode.com/problems/letter-case-permutation/
     *
     *  784. Letter Case Permutation
     * */
    public static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        result.add(s);
        for(int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if(Character.isDigit(currentChar)) continue;

            int n = result.size();
            for(int j = 0; j < n; j++) {
                String[] chars = result.get(j).split("");
                if(chars[i] == chars[i].toLowerCase()) {
                    chars[i] = chars[i].toUpperCase();
                } else {
                    chars[i] = chars[i].toLowerCase();
                }
                result.add(String.join("", chars));
            }
        }
        return result;
    }
}
