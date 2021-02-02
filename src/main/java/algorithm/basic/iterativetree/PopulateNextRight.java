package algorithm.basic.iterativetree;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 * The binary tree has the following definition:
 * class Node {
 *   int val;
 *   Node left;
 *   Node right;
 *   Node next;
 * }
 *
 *
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 */
public class PopulateNextRight {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node leftMost = root;
        while (leftMost.left != null) {
            // start from the leftmost node, connect current level
            Node cur = leftMost;
            while (cur != null) { // traverse the linked list of current level
                // connect for same root nodes
                cur.left.next = cur.right;
                // connect for inter-root nodes
                if (cur.next != null) { // avoid NPE
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            leftMost = leftMost.left;
            // traverse the left most path to arrive last level
        }
        return root;
    }

    private static class Node {
        int val;
        Node left;
        Node right;
        Node next;
    }
}
