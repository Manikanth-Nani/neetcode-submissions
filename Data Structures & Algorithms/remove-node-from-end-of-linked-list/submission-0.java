class Solution {
    public int lengthLL(ListNode head){
        ListNode curr = head;
        int len = 0;
        while(curr != null){
            len++;
            curr = curr.next;
        }
        return len;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Handle a single-node list
        if(head.next == null) return null; 
        
        int len = lengthLL(head);
        int pos = len - n;
        
        // Edge Case: If we need to remove the head node itself
        if (pos == 0) {
            return head.next;
        }
        
        ListNode posLL = head;
        int i = 1; // Start counting from 1 to stop exactly before the target node
        
        while(i < pos){
            posLL = posLL.next; 
            i++;
        }
        
        // Skip the target node
        posLL.next = posLL.next.next;
        
        return head;
    }
}
