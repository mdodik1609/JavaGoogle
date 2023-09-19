package org.example.pattern15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
//        combinationSum(
//                new int[]{2,3,7}, 7
//        );
        minCost(
                9, new int[]{5,6,1,4,7}
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

    /**
     *      https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
     *
     *  1547. Minimum Cost to Cut a Stick
     * */
    public static int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int numOfCuts = cuts.length;
        int[] cuts2 = new int[numOfCuts + 2];
        cuts2[0] = 0;
        for(int i = 1; i < cuts2.length - 1; i++) {
            cuts2[i] = cuts[i - 1];
        }
        cuts2[numOfCuts + 1] = n;

        int[][] dp = new int[numOfCuts + 2][numOfCuts + 2];

        for(int i = 2; i < numOfCuts + 2; i++) {
            for(int j = 0; j < numOfCuts + 2 - i; j++) {
                int k = j + i;
                dp[j][k] = Integer.MAX_VALUE;
                for(int l = j + 1; l < k; l++){
                    dp[j][k] = Math.min(
                            dp[j][k],
                            dp[j][l] + dp[l][k] + cuts2[k] - cuts2[j]
                    );
                }
            }
        }
        return dp[0][numOfCuts + 1];
    }
}
