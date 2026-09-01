class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        StringBuilder rootStr = new StringBuilder();
        StringBuilder subRootStr = new StringBuilder();
        
        // 1. Serialize both trees
        serialize(root, rootStr);
        serialize(subRoot, subRootStr);
        
        // 2. Check if subRootStr is a substring of rootStr
        return rootStr.toString().contains(subRootStr.toString());
    }
    
    private void serialize(TreeNode node, StringBuilder sb) {
        // Base case: Use a unique marker for null nodes
        if (node == null) {
            sb.append(",#");
            return;
        }
        
        // Use a delimiter (like a comma) to prevent value blending (e.g., separating 12 from 1,2)
        sb.append(",").append(node.val);
        
        // Pre-order traversal
        serialize(node.left, sb);
        serialize(node.right, sb);
    }
}
