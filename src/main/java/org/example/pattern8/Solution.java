package org.example.pattern8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    /**
     * https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2008:Tree%20Depth%20First%20Search.md
     *
     * Pattern 8: Tree Depth First Search (DFS)
     * */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        pathSum(root, 22);
    }
    /**
     *  https://leetcode.com/problems/path-sum/
     *
     *  112. Path sum
     * */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;

        int val = root.val;

        if(val == targetSum && isLeaf(root)) return true;
        return (hasPathSum(root.left, targetSum - val) || hasPathSum(root.right, targetSum - val));
    }

    public static boolean isLeaf(TreeNode node) {
        if(node.left == null && node.right == null) return true;
        return false;
    }

    /**
     *  https://leetcode.com/problems/path-sum-ii/
     *
     *  113. Path sum II
     * */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();

        result = findAllPaths(root, targetSum, new ArrayList<>(), result);

        return result;
    }
    public static List<List<Integer>> findAllPaths(TreeNode currentNode, int sum, List<Integer> currentPath, List<List<Integer>> allPaths) {
        if(currentNode == null) return allPaths;
        currentPath.add(currentNode.val);
        if(currentNode.val == sum && isLeaf(currentNode)) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            findAllPaths(currentNode.left, sum - currentNode.val, currentPath, allPaths);
            findAllPaths(currentNode.right, sum - currentNode.val, currentPath, allPaths);
        }
        currentPath.remove(currentPath.size() - 1);
        return allPaths;
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
