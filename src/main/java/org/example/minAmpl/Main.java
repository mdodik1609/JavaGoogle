package org.example.minAmpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main (String[] args) {
    /**
     * https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
     *
     * */

        System.out.println(minDifference(new int[]{5, 3, 2, 4}));
        System.out.println(minDifference(new int[]{0,1,5,10,14}));
        System.out.println(minDifference(new int[]{0,1,1,4,6,6,6}));
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
