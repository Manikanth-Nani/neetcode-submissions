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

/**
 * Problem: LeetCode 105 - Construct Binary Tree from Preorder and Inorder Traversal
 * 
 * Time Complexity:  O(N^2) in the worst case (skewed tree) because we perform a linear scan 
 *                   to find the root index in the inorder array at every recursion step.
 * Space Complexity: O(N) to store the recursive call stack (worst case for a skewed tree).
 */
class Solution {
    
   
    public TreeNode construct(int[] inorder, int[] preorder, int inSt, int inEnd, int preSt, int preEnd) {

        // Base Case: If the boundaries cross, the current subtree is empty
        if (inSt > inEnd) {
            return null;
        }

        // The first element in the preorder range is always the root of the current subtree
        TreeNode node = new TreeNode(preorder[preSt]);

        // Find the index of the root node in the inorder array.
        // This splits the inorder array into a Left Subtree and a Right Subtree.
        int i;
        for (i = inSt; i <= inEnd; i++) {
            if (inorder[i] == node.val) {
                break; // Root index found at position 'i'
            }
        }

        // Calculate the size (number of nodes) of the Left Subtree
        int lst = i - inSt;

        // Recursively build the Left Subtree
        // Inorder range: From 'inSt' up to 'i-1'
        // Preorder range: Starts right after root ('preSt+1') and spans 'lst' elements ('preSt + lst')
        node.left = construct(inorder, preorder, inSt, i - 1, preSt + 1, preSt + lst);
        
        // Recursively build the Right Subtree
        // Inorder range: From 'i+1' up to 'inEnd'
        // Preorder range: Starts after the left subtree ('preSt + lst + 1') up to 'preEnd'
        node.right = construct(inorder, preorder, i + 1, inEnd, preSt + lst + 1, preEnd);

        // Return the completely constructed root node
        return node;
    }

    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Initialize the recursive construction with the full boundaries of both arrays
        return construct(inorder, preorder, 0, inorder.length - 1, 0, preorder.length - 1);
    }
}
