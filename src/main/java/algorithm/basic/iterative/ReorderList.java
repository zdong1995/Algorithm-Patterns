package algorithm.basic.iterative;

import datastrcture.*;

/**
 * Given a singly linked list L_0 → L_1 → … → L_{n-1} → L_n
 * Reverse it to L_n → L_{n-1} → … → L_1 → L_0
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode mid = findMiddle(head);
        ListNode secondHead = reverseList(mid.next);
        mid.next = null; // de-link to avoid circle
        head = mergeTwoList(head, secondHead);
    }

    private ListNode findMiddle(ListNode head) {
        // assumption: head is not null
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

    private ListNode reverseList(ListNode head) {
        // assumption: head is not null
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    private ListNode mergeTwoList(ListNode one, ListNode two) {
        // use dummy head to handle the corner case, one is oddHead, two is evenHead
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (one != null && two != null) {
            tail.next = one;
            one = one.next;
            tail.next.next = two;
            two = two.next;
            tail = tail.next.next;
        }

        // first half sublist will be longer or equal than second half list
        if (one != null) {
            tail.next = one;
        }
        return dummy.next;
    }
}
