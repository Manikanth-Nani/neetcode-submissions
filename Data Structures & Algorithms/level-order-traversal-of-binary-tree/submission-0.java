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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // Base case: If the tree is empty, return an empty list immediately.
        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        // Initialize the queue with the root node to start the traversal.
        q.add(root);

        // Continue processing as long as there are nodes left to visit in the queue.
        while (!q.isEmpty()) {
            // Snapshot the current queue size. This represents exactly how many 
            // nodes belong to the current horizontal level of the tree.
            int sz = q.size();
            List<Integer> level = new ArrayList<>();
            
            // Iterate exactly 'sz' times to process only the nodes of the current level.
            for (int i = 1; i <= sz; i++) {
                // Remove the node at the front of the queue.
                TreeNode rem = q.remove();
                
                // Store its value in the current level's list.
                level.add(rem.val);

                // If a left child exists, add it to the queue for the next level.
                if (rem.left != null) q.add(rem.left);
                
                // If a right child exists, add it to the queue for the next level.
                if (rem.right != null) q.add(rem.right);
            }

            // Append the fully processed level to the final result list.
            res.add(level);
        }

        return res;

    }
}
