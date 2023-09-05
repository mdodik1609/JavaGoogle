package org.example.pattern3;

/**
 * Pattern 3: Fast & Slow pointers
 *
 *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2003%3A%20Fast%20%26%20Slow%20pointers.md
 *
 * */
public class Solution {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        System.out.println(hasCycle(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println(hasCycle(head));


    }


    /**
     *  https://leetcode.com/problems/linked-list-cycle/
     *
     *  141. Linked List Cycle
     * */
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast) return true;
        }
        return false;
    }

    /**
     *  https://leetcode.com/problems/linked-list-cycle-ii/description/
     *
     *  142. Linked List Cycle II
     * */
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode cache = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if(slow == fast) {
                while (cache != slow) {
                    cache = cache.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}



class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
   }
}

