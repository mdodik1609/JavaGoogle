package org.example.pattern13;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution {
    /**
     * https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2013%3A%20Top%20'K'%20Elements.md
     *
     *  Pattern 13: Top 'K' Elements
     * */
    public static void main(String[] args) {
//        System.out.println(findKthLargest(
//                new int[]{2,1,3,4,7,5,8,9,6}, 5
//        ));

//        System.out.println(
//                frequencySort("dinamooo")
//        );

        System.out.println(
                findLeastNumOfUniqueInts(
                        new int[] {2,1,1,3,3,3}, 3)
        );
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
    /**
     *  https://leetcode.com/problems/k-closest-points-to-origin/
     *
     *  973. K closest points to origin
     * */
    public static int[][] kClosest(int[][] points, int k){
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for(int i = 0; i < points.length; i++){
            int sum = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            minHeap.add(new int[] {i,sum});
        }

        int[][] res = new int[k][];
        while(k > 0){
            res[k-1] = points[minHeap.poll()[0]];
            k--;
        }
        return res;
    }
    /**
     *  https://leetcode.com/problems/top-k-frequent-elements/
     *
     *  347. Top K frequent elements
     * */
    public static int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> countMap = new HashMap<>();
        for(int i : nums){
            countMap.put(i, countMap.getOrDefault(i, 0)+1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                k, (a,b) -> countMap.get(b) - countMap.get(a)
        );

        int[] result = new int[k];

        for(int i : countMap.keySet()) {
            maxHeap.add(i);
        }
        for(int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }
    /**
     *  https://leetcode.com/problems/sort-characters-by-frequency/
     *
     *  451. Sort characters by frequency
     * */
    public static String frequencySort(String s) {
        HashMap<Character, Integer> frequencyCharMap = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            frequencyCharMap.put(s.charAt(i), frequencyCharMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<Character>(
                (a,b) -> frequencyCharMap.get(b) - frequencyCharMap.get(a)
        );

        for(Character i : frequencyCharMap.keySet()) {
            maxHeap.add(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < frequencyCharMap.size(); i++) {
            char c = maxHeap.poll();
            int fChar = frequencyCharMap.get(c);
            for(int j = 0; j < fChar; j++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
     *
     *  1481. Least Number of Unique Integers after K Removals
     * */
    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }

        PriorityQueue<Integer> pq = new  PriorityQueue<Integer>(
                (a,b) -> freqMap.get(a) - freqMap.get(b)
        );

        for(int i : freqMap.keySet()) {
            pq.add(i);
        }

        for(int i = 0; i < k; i++ ) {
            int tempNum = pq.poll();

            freqMap.replace(tempNum, freqMap.get(tempNum) - 1);
            if(freqMap.get(tempNum) == 0) freqMap.remove(tempNum);
            else pq.add(tempNum);
        }

        return freqMap.size();
    }
}
