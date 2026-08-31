/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    class Pair implements Comparable<Pair>{
        int val;
        ListNode node;

        Pair(int v, ListNode n){
            val = v;
            node = n;
        }

        //sort based on node val 
        public int compareTo(Pair other){
            if(this.val < other.val) return -1;
            else if(this.val > other.val) return 1;
            else return 0;
        }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        
        int n = lists.length;
        if(n == 0) return null;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        // Add the first element of each list to the priority queue
        for (int i = 0; i < n; i++) {
            if (lists[i] != null) {  // Check for null before adding to the priority queue
                pq.add(new Pair(lists[i].val, lists[i]));
            }
        }

        ListNode ans = new ListNode(-1);
        ListNode curr = ans;

        // Process the priority queue
        while (pq.size() > 0) {
            Pair rem = pq.remove();
            ListNode new_node = new ListNode(rem.val);
            curr.next = new_node;
            curr = curr.next;

            if (rem.node.next != null) {
                pq.add(new Pair(rem.node.next.val, rem.node.next));
            }
        }

        return ans.next;
    }
}