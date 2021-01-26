package algorithm.recursion.tree.lca;

import datastrcture.TreeNode;

import java.util.*;

/**
 * Given K nodes in a binary tree, find their lowest common ancestor.
 */
public class LCAOfKNode {
  // Recursion: Time O(n), Space O(max(k, height))
  public TreeNode lcaOfKNode(TreeNode root, List<TreeNode> nodes) {
    // use hashset to optimize the validation of finding node
    Set<TreeNode> nodeSet = new HashSet<>(nodes);
    return findLCA(root, nodeSet);
  }

  private TreeNode findLCA(TreeNode root, Set<TreeNode> nodeSet) {
    // base case
    if (root == null || nodeSet.contains(root)) { // root == p || q
      return root;
    }

    TreeNode left = findLCA(root.left, nodeSet);
    TreeNode right = findLCA(root.right, nodeSet);

    if (left != null && right != null) {
      return root;
    }
    return left == null ? right : left;
  }
}
