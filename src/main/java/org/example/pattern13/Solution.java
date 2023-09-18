package org.example.pattern13;

import java.util.ArrayList;
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

//        System.out.println(
//                findLeastNumOfUniqueInts(
//                        new int[] {2,1,1,3,3,3}, 3)
//        );
//        System.out.println(
//                reorganizeString(
//                        "ababb"
//                )
//        );
        FreqStack stack = new FreqStack();
        stack.push(5);
        stack.push(7);
        stack.push(5);
        stack.push(7);
        stack.push(4);
        stack.push(5);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
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

    /**
     *  https://leetcode.com/problems/reorganize-string/
     *
     *  767. Reorganize String
     * */
    public static String reorganizeString(String s) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        PriorityQueue<Character> pq = new  PriorityQueue<Character>(
                (a,b) -> freqMap.get(b) - freqMap.get(a)
        );

        for(Character c : freqMap.keySet()) {
            pq.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++ ) {
            if(i - 1 < 0 || sb.charAt(i - 1) != pq.peek()) {
                Character tempChar1 = pq.poll();
                sb = sb.append(tempChar1);
                freqMap.replace(tempChar1, freqMap.get(tempChar1) - 1);
                if(freqMap.get(tempChar1) == 0) freqMap.remove(tempChar1);
                else pq.add(tempChar1);
                continue;
            }
            if(pq.size() < 2) return "";
            Character tempChar1 = pq.poll();
            Character tempChar2 = pq.poll();
            sb = sb.append(tempChar2);
            freqMap.replace(tempChar2, freqMap.get(tempChar2) - 1);
            if(freqMap.get(tempChar2) == 0) freqMap.remove(tempChar2);
            else pq.add(tempChar2);
            pq.add(tempChar1);
        }
        return sb.toString();
    }
}

/**
 *  https://leetcode.com/problems/maximum-frequency-stack/
 *
 *  895. Maximum Frequency Stack
 * */
class FreqStack {
    HashMap<Integer, Integer> freqMap;
    ArrayList<Integer> stack;
    PriorityQueue<Integer> pq;

    public FreqStack() {
        freqMap = new HashMap<Integer, Integer>();
        stack = new ArrayList<Integer>();
        pq = new PriorityQueue(
                (a,b) -> freqMap.get(b) - freqMap.get(a) // Collections.reverseOrder();
        );
    }

    public void push(int val) {
        stack.add(val);
        if(freqMap.containsKey(val)) freqMap.replace(val, freqMap.get(val) + 1);
        else freqMap.put(val, 1);
        if(pq.contains(val)) pq.remove(val);
        pq.add(val);
    }

    public int pop() {
        int result = -1;
        ArrayList<Integer> allResults = new ArrayList();
        allResults.add(pq.poll());
        while(freqMap.get(allResults.get(0)) == freqMap.get(pq.peek()) ) {
            allResults.add(pq.poll());
        }

        for(int i = stack.size() - 1; i >= 0; i--) {
            if(!allResults.contains(stack.get(i))) continue;

            freqMap.replace(stack.get(i), freqMap.get(stack.get(i)) - 1);
            pq.remove(stack.get(i));
            if(freqMap.get(stack.get(i)) == 0) {
                freqMap.remove(stack.get(i));
            } else {
                pq.add(stack.get(i));
            }
            for(int j = 0; j < allResults.size(); j++) {
                if(allResults.get(j) != stack.get(i)) {
                    pq.add(allResults.get(j));
                }
            }
            result = stack.remove(stack.lastIndexOf(stack.get(i)));
            break;
        }
        if(result == -1) throw new IllegalArgumentException("something went wrong.");

        return result;
    }
}
