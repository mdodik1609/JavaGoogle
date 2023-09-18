package org.example.pattern14;

import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    /**
     * https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2014%3A%20K-way%20merge.md
     *
     *  Pattern 14: K-way merge
     * */
    public static void main(String[] args) {

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(4);

        System.out.println(
                mergeKLists(
                        new ListNode[] {l1, l2, l3}
                )
        );

    }
    /**
     * https://leetcode.com/problems/merge-k-sorted-lists/
     *
     *  23. Merge k Sorted Lists
     * */
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                (a,b) -> a.val - b.val
        );

        for(ListNode node : lists) {
            if(node == null) continue;
            pq.add(node);
        }

        ListNode result = new ListNode();
        ListNode finalRes = result;

        while(!pq.isEmpty()) {
            ListNode temp = pq.poll();
            result.next = temp;
            result = result.next;
            if(temp.next == null) continue;
            pq.add(temp.next);
        }

        return  finalRes.next;
    }

    /**
     *  https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
     *
     *  632. Smallest Range Covering Elements from K Lists
     * */
    public int[] smallestRange(List<List<Integer>> nums) {
        if(nums == null || nums.size() == 0) return new int[0];

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                (a,b) -> a[2] - b[2]
        );

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            pq.add(new int[]{i, 0, val});
            min = Math.min(min, val);
            max = Math.max(max, val);
        }

        int start = min;
        int end = max;
        int minRange = end - start;
        while(pq.size() == nums.size()) {
            int[] q = pq.poll();
            int row = q[0];
            int col = q[1];
            int val = q[2];

            if(max - val < minRange) {
                minRange = max - val;
                min = val;
                start = min;
                end = max;
            }

            if(col + 1 < nums.get(row).size()) {
                int newVal = nums.get(row).get(col + 1);
                pq.add(new int[]{row, col + 1, newVal});
                max = Math.max(max, newVal);
            }
        }
        return new int[]{start, end};
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
