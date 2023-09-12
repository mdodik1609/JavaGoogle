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
//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(4);
//        root.right = new TreeNode(8);
//        root.left.left = new TreeNode(11);
//        root.left.left.left = new TreeNode(7);
//        root.left.left.right = new TreeNode(2);
//        root.right.left = new TreeNode(13);
//        root.right.right = new TreeNode(4);
//        root.right.right.left = new TreeNode(5);
//        root.right.right.right = new TreeNode(1);
//        pathSum(root, 22);


        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);
        System.out.println(sumNumbers(root));
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

    /**
     *  https://leetcode.com/problems/sum-root-to-leaf-numbers/
     *
     *  129. Sum Root to Leaf Numbers
     * */
    public static int sumNumbers(TreeNode root) {
        return findAllPathsSum(root, 0);
    }

    public static int findAllPathsSum(TreeNode currentNode, int result) {
        if(currentNode == null) return 0;
        result = 10 * result + currentNode.val;
        if(isLeaf(currentNode)) {
            return result;
        }
        return findAllPathsSum(currentNode.left, result) + findAllPathsSum(currentNode.right, result);
    }

    /**
     *  https://leetcode.com/problems/path-sum-iii/
     *
     *  437. Path Sum III
     * */
    public static int pathSumIII(TreeNode root, int targetSum) {
        return countPathSum(root, targetSum, new ArrayList<Integer>());
    }

    public static int countPathSum(TreeNode node, int targetSum, List<Integer> currentPath) {
        if(node == null) return 0;
        currentPath.add(node.val);
        int result = 0;
        int tempSum = 0;
        for(int i = currentPath.size() - 1; i >= 0; i--) {
            tempSum += currentPath.get(i);
            if(tempSum == targetSum) result++;
        }

        if(!isLeaf(node)) {
            result += countPathSum(node.left, targetSum, currentPath) + countPathSum(node.right, targetSum, currentPath);
        }

        currentPath.remove(currentPath.size() - 1);
        return result;
    }

    /**
     *  https://leetcode.com/problems/diameter-of-binary-tree/
     *
     *  543. Diameter of Binary Tree
     * */
    public int diameterOfBinaryTree(TreeNode root) {
        int[] result = new int[1];
        diameterRecursive(root, result);
        return result[0];
    }

    public static int diameterRecursive(TreeNode node, int[] result) {
        if(node == null) return 0;
        int leftHeight = diameterRecursive(node.left, result);
        int rightHeight = diameterRecursive(node.right, result);
        result[0] = Math.max(result[0], leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
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
