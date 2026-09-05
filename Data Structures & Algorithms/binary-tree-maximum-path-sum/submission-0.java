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
    
    public int pathSum(TreeNode root, int[] maxi){
        if(root == null) return 0;

        // Recursively get the max path sum from left and right children.
        // If a child's path sum is negative, ignore it by taking 0.
        int lstSum = Math.max(0, pathSum(root.left, maxi));
        int rstSum = Math.max(0, pathSum(root.right, maxi));

        // Update the global maximum if the path turning at the current root 
        // (left branch + right branch + root value) is greater than our current max.
        maxi[0] = Math.max(maxi[0], lstSum + rstSum + root.val);

        // Return the maximum single branch (either left or right) plus the root's value
        // to the parent node, maintaining a valid, unbranched path sequence.
        return Math.max(lstSum, rstSum) + root.val;
    }

    public int maxPathSum(TreeNode root) {
        // Use a 1-element array as a pass-by-reference reference to track global maximum
        int[] maxi = new int[1];
        maxi[0] = Integer.MIN_VALUE;
        
        pathSum(root, maxi);
        return maxi[0];
    }
}
