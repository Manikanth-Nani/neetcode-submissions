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
    public ListNode removeNthFromEnd(ListNode head, int n) {
         // Edge case: If the list is empty
        if (head == null) return null;

        // Initialize two pointers, both starting at the head of the list
        ListNode first = head, second = head;

        // Move the 'first' pointer 'n' steps ahead
        for (int i = 0; i < n; i++) {
            first = first.next;
        }

        // Edge case: If 'first' pointer is null, it means we need to remove the head
        if (first == null) {
            return head.next;  // Move the head to the next node
        }

        // Move both 'first' and 'second' pointers until 'first' reaches the end of the list
        // 'second' will then be at the node just before the node to be removed
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }

        // Remove the nth node from the end by bypassing it
        second.next = second.next.next;

        // Return the head of the modified linked list
        return head;
    }
}