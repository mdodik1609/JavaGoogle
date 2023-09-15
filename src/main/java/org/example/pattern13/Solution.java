package org.example.pattern13;

import java.util.PriorityQueue;

public class Solution {
    /**
     * https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2013%3A%20Top%20'K'%20Elements.md
     *
     *  Pattern 13: Top 'K' Elements
     * */
    public static void main(String[] args) {
        System.out.println(findKthLargest(
                new int[]{2,1,3,4,7,5,8,9,6}, 5
        ));
    }
    /**
     *  https://leetcode.com/problems/kth-largest-element-in-an-array/
     *
     *  215. Kth largest element in an array
     * */
    public static int findKthLargest(int[] nums, int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i = 0; i < k; i++) {
            minHeap.add(nums[i]);
        }

        for(int i = k; i < nums.length; i++) {
            if(nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }
        return minHeap.peek();
    }
}
