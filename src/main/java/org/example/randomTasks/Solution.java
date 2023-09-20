package org.example.randomTasks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

        System.out.println(
                maxRoom(
                        new String[]{
                                "+1A", "+3E", "-1A", "+4F", "+1A", "-3E"
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
}
