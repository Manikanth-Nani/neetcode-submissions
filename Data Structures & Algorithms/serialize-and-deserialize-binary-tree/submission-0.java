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

public class Codec {

    public String serialize(TreeNode root) {
        if(root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(q.size() > 0){
            TreeNode rem = q.remove();

            if(rem == null) sb.append("#,");
            else{
                sb.append(rem.val).append(",");
                q.add(rem.left);
                q.add(rem.right);
            }
        }

        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if(data == null || data.isEmpty()) return null;

        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        int i = 1;
        while(q.size() > 0){
            TreeNode rem = q.remove();

            if(!nodes[i].equals("#")){
                rem.left = new TreeNode(Integer.parseInt(nodes[i]));
                q.add(rem.left);
            }

            i++;

            if(!nodes[i].equals("#")){
                rem.right = new TreeNode(Integer.parseInt(nodes[i]));
                q.add(rem.right);
            }
            i++;
        }

        return root;
        
    }
}
