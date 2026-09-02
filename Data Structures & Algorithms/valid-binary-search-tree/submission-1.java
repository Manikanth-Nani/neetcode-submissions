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
    public boolean isValid(TreeNode root, int leftMax, int rightMax){
        if(root == null) return true;

        if(leftMax <= root.val && root.val <= rightMax){

            boolean lst = isValid(root.left, leftMax, root.val - 1);
            if(lst == false) return false;

            boolean rst = isValid(root.right, root.val+1, rightMax);
            if(rst == false) return false;

            return true;
        }
        else{
            return false;
        }
    }
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
