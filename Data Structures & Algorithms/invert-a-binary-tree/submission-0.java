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
    /**
     * Inverts a binary tree by swapping the left and right subtrees of every node.
     * This implementation uses a post-order bottom-up recursive approach.
     * 
     * @param root The root node of the binary tree.
     * @return The root node of the inverted binary tree.
     */
    public TreeNode invertTree(TreeNode root) {
        // Base case: If the current node is null, there is nothing to invert.
        if (root == null) {
            return null;
        }

        // Recursively invert the left subtree.
        TreeNode left = invertTree(root.left);
        
        // Recursively invert the right subtree.
        TreeNode right = invertTree(root.right);

        // Swap the inverted subtrees for the current root node.
       
        TreeNode temp = left;
        root.left = right;
        root.right = temp; 

        return root;
    }
}
