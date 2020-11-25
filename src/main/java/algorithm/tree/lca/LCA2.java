package algorithm.tree.lca;

import datastrcture.TreeNode;
/**
 * Given two nodes in a binary tree, find their lowest common ancestor.
 * The given two nodes may not in the binary tree. Return null if any of the nodes is not in the tree.
 */
public class LCA2 {
  // Recursion: Time O(n), Space O(height)
  public TreeNode lca2(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || p == null || q == null) { // sanity check
      return null;
    }
    TreeNode res = findLCA(root, p, q);
    // need to check whether res == p || q
    if (res == p) {
      // find q in the subtree of p to determine whether q in the tree
      return findLCA(p, q, q) != null ? res : null;
    }
    if (res == q) {
      // find p in the subtree of q to determine whether q in the tree
      return findLCA(q, p, p) != null ? res : null;
    }
    return res; // (res != p && res != q) || res == null
  }

  private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
    // base case
    if (root == null || root == p || root == q) {
      return root;
    }
    TreeNode left = findLCA(root.left, p, q);
    TreeNode right = findLCA(root.right, p, q);

    if (left != null && right != null) { // current root is LCA
      return root;
    }
    return left != null ? left : right;
  }
}
