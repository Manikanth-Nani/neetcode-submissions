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
    public boolean hasCycle(ListNode head) {
        // Base case: if list is empty or has only one node, a cycle is impossible
        if(head == null || head.next == null) return false;

        // Initialize two pointers at the start of the list
        ListNode slow = head; // Moves 1 step at a time
        ListNode fast = head; // Moves 2 steps at a time

        // Traverse until the fast pointer reaches the end of the list
        while(fast != null && fast.next != null){
            slow = slow.next;        // Advance slow pointer by 1 step
            fast = fast.next.next;   // Advance fast pointer by 2 steps

            // If they meet, a cycle exists (the fast pointer lapped the slow pointer)
            if(slow == fast) return true;
        }

        // Fast pointer reached the end (null), meaning the list has a definite end (no cycle)
        return false;
    }
}
