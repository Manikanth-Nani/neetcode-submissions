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
     * Finds the kth smallest element in a Binary Search Tree (BST).
     * Strategy: Morris Inorder Traversal.
     * Time Complexity: O(N) - Each node is visited at most 3 times.
     * Space Complexity: O(1) - Iterative approach using temporary pointers (no stack/recursion).
     */
    public int kthSmallest(TreeNode root, int k) {
        int count = 0; // Tracks the number of nodes processed in inorder sequence
        TreeNode curr = root; // Iterator node starting at the root
        
        while (curr != null) {
            // Case 1: If there is no left subtree, process the current node
            if (curr.left == null) {
                count++;
                // If the current node is the kth smallest, return its value
                if (count == k) return curr.val;
                
                // Move to the right subtree
                curr = curr.right;
            } 
            // Case 2: If a left subtree exists, we find the inorder predecessor
            else {
                // The inorder predecessor is the rightmost node in the left subtree
                TreeNode pre = curr.left;
                
                // Traverse right until we hit a leaf or a temporary loop pointer back to 'curr'
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }
                
                // Sub-case A: Threading the tree
                // If the predecessor's right link is null, create a temporary bridge to 'curr'
                if (pre.right == null) {
                    pre.right = curr; 
                    curr = curr.left; // Move deeper into the left subtree
                }
                
                // Sub-case B: Restoring the tree structure
                // If the bridge already exists, it means we have fully traversed the left subtree
                if (pre.right == curr) {
                    pre.right = null; // Revert the change to clean up the tree structure
                    count++; // Formally "visit" the current node
                    
                    // Check if this node is our target element
                    if (count == k) return curr.val;
                    
                    // Move to the right subtree since the left side and root are finished
                    curr = curr.right;
                }
            }
        }
        
        return -1; // Fallback value if k is out of bounds
    }
}
