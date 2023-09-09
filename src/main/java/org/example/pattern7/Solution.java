package org.example.pattern7;

import java.util.*;
import java.util.stream.Stream;

public class Solution {
    /**
     *  https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2007:%20Tree%20Breadth%20First%20Search.md
     *
     *
     *  Pattern 7: Tree Breadth First Search
     * */
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.right.right = new TreeNode(5);


        //levelOrder(treeNode);
        zigzagLevelOrder(treeNode);
    }
    /**
     *  https://leetcode.com/problems/binary-tree-level-order-traversal/
     *
     *  102. Binary tree level order traversal
     *
     * */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();

        ArrayDeque<TreeNode> deque = new ArrayDeque<>();

        deque.push(root);

        while(!deque.isEmpty()) {

            List<TreeNode> listNodes = new ArrayList<>();
            while(!deque.isEmpty()) {
                listNodes.add(deque.pollLast());
            }

            result.add(
                    listNodes.stream().map(it -> it.val).toList()
            );

            for(int i = 0; i < listNodes.size(); i++) {
                TreeNode current = listNodes.get(i);
                if(current.left != null) deque.push(current.left);
                if(current.right != null) deque.push(current.right);
            }

        }

        return result;
    }

    /**
     *  https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
     *
     *  107. Binary Tree Level Order Traversal II
     * */
    private static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = levelOrder(root);
        Collections.reverse(result);
        return result;
    }

    /**
     *  https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
     *
     *  103. Binary Tree Zigzag Level Order Traversal
     * */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) {
            return Collections.emptyList();
        }
        boolean zigzagOrder = false;
        List<List<Integer>> result = new ArrayList<>();

        ArrayDeque<TreeNode> deque = new ArrayDeque<>();

        deque.push(root);

        while(!deque.isEmpty()) {

            List<TreeNode> listNodes = new ArrayList<>();
            while(!deque.isEmpty()) {
                if(zigzagOrder) {
                    listNodes.add(deque.pollFirst());
                } else {
                    listNodes.add(deque.pollLast());
                }

            }

            result.add(
                    listNodes.stream().map(it -> it.val).toList()
            );
            if(zigzagOrder) Collections.reverse(listNodes);

            for(int i = 0; i < listNodes.size(); i++) {
                TreeNode current = listNodes.get(i);
                if(current.left != null) deque.push(current.left);
                if(current.right != null) deque.push(current.right);
            }
            zigzagOrder = !zigzagOrder;
        }
        return result;
    }

    /**
     *  https://leetcode.com/problems/average-of-levels-in-binary-tree/
     *
     *  637. Average of Levels in Binary Tree
     * */
    public static  List<Double> averageOfLevels(TreeNode root){
        ArrayList<Double> result = new ArrayList<>();

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.push(root);

        while(!deque.isEmpty()) {
            int i = 0;

            List<TreeNode> listNodes = new ArrayList<>();
            while (!deque.isEmpty()) {
                listNodes.add(deque.pollFirst());
                i++;
            }

            long sum = 0;
            for(int j = 0; j < listNodes.size(); j++) {
                sum += listNodes.get(j).val;
            }

            result.add((double) sum / i);

            for(int j = 0; j < listNodes.size(); j++) {
                TreeNode current = listNodes.get(j);
                if(current.left != null) deque.push(current.left);
                if(current.right != null) deque.push(current.right);
            }

        }

        return result;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
      }
 }
