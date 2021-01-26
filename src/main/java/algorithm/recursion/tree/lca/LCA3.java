package algorithm.recursion.tree.lca;

import java.util.*;

/**
 * Given two nodes in a binary tree (with parent pointer).
 * Find their lowest common ancestor.
 */
public class LCA3 {
  // Method 1: Find intersection of two LinkedList
  // Time O(height), Space O(1)
  public Node lca3(Node p, Node q) {
    Node node1 = p, node2 = q;
    // traver upside to find the intersection
    // when node1 reach end, set node1 to start point of node2, i.e. Node q
    // thus node1 and node2 will traverse same length (height_p + height_q)
    while (node1 != node2) {
      node1 = node1 == null ? q : node1.parent; // will cover corner case p == null
      node2 = node2 == null ? p : node2.parent; // will cover corner case q == null
    }
    return node1;
  }

  // Method 2: Set
  // Time O(height), Space O(height)
  public Node lca(Node p, Node q) {
    Set<Node> visited = new HashSet<>();

    while (p != null) {
      visited.add(p);
      p = p.parent;
    }

    while (q != null) {
      if (visited.contains(q)) {
        return q;
      }
      q = q.parent;
    }
    return null;
  }

  static class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;

    public Node(int val) {
      this.val = val;
      this.left = null;
      this.right = null;
      this.parent = null;
    }
  }
}