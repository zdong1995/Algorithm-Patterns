package algorithm.pointers.merge;

import datastrcture.ListNode;

import java.util.*;

/**
 * You are given an array of k linked lists, each is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 */
public class MergeKSortedList {
    // Method 1: K Pointers Merge with MinHeap
    // Time: O(nK*logK), Space: O(K)
    public ListNode mergeKSortedList(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int k = lists.length;
        // keep k pointers to merge k list, each time move next the list with smallest
        // thus we can use a min heap with size k to compare the k pointers
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(k, (n1, n2) -> n1.val - n2.val);
        // initialize: add k head to the heap
        for (ListNode head : lists) {
            if (head != null) { // corner case: the k list may have null list
                minHeap.offer(head);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        // each add the smallest node to the new list, and add next node into the heap
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            if (node.next != null) { // added all the nodes of one list
                minHeap.offer(node.next);
            }
            cur.next = node;
            node.next = null;
            cur = node;
        }

        return dummy.next;
    }

    // Method 2: Recursive Binary Reduction, Divide and Conquer
    // Time: O(nK*logK), Space: O(K) for call stack
    public ListNode mergeKSortedList1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    // return merged lists that range from start to end
    private ListNode merge(ListNode[] lists, int start, int end) {
        // base case
        if (start == end) { // the list itself
            return lists[start];
        }

        int mid = start + (end - start) / 2;
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid + 1, end);
        return mergeTwoList(left, right);
    }

    // Method 3: Iterative Binary Reduction
    // Time: O(nK*logK), Space: O(1)
    public ListNode mergeKSortedList2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int k = lists.length;
        // reuse the list node array
        for (int interval = 1; interval < k; interval *= 2) {
            for (int i = 0; i < (k - interval); i += interval * 2) {
                lists[i] = mergeTwoList(lists[i], lists[i + interval]);
            }
        }
        return lists[0];
    }

    // Method 4: Iterative Merge Two List
    // Time: O(nK^2), Space: O(1)
    public ListNode mergeKSortedList3(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode merged = lists[0];
        // for loop can cover the case that length == 1
        // if length = 1, the for loop will be i = 1 < 1, will not enter
        for (int i = 1; i < lists.length; i++) {
            merged = mergeTwoList(lists[i], merged);
        }

        return merged;
    }

    private ListNode mergeTwoList(ListNode head1, ListNode head2) {
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

        if (head1 != null) {
            cur.next = head1;
        }
        if (head2 != null) {
            cur.next = head2;
        }
        return dummy.next;
    }
}
