/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    /**
     * Finds the Lowest Common Ancestor (LCA) of two nodes in a BST.
     * Strategy: Leverage BST properties to find the node where p and q split paths.
     * Time Complexity: O(H) - where H is the height of the tree (O(log N) balanced, O(N) worst case).
     * Space Complexity: O(H) - due to the recursive call stack.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if root is null, no ancestor can be found
        if (root == null) return null;

        // Case 1: Both p and q are strictly greater than root.
        // The LCA must reside entirely in the right subtree.
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // Case 2: Both p and q are strictly less than root.
        // The LCA must reside entirely in the left subtree.
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // Case 3: We found the split point! 
        // This happens when root matches p or q, or root is sandwiched between them.
        return root;
    }
}
