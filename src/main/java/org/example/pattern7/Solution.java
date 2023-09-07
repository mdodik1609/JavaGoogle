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
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);


        levelOrder(treeNode);
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
