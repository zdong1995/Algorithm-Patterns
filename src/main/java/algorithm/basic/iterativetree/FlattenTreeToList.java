package algorithm.basic.iterativetree;

import datastrcture.*;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 */

public class FlattenTreeToList {
    public void flatten(TreeNode root) {
        if (root == null) { // base case
            return;
        }
        TreeNode cur = root;
        while (cur != null) { // traverse down along the rightmost path
            TreeNode next = cur.right; // store next node
            // flatten the left subtree to insert between cur and cur.right
            if (cur.left != null) {
                // find the tail of the right subtree of cur.left
                TreeNode tail = cur.left;
                while (tail.right != null) {
                    tail = tail.right;
                }
                // connect the tail to stored next
                tail.right = next;
                cur.right = cur.left;
                cur.left = null; // delink
            }
            cur = cur.right;
        }
    }
}
