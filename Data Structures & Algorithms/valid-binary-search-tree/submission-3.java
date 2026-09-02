class Solution {
    // Use long parameters to completely eliminate Integer overflow/underflow bugs
    public boolean isValid(TreeNode root, long minBound, long maxBound){
        if(root == null) return true;

        // A node's value must be STRICTLY between its minimum and maximum possible boundaries
        if(root.val > minBound && root.val < maxBound){

            // Left child must be smaller than the current root's value
            boolean lst = isValid(root.left, minBound, root.val);
            if(lst == false) return false;

            // Right child must be larger than the current root's value
            boolean rst = isValid(root.right, root.val, maxBound);
            if(rst == false) return false;

            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean isValidBST(TreeNode root) {
        // Pass standard 64-bit long limits so 32-bit Integer boundaries don't break the code
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
