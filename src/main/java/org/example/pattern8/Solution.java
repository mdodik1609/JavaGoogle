package org.example.pattern8;

public class Solution {
    /**
     * https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2008:Tree%20Depth%20First%20Search.md
     *
     * Pattern 8: Tree Depth First Search (DFS)
     * */
    public static void main(String[] args) {

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
