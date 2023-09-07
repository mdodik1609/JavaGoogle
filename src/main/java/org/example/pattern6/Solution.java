package org.example.pattern6;

public class Solution {
    /**
     *      https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2006:%20In-place%20Reversal%20of%20a%20LinkedList.md
     *
     *      Pattern 6: In-place reversal of a LinkedList
     * */
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);


//        System.out.println(
//                reverseBetween(
//                        head, 2, 4
//                )
//        );

        reverseKGroup(head, 3);
    }

    /**
     *  https://leetcode.com/problems/reverse-linked-list/
     *
     *  206. Reverse Linked list
     *
     * */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while(current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    /**
     *  https://leetcode.com/problems/reverse-linked-list-ii/
     *
     *  92. Reverse Linked List II
     * */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode temp = new ListNode(-1);
        ListNode prev = temp;
        temp.next = head;

        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode cur = prev.next;

        for (int i = 0; i < right - left; i++) {
            ListNode ptr = prev.next;
            prev.next = cur.next;
            cur.next = cur.next.next;
            prev.next.next = ptr;
        }

        return temp.next;
    }

    /**
     *  https://leetcode.com/problems/reverse-nodes-in-k-group/
     *
     *  25. Reverse Nodes in k-Group
     * */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode prev = null;
        ListNode current = head;

        mainLoop: while(true) {

            ListNode lastNodeOfPreviousPart = prev;
            ListNode lastNodeOfSubList = current;

            ListNode next = null;
            int i = 0;

            while(current != null && i < k) {
                 next = current.next;
                 current.next = prev;
                 prev = current;
                 current = next;
                 i++;
            }

            if(lastNodeOfPreviousPart != null) {
                lastNodeOfPreviousPart.next = prev;
            } else {
                head = prev;
            }

            lastNodeOfSubList.next = current;
            prev = lastNodeOfSubList;

            ListNode temp = current;
            for(int j = 0; j < k; j++) {
                if(temp == null) break mainLoop;
                temp = temp.next;
            }
        }
        return head;
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int value) {
        this.val = value;
    }
    ListNode(int value, ListNode next) {
        this.val = value;
        this.next = next;
    }
}
