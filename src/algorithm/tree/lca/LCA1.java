package algorithm.tree.lca;

import algorithm.tree.TreeNode;

/**
 * Given two nodes in a binary tree, find their lowest common ancestor.
 * The given two nodes are guaranteed to be in the binary tree
 */
public class LCA1 {
  public TreeNode lowestCommon(TreeNode root, TreeNode a, TreeNode b) {
    // base case
    if (root == null || root == a || root == b) {
      return root;
    }

    // step1: ask a value from my left and right, respectively
    TreeNode left = lowestCommon(root.left, a, b);
    TreeNode right = lowestCommon(root.right, a, b);

    // step2+3: what should we do in the current level and return to parent
    if (left != null && right != null) { // current root is the LCA
      return root;
    }
    return left == null ? right : left;
  }
}
