package org.example.randomTasks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    /**
     * Random tasks from here:
     *  https://leetcode.com/discuss/interview-question/352460/Google-Online-Assessment-Questions
     *
     * */
    public static void main(String[] args) {
//        System.out.println(
//                minAbs(
//                        new int[]{1,2,3,4,5}
//                )
//        );

//        System.out.println(
//                maxRoom(
//                        new String[]{
//                                "+1A", "+3E", "-1A", "+4F", "+1A", "-3E"
//                        }
//                )
//        );

        System.out.println(
                minNumMeetingRooms(
                        new int[][] {
                                {1,5},{2,5},{6,7},{5,6},{3,8}
                        }
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
        return sum - dp[halfSum]* 2;
    }

    /**
     *
     *  https://leetcode.com/discuss/interview-question/421787/
     *
     * */
    public static String maxRoom(String[] rooms) {
        HashMap<String, Integer> freqMap = new HashMap<>();

        for(String room : rooms) {
            if(room.charAt(0) == '-') continue;

            Character[] currentRoom = new Character[2];
            currentRoom[0] = room.charAt(1);
            currentRoom[1] = room.charAt(2);

            freqMap.put(currentRoom.toString(), freqMap.getOrDefault(currentRoom.toString(), 0) + 1);
        }
        int max = 0;
        String maxRoom = "";
        for(Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if(max < entry.getValue()) {
                max = entry.getValue();
                maxRoom = entry.getKey();
            }
        }
        return maxRoom;
    }

    /**
     *  https://leetcode.com/discuss/interview-question/356520
     *
     *  Meeting rooms II
     * */
    public static int minNumMeetingRooms(int[][] meetings) {
        if(meetings.length == 0) return 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(
                (a,b) -> a[1] - b[1]
        );
        Arrays.sort(
                meetings, (a,b) -> a[0] - b[0]
        );
        q.add(meetings[0]);
        for(int i = 1; i < meetings.length; i++) {
            int[] earliest = q.poll();
            int[] current = meetings[i];

            if(earliest[1] <= current[0]) {
                earliest[1] = current[1];
            } else {
                q.add(current);
            }
            q.add(earliest);
        }
        return q.size();
    }

    public static boolean canPartition(int[] nums) {
        int l = nums.length;
        if (l < 2) return false;

        int sum = Arrays.stream(nums).sum();
        if(sum % 2 != 0) return false;

        sum = sum / 2;

        boolean[][] cache = new boolean[l + 1][sum + 1];

        for(int i = 0; i <= l; i++) {
            for(int j = 0; j <= sum; j++) {
                if(i == 0 || j == 0) cache[i][j] = false;
                else if(nums[i-1] > j) cache[i][j] = cache[i-1][j];
                else if(nums[i-1]==j) cache[i][j] = true;
                else cache[i][j] = cache[i-1][j] || cache[i-1][j-nums[i-1]];
            }
        }
        return cache[l][sum];
    }

    public static int minDifference(int[] nums) {
        if(nums.length < 5) return 0;

        Arrays.sort(nums);
        int l = nums.length;
        return Math.min(
                Math.min(nums[l - 1] - nums[3], nums[l - 2] - nums[2]),
                Math.min(nums[l - 3] - nums[1], nums[l - 4] - nums[0])
        );
    }
}
