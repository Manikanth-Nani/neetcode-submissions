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
    public ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null){
            ListNode forward = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forward;
        }

        return prev;
    }
    public ListNode middle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return ;

        // 1) Split list into two halves.
        ListNode mid = middle(head);
        ListNode firstHalf = head;
        ListNode secondHalf = mid.next;
        mid.next = null;

        // 2) Reverse the second half.
        secondHalf = reverse(secondHalf);

        // 3) Merge alternately: firstHalf node, then secondHalf node.
        while(secondHalf != null){
            // Keep next pointers safe before rewiring.
            ListNode temp1 = firstHalf.next;
            ListNode temp2 = secondHalf.next;
            
            firstHalf.next = secondHalf;
            // Connect inserted second-half node back to first-half chain.
            secondHalf.next  = temp1;
            
            // Advance to next pair of nodes.
            firstHalf = temp1;
            secondHalf = temp2;
        }
    }
}