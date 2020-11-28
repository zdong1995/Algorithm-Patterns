package algorithm.tree.lca;

import datastrcture.KnaryTreeNode;

import java.util.*;

/**
 * Given N nodes in a K-nary tree, find their lowest common ancestor.
 * The N nodes are guaranteed to be in the K-nary tree.
 */
public class LCAOfKnaryMNode {
  public KnaryTreeNode knaryLCAofMNode(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
    // sanity check
    if (root == null || nodes == null || nodes.size() == 0) {
      return null;
    }
    Set<KnaryTreeNode> nodeSet = new HashSet<>(nodes);
    return findLCA(root, nodeSet);
  }

  private KnaryTreeNode findLCA(KnaryTreeNode root, Set<KnaryTreeNode> nodeSet) {
    // base case
    if (root == null || nodeSet.contains(root)) {
      return root;
    }

    int count = 0;
    KnaryTreeNode temp = null; // initialize the lca to return in current subtree

    for (KnaryTreeNode child : root.children) {
      KnaryTreeNode res = findLCA(child, nodeSet);
      if (res != null) {
        count++;
        if (count > 1) {
          // found more than one node, similar to count == 2 for two nodes
          // that means current root is LCA of multiple nodes
          return root;
        }
        temp = res; // record the found non-null node
      }
    }
    return temp; // found only one node or null
  }
}
