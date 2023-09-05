package org.example.pattern3;

import java.util.List;

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
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);

        System.out.println(isPalindrome(head));


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

    /**
     *  https://leetcode.com/problems/happy-number/
     *
     *  202. Happy number
     * */
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        while(true) {
            slow = findSquareSum(slow);
            fast = findSquareSum(findSquareSum(fast));

            if(slow == fast) break; //found a cycle
        }
        return slow == 1;
    }
    public static int findSquareSum(int n) {
        int result = 0;
        while(n > 0) {
            int digit = n % 10;
            result += Math.pow(digit, 2);
            n = (int) Math.floor(n / 10);
        }
        return result;
    }

    /**
     *  https://leetcode.com/problems/middle-of-the-linked-list/
     *
     *  876. Middle of the Linked List
     * */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     *  https://leetcode.com/problems/palindrome-linked-list/
     *
     *  234. Palindrome Linked list
     * */
     public static boolean isPalindrome(ListNode head) {
         if(head == null) return true;
         ListNode p1 = head;
         ListNode p2 = head;
         ListNode p3 = p1.next;
         ListNode pre = p1;

         while(p2.next != null && p2.next.next != null) {
             p2 = p2.next.next;
             pre = p1;
             p1 = p3;
             p3 = p3.next;
             p1.next = pre;
         }

         if(p2.next == null) {
             p1 = p1.next;
         }

         while(p3 != null) {
             if(p1.val != p3.val) {
                 return false;
             }
             p1 = p1.next;
             p3 = p3.next;
         }
         return true;
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

