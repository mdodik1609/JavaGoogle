package org.example.pattern4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

        System.out.println(
                insert(
                        new int[][]{
                                {1,3}, {6,9}
                        },
                        new int[]{2,5}
                )
        );
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

}

class Interval{
    int start;
    int end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + this.start + ", " + this.end + "]";
    }
}
