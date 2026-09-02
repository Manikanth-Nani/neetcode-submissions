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
   
    public boolean validate(TreeNode root, TreeNode[] prev){
        // Base Case: An empty tree/node is technically a valid BST
        if(root == null) return true;

        // Step 1: Traverse and validate the left subtree
        boolean left = validate(root.left, prev);
        // If the left subtree fails the BST condition, propagate the failure upwards immediately
        if(left == false) return false;

        // Step 2: Validate the current node against the previously visited node
        // In a valid BST, the current node's value MUST be strictly greater than the previous node's value.
        // If it is smaller or equal, the tree violates the BST property.
        if(prev[0] != null && prev[0].val >= root.val) return false;
        
        // Update 'prev' to point to the current node before moving to the right subtree
        prev[0] = root;

        // Step 3: Traverse and validate the right subtree
        boolean right = validate(root.right, prev);
        // If the right subtree fails the BST condition, return false
        if(right == false) return false;
        
        // If both subtrees and the current node satisfy the BST condition, the current subtree is valid
        return true;
    }

    /**
     * Main method to validate if a binary tree is a valid Binary Search Tree.
     * Time Complexity: O(N) - Every node in the tree is visited exactly once.
     * Space Complexity: O(H) - Where H is the height of the tree, representing the recursion stack frames.
     */
    public boolean isValidBST(TreeNode root) {
       
        TreeNode[] prev = new TreeNode[1];
        return validate(root, prev);
    }
}
