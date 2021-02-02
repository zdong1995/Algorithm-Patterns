package algorithm.basic.iterativetree;

/**
 * You are given a doubly linked list which in addition to the next and previous pointers,
 * it could have a child pointer, which may or may not point to a separate doubly linked list.
 * These child lists may have one or more children of their own, and so on.
 *
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list.
 * You are given the head of the first level of the list.
 */
public class FlattenMultiLevelList {
    public Node flatten(Node head) {
        Node cur = head;
        while (cur != null) {
            // case 1: no child, no need to flatten child list
            if (cur.child == null) {
                cur = cur.next;
                continue;
            }
            // case 2: has child, find tail of child list, connect to next
            Node tail = cur.child;
            while (tail.next != null) {
                tail = tail.next;
            }
            // connect with the child list
            tail.next = cur.next;
            if (cur.next != null) { // avoid NPE, cur maybe the last node
                cur.next.prev = tail;
            }
            cur.next = cur.child;
            cur.child.prev = cur;
            cur.child = null; // delink
            // traverse next node
            cur = cur.next;
        }
        return head;
    }

    private static class Node {
        int val;
        Node next;
        Node prev;
        Node child;
    }
}
