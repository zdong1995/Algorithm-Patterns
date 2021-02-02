package algorithm.basic.iterativetree;

/**
 * You are given a tree:
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
public class PopulateNextRight2 {
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node leftMost = root;
        while (leftMost != null) {
            // start from leftMost node to traverse current level
            // and connect the next level
            Node cur = leftMost; // current node at current level
            Node dummy = new Node(0); // used to store head of next level
            Node prev = dummy; // the leading node of next level
            // use dummy to avoid null check to simplify the implementation
            while (cur != null) {
                // traverse current level to connect next level node
                // populate prev.next to next node in the next level
                if (cur.left != null) {
                    prev.next = cur.left;
                    prev = prev.next;
                }
                if (cur.right != null) {
                    prev.next = cur.right;
                    prev = prev.next;
                }
                // move to next node in current level
                cur = cur.next;
            }
            // move leftMost to left most node (head) of next level
            leftMost = dummy.next;
        }
        return root;
    }

    private static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
