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
    
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // Base Case 1: Both nodes are null, meaning we reached the end of identical paths.
        if (p == null && q == null) return true;
        
        // Base Case 2: One node is null and the other isn't, meaning the structures mismatch.
        if (p == null || q == null) return false;

        // Value Check: If current nodes have different values, the trees are not identical.
        if (p.val != q.val) return false;
        
        // Structural Check: Recursively check if the left subtrees match.
        else if (isSameTree(p.left, q.left) == false) return false;
        
        // Structural Check: Recursively check if the right subtrees match.
        else if (isSameTree(p.right, q.right) == false) return false;

        // If all checks pass, the trees are identical up to this point.
        return true;
    }
}
