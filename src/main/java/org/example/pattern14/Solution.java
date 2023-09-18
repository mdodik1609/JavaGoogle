package org.example.pattern14;

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

}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
