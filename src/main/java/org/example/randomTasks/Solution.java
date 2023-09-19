package org.example.randomTasks;

import java.util.Arrays;

public class Solution {
    /**
     * Random tasks from here:
     *  https://leetcode.com/discuss/interview-question/352460/Google-Online-Assessment-Questions
     *
     * */
    public static void main(String[] args) {
        System.out.println(
                minAbs(
                        new int[]{1,2,3,4,5}
                )
        );
    }

    /**
     *  https://leetcode.com/discuss/interview-question/356433/
     *
     * */
    public static int minAbs(int[] tasks){
        int sum = Arrays.stream(tasks).sum();
        int halfSum = sum / 2;
        int[] dp = new int[halfSum + 1];

        for(int i = 0; i < tasks.length; i++) {
            if(dp[halfSum] == halfSum) break;
            if(tasks[i] > halfSum) continue;
            for(int j = halfSum; j >= tasks[i]; j--) {
                dp[j] = Math.max(dp[j], tasks[i] + dp[j - tasks[i]]);
            }
        }
        return (sum - dp[halfSum]) - dp[halfSum];
    }
}
