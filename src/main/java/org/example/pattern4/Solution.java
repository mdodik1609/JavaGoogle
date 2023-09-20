package org.example.pattern4;

import javax.swing.*;
import java.util.*;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2004%20%3A%20Merge%20Intervals.md
     *
     *  Pattern 4 : Merge Intervals
     *
     * */
    public static void main(String[] args) {
//        System.out.println(merge(new int[][]{
//                {1,3},{2,6},{8,10},{15,18}
//        }));
//        System.out.println(merge(new int[][]{
//                {1,4},{0,4}
//        }));

//        System.out.println(
//                insert(
//                        new int[][]{
//                                {1,3}, {6,9}
//                        },
//                        new int[]{2,5}
//                )
//        );

//        System.out.println(
//                minNumOfRooms(
//                        new int[][]{
//                                {1,4},{2,5},{7,9}
//                        }
//                )
//        );
//        System.out.println(minNumOfRooms(
//                new int[][]{
//                        {1,4}
//                }
//        ));
//        System.out.println(minNumOfRooms(
//                new int[][]{
//                        {4,5},{2,3},{2,4},{3,5}
//                }
//        ));

//        System.out.println(findMaxCPULoad(
//                new int[][] {
//                        {1,4,3},{2,5,4},{7,9,6}
//                }
//        ));
//        System.out.println(findMaxCPULoad(
//                new int[][] {
//                        {6,7,10},{2,4,11},{8,12,15}
//                }
//        ));
        System.out.println(maxCPULoadPQ(
                new int[][] {
                        {1,4,2},{2,4,1},{3,6,5}
                }
        ));
    }

    /**
     *  https://leetcode.com/problems/merge-intervals/
     *
     *  54. Merge Intervals
     *
     * */
    public static int[][] merge(int[][] intervals) {
         Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    /**
     *  https://leetcode.com/problems/insert-interval/
     *
     *  57. Insert Interval
     * */
    public static int[][] insert(int[][] intervals, int[] interval){
        int length = intervals.length;
        int[][] result = new int[intervals.length + 1][2];

        for(int i = 0; i < intervals.length; i++) {
            result[i] = intervals[i];
        }
        result[intervals.length] = interval;

        return merge(result);
    }

    public static int[][] insertOptimized(int[][] intervals, int[] interval) {
        ArrayList<int[]> result = new ArrayList<>();
        int i = 0;

        while(i < intervals.length && intervals[i][1] < interval[0]) {
            result.add(intervals[i]);
            i++;
        }
        while(i < intervals.length && intervals[i][0] <= interval[1]) {
            interval[0] = Math.min(intervals[i][0], interval[0]);
            interval[1] = Math.max(intervals[i][1], interval[1]);
            i++;
        }
        result.add(interval);
        while(i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }

    /**
     *  https://leetcode.com/problems/meeting-rooms-ii/
     *
     *  Meeting rooms II
     * */
    public static int minNumOfRooms(int[][] meetings) {
        if(meetings.length == 0) return 0;
        //ArrayList<ArrayList<int[]>> result = new ArrayList<>();

        ArrayList<ArrayList<Interval>> result2 = new ArrayList<>();
        result2.add(new ArrayList<>(Collections.singleton(
            new Interval(meetings[0])
        )));

        //result.add(new ArrayList<>(Collections.singleton(meetings[0])));

        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        // place meeting in a room if it is available
        // place an interval to one of arrays where there will not be cross-sections
        for(int i = 1; i < meetings.length; i++) {

            for(int j = 0; j < result2.size(); j++) {
                int finalI = i;
                if(result2.get(j).stream().filter(
                        it ->  it.checkOverlap(new Interval(meetings[finalI]))  //(meetings[finalI][0] > it.start && meetings[finalI][1] < it.end) || (meetings[finalI][0] > it.start && meetings[finalI][1] < it.end)
                ).count() == 0) {
                    var temp = result2.get(j);
                    result2.remove(temp);
                    temp.add(new Interval(meetings[finalI]));
                    result2.add(temp);
                } else {
                    result2.add(new ArrayList<>(Collections.singleton(
                            new Interval(meetings[finalI])
                    )));
                }
            }
        }

        return result2.size();
    }

    /**
     *  https://github.com/mdodik1609/JavaGoogle/commit/edad441152178f51fc5994f209b3ee30bd5b082a
     *
     *  Maximum CPU load
     *
     *  similar task: Car pooling -> https://leetcode.com/problems/car-pooling/
     * */
    public static int findMaxCPULoad(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        ArrayList<int[]> jobs = new ArrayList<>(Arrays.stream(intervals).toList());

        for (int i = 1; i < jobs.size(); i++) {
            int[] current = jobs.get(i);
            int[] previous = jobs.get(i - 1);
            if (current[0] < previous[1]) {
                jobs.add(i ,new int[]{
                        previous[0], current[1], current[2] + previous[2]
                });
                jobs.remove(i + 1);
                jobs.remove(i - 1);
                i--;
            }
        }

        return jobs.stream().mapToInt(
                it -> it[2]
        ).max().getAsInt();
    }

    public static int maxCPULoadPQ(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        PriorityQueue<int[]> q = new PriorityQueue<>(
                (a,b) -> a[1] - b[1]
        );
        int maxLoad = 0;
        int currentLoad = 0;

        for(int[] interval : intervals) {
            while(!q.isEmpty() && interval[0] > q.peek()[1]) {
                currentLoad = currentLoad - q.poll()[2];
            }

            q.add(interval);
            currentLoad = currentLoad + interval[2];
            maxLoad = Math.max(currentLoad, maxLoad);
        }
        return maxLoad;
    }

}

class Interval{
    public int start;
    public int end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public Interval(int[] field) {
        this.start = field[0];
        this.end = field[1];
    }

    public boolean checkOverlap(Interval i2){
        if(this.start > i2.start && this.end < i2.end) return true;
        if(this.end > i2.start && this.end < i2.end) return true;
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.start + ", " + this.end + "]";
    }

    public boolean equals(Interval interval) {
        return this.start == interval.start && this.end == interval.end;
    }
}
