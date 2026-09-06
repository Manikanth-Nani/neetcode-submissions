
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
    
   
    private Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Step 1: Pre-populate the map with value -> index mapping from the inorder array.
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // Step 2: Kick off the recursive construction with the full boundaries
        return construct(inorder, preorder, 0, inorder.length - 1, 0, preorder.length - 1);
    }

    private TreeNode construct(int[] inorder, int[] preorder, int inSt, int inEnd, int preSt, int preEnd) {

        // Base Case: If the boundaries cross, the current subtree is empty
        if (inSt > inEnd) {
            return null;
        }

        // The first element in the preorder range is always the root of the current subtree
        TreeNode node = new TreeNode(preorder[preSt]);

        // O(1) Lookup: Directly fetch the root's index in the inorder array using our map
        int i = inorderMap.get(node.val);

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
}
