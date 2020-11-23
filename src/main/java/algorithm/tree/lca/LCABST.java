package algorithm.tree.lca;

import algorithm.tree.TreeNode;

public class LCABST {
  public TreeNode lcaBST(TreeNode root, TreeNode p, TreeNode q) {
    // base case
    if (root == null) {
      return root;
    }

    if (root.val < p.val && root.val < q.val) {
      // case 1: root.val < p.val && root.val < q.val -> p and q in root.right
      return lcaBST(root.right, p, q);
    } else if (root.val > p.val && root.val > q.val) {
      // case 2: root.val > p.val && root.val > q.val -> p and q in root.left
      return lcaBST(root.left, p, q);
    } else {
      // case 3: p < root < q || p > root > q -> root is LCA
      return root;
    }
  }
}
