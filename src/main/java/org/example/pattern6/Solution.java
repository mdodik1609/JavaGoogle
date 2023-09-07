package org.example.pattern6;

public class Solution {
    /**
     *      https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2006:%20In-place%20Reversal%20of%20a%20LinkedList.md
     *
     *      Pattern 6: In-place reversal of a LinkedList
     * */
    public static void main(String[] args){

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
