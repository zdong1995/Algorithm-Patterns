package algorithm.tree.lca;

import datastrcture.TreeNode;

/**
 * Given two TreeNodes in a binary search tree.
 * Find their lowest common ancestor.
 * The two nodes are guaranteed to be in the binary search tree.
 */
public class LCAOfBST {
  public TreeNode lcaOfBST(TreeNode root, TreeNode p, TreeNode q) {
    // base case
    if (root == null) {
      return root;
    }

    if (root.val < p.val && root.val < q.val) {
      // case 1: root.val < p.val && root.val < q.val -> p and q in root.right
      return lcaOfBST(root.right, p, q);
    } else if (root.val > p.val && root.val > q.val) {
      // case 2: root.val > p.val && root.val > q.val -> p and q in root.left
      return lcaOfBST(root.left, p, q);
    } else {
      // case 3: p < root < q || p > root > q -> root is LCA
      return root;
    }
  }
}
