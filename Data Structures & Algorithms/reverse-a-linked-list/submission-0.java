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
    public ListNode reverseList(ListNode head) {
        // Base case: if list is empty or has only one node, no reversal is needed
        if(head == null || head.next == null) return head;

        // Initialize tracking pointers
        ListNode prev = null;    // Tracks the previous node (initially null for the new tail)
        ListNode curr = head;    // Tracks the current node being processed

        // Traverse through the entire linked list
        while(curr != null){
            ListNode farword = curr.next; // Temporarily save the next node to prevent losing the rest of the list
            curr.next = prev;             // Reverse the pointer to face the previous node
            prev = curr;                  // Move the 'prev' pointer one step forward
            curr = farword;               // Move the 'curr' pointer one step forward
        }

        // 'prev' now points to the new head of the reversed list
        return prev;
    }
}
