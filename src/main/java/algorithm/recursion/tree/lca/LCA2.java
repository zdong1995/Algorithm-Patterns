package algorithm.recursion.tree.lca;

import datastrcture.TreeNode;
/**
 * Given two nodes in a binary tree, find their lowest common ancestor.
 * The given two nodes may not in the binary tree. Return null if any of the nodes is not in the tree.
 */
public class LCA2 {
  // Method 1: Recursion + Find existence
  // Time O(n), Space O(height)
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

  // Method 2: Recursion with counter
  // Time O(n), Space O(height)
  private int count = 0; // how many nodes between one and two we have found

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
    // sanity check
    if (root == null || one == null || two == null) {
      return null;
    }
    TreeNode lca = getLCA(root, one, two);
    return count == 2 ? lca : null;
  }

  private TreeNode getLCA(TreeNode root, TreeNode one, TreeNode two) {
    // base case
    if (root == null) {
      return null;
    }
    // must go through all the recursion, can't return early
    TreeNode left = getLCA(root.left, one, two);
    TreeNode right = getLCA(root.right, one, two);

    if (root == one || root == two) {
      count++;
      return root;
    }
    if (left != null && right != null) {
      return root;
    }
    return left != null ? left : right;
  }
}
