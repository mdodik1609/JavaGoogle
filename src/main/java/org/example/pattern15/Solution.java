package org.example.pattern15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        combinationSum(
                new int[]{2,3,7}, 7
        );
    }

    /**
     *  https://leetcode.com/problems/combination-sum/
     *
     *  39. Combination Sum
     * */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>>[] dp = new List[target + 1];
        dp[0] = new ArrayList<>();
        dp[0].add(new ArrayList<>());

        for (int c : candidates) {
            for (int i = c; i <= target; i++) {
                if (dp[i - c] != null) {
                    if (dp[i] == null)
                        dp[i] = new ArrayList<>();

                    for (List<Integer> list : dp[i - c]) {
                        List<Integer> copy = new ArrayList<>(list);
                        copy.add(c);

                        dp[i].add(copy);
                    }
                }
            }
        }

        return dp[target] == null ? new ArrayList<>() : dp[target];
    }

    /**
     *  https://leetcode.com/problems/target-sum/
     *
     *  494. Target sum
     * */
    public static int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
        }

        if(sum - target < 0 || (sum - target) % 2 == 1) {
            return 0;
        }

        int halfSum = (sum - target) / 2;
        int[][] dp = new int[n + 1][halfSum + 1];
        dp[0][0] = 1;

        for(int i = 1; i < n + 1; i++) {
            for(int j = 0; j <= halfSum; j++) {
                if(nums[i - 1] <= j) {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][halfSum];
    }
}
