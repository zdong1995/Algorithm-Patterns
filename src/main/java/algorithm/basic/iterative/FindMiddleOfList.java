package algorithm.basic.iterative;

import datastrcture.*;

/**
 * Given one linked list, find the middle node of the linked list.
 * If there are even number of nodes, return the left middle one.
 */
public class FindMiddleOfList {
    public ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        // odd number: slow stop at n+1, fast stop at 2n+1 -> fast.next = null
        // even number: slow stop at n, fast stop at 1+2(n-1)=2n-1 -> fast.next.next = null
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
