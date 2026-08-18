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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Base cases: if one list is empty, return the other list intact
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        // Determine the starting head node of the merged list
        ListNode head;
        if(list1.val <= list2.val){
            head = list1;         // Pick list1 if its value is smaller or equal
            list1 = list1.next;   // Move list1 pointer forward
        }
        else{
            head = list2;         // Pick list2 if its value is smaller
            list2 = list2.next;   // Move list2 pointer forward
        }

        // Initialize a temporary pointer to build the rest of the list
        ListNode temp = head;

        // Traverse both lists simultaneously until one runs out
        while(list1 != null && list2 != null){

            // Attach the node with the smaller value to the merged list
            if(list1.val <= list2.val){
                temp.next = list1;    // Link the current smaller node
                list1 = list1.next;   // Advance list1 pointer
            }
            else{
                temp.next = list2;    // Link the current smaller node
                list2 = list2.next;   // Advance list2 pointer
            }
            temp = temp.next;         // Move the tail pointer of the merged list forward
        }

        // Append any remaining nodes from the list that did not finish
        if(list1 != null) temp.next = list1;
        if(list2 != null) temp.next = list2;
        
        // Return the start of the newly merged sorted list
        return head;
    }
}
