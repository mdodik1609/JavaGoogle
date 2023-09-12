package org.example.pattern9;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%F0%9F%99%83%20Pattern%2009%3A%20Two%20Heaps.md
     *
     *  Pattern 9: Two Heaps
     *
     * */
    public static void main(String[] args) {
//        MedianFinder finder = new MedianFinder();
//        finder.addNum(1);
//        finder.addNum(2);
//        finder.addNum(3);
//        System.out.println(finder.findMedian());

        System.out.println(medianSlidingWindow(
                new int[]{1,3,-1,-3,5,3,6,7}, 3
        ));
    }

    /**
     *  https://leetcode.com/problems/sliding-window-median/
     *
     *  480. Sliding Window Median
     * */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) return new double[]{};
        double[] result = new double[nums.length - k + 1];
        int j = 0;
        MedianFinder medianFinder = new MedianFinder();
        for(int i = 0; i < k - 1; i++) {
            medianFinder.addNum(nums[i]);
        }
        for(int i = k - 1; i < nums.length; i++) {
            medianFinder.addNum(nums[i]);
            result[j]= medianFinder.findMedian();
            j++;
            medianFinder.removeNumber(nums[i - k + 1]);
        }
        return result;
    }
}

/**
 *  https://leetcode.com/problems/find-median-from-data-stream/
 *
 *  295. Find Median from Data Stream
 * */
class MedianFinder {

    PriorityQueue<Integer> maxStack = new PriorityQueue<>();
    PriorityQueue<Integer> minStack = new PriorityQueue<>(Collections.reverseOrder());

    public MedianFinder() {

    }

    public void balanceStack() {
        if(maxStack.size() < minStack.size()) {
            maxStack.add(minStack.poll());
        }
    }

    public void removeNumber(int num) {
        if(!maxStack.remove(num)){
            minStack.remove(num);
        } else {
            balanceStack();
        }
    }

    public void addNum(int num) {
        maxStack.add(num);
        minStack.add(maxStack.poll());
        if(maxStack.size() < minStack.size()) {
            maxStack.add(minStack.poll());
        }
     }

    public double findMedian() {
        if(maxStack.size() != minStack.size()) {
            return maxStack.peek();
        } else {
            return ( (double) maxStack.peek() + minStack.peek()) / 2;
        }
    }
}