package org.example.pattern15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
//        combinationSum(
//                new int[]{2,3,7}, 7
//        );
//        minCost(
//                9, new int[]{5,6,1,4,7}
//        );
//        coinChange(
//                new int[]{1,2,3}, 5
//        );
        coinChange(
                new int[]{2}, 3
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
    /**
     *  https://leetcode.com/problems/coin-change/
     *
     *  322. Coin change
     * */
    public static int coinChange(int[] coins, int amount) {
        int n = coins.length;

        int[][] dp = new int[n][amount + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= amount; j++) {
                dp[i][j] = 10000 + 1;
            }
        }
        for(int i = 0; i < n; i++) dp[i][0] = 0;

        for(int i = 0; i < n;i++) {
            for (int j = 1; j <= amount; j++) {
                if(i > 0) {
                    dp[i][j] = dp[i-1][j];
                }
                if(j >= coins[i]) {
                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i][j-coins[i]] + 1
                    );
                }
            }
        }
        if(dp[n-1][amount] > 10000) return -1;
        return dp[n - 1][amount];
    }
    /**
     *  https://leetcode.com/problems/fibonacci-number/
     *
     *  509. Fibonacci number
     * */
    public int fib(int n) {
        if(n == 0) return 0;
        if(n == 1 || n == 2) return 1;

        int[] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = dp[2] = 1;

        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i -1] + dp[i -2];
        }
        return dp[n];
    }

    /**
     *  https://leetcode.com/problems/jump-game-ii/
     *
     *  45. Jump Game II
     * */
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        for(int i = 0; i < n; i++) dp[i] = Integer.MAX_VALUE;

        dp[0] = 0;
        for(int i = 0; i < n - 1;i++) {
            for(int j = i + 1; j < n && j <= nums[i] + i; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[n-1];
    }

    /**
     *  https://leetcode.com/problems/house-robber
     *
     *  198. House Robber
     * */
    public int rob(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for(int i = 1; i < n; i ++) {
            dp[i + 1] = Math.max(
                    dp[i-1] + nums[i],
                    dp[i]
            );
        }
        return dp[n];
    }
}
