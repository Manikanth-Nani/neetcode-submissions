/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int maxDepth(TreeNode root) {
        // Base case: An empty tree or reaching past a leaf node has 0 nodes.
        if (root == null) {
            return 0;
        }
        
        // Recursively find the depth of the left subtree.
        int leftDepth = maxDepth(root.left);
        
        // Recursively find the depth of the right subtree.
        int rightDepth = maxDepth(root.right);

        // Take the larger depth between the subtrees and add 1 to count the current node.
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
