package org.example.maxTaxi;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        System.out.println(maxTaxiEarnings(5, new int[][]{{2,5,4}, {1,5,1}}));
        System.out.println(maxTaxiEarnings(5, new int[][]{{1,6,1},{3,10,2},{10,12,3},{11,12,2},{12,15,2},{13,18,1}}));
    }


    public static long maxTaxiEarnings(int n, int[][] rides){
        //sort by starting point
        Arrays.sort(rides, (i,j) -> i[0] - j[0]);
        long result = 0L;

        PriorityQueue<long[]> q = new PriorityQueue<>((i, j) -> Long.compare(i[0], j[0]));

        for(int i = 0; i < rides.length; i++) {
            int startPoint = rides[i][0];
            int endPoint = rides[i][1];
            long earnings = endPoint - startPoint + rides[i][2];

            while (!q.isEmpty() && startPoint >= q.peek()[0]) {
                result = Math.max(result, q.peek()[1]);
                q.poll();
            }
            q.offer(new long[] {endPoint, result + earnings});
        }

        while(!q.isEmpty()) {
            result = Math.max(result, q.poll()[1]);
        }

        return result;
    }
}
