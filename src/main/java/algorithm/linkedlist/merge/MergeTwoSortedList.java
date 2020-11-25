package algorithm.linkedlist.merge;

import datastrcture.ListNode;

/**
 * You are given two linked lists, each is sorted in ascending order.
 * Merge the linked-lists into one sorted linked-list and return it.
 */
public class MergeTwoSortedList {
  // Iterative: Time O(m+n), Space O(1)
  private ListNode mergeTwoSortedList(ListNode head1, ListNode head2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;

    while (head1 != null && head2 != null) {
      if (head1.val < head2.val) {
        cur.next = head1;
        head1 = head1.next;
      } else {
        cur.next = head2;
        head2 = head2.next;
      }
      cur = cur.next;
    }
    // post-processing
    if (head1 != null) {
      cur.next = head1;
    }
    if (head2 != null) {
      cur.next = head2;
    }
    return dummy.next;
  }
}
