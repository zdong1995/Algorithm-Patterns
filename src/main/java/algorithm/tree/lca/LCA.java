package algorithm.tree.lca;

import datastrcture.TreeNode;

/**
 * Given two nodes in a binary tree, find their lowest common ancestor.
 * The given two nodes are guaranteed to be in the binary tree.
 */
public class LCA {
  // Recursion: Time O(n), Space O(height)
  public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
    // base case
    if (root == null || root == p || root == q) {
      return root;
    }

    // step1: ask a value from my left and right, respectively
    TreeNode left = lca(root.left, p, q);
    TreeNode right = lca(root.right, p, q);

    // step2+3: what should we do in the current level and return to parent
    if (left != null && right != null) { // current root is the LCA
      return root;
    }
    return left == null ? right : left;
  }
}