package algorithm.pointers.merge;

import datastrcture.ListNode;

/**
 * You are given two linked lists, each is sorted in ascending order.
 * Merge the linked-lists into one sorted linked-list and return it.
 */
public class MergeTwoSortedList {
  // Iterative: Time O(m+n), Space O(1)
  public ListNode mergeTwoSortedList(ListNode head1, ListNode head2) {
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;

    while (head1 != null && head2 != null) {
      if (head1.val < head2.val) {
        tail.next = head1;
        head1 = head1.next;
      } else {
        tail.next = head2;
        head2 = head2.next;
      }
      tail = tail.next;
    }
    // post-processing
    if (head1 != null) {
      tail.next = head1;
    }
    if (head2 != null) {
      tail.next = head2;
    }
    return dummy.next;
  }
}
