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
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        finder.addNum(3);
        System.out.println(finder.findMedian());
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