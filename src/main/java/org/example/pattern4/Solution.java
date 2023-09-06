package org.example.pattern4;

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

        System.out.println(
                minNumOfRooms(
                        new int[][]{
                                {1,4},{2,5},{7,9}
                        }
                )
        );
        System.out.println(minNumOfRooms(
                new int[][]{
                        {1,4}
                }
        ));
        System.out.println(minNumOfRooms(
                new int[][]{
                        {4,5},{2,3},{2,4},{3,5}
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
