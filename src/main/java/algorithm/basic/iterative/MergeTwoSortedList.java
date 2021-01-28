package algorithm.basic.iterative;

import datastrcture.*;

/**
 * Given two sorted lists, return the merged list of the given two lists.
 */
public class MergeTwoSortedList {
    public ListNode mergeTwoList(ListNode one, ListNode two) {
        // use dummy head to handle the corner case
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (one != null || two != null) {
            if (one.val < two.val) {
                tail.next = one;
                one = one.next;
            } else {
                tail.next = two;
                two = two.next;
            }
            tail = tail.next;
        }

        if (one != null) {
            tail.next = one;
        } else {
            tail.next = two;
        }
        return dummy.next;
    }
}
