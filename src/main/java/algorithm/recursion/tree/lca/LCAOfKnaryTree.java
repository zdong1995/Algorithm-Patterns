package algorithm.recursion.tree.lca;

import datastrcture.KnaryTreeNode;

/**
 * Given two nodes in a K-nary tree, find their lowest common ancestor.
 * The given two nodes are guaranteed to be in the K-nary tree.
 */
public class LCAOfKnaryTree {
    // Recursion: Time O(n), Space O(height)
    public KnaryTreeNode lcaOfKnaryTree(KnaryTreeNode root, KnaryTreeNode p, KnaryTreeNode q) {
        // base case
        if (root == null || root == p || root == q) {
            return root;
        }

        int count = 0; // store how many nodes between p and q we have found
        KnaryTreeNode temp = null; // initialize the lca to return in current subtree

        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode res = lcaOfKnaryTree(child, p, q);
            if (res != null) {
                count++; // found one node, update the counter
                if (count == 2) { // already found two nodes, current root is LCA
                    return root;
                }
                temp = res; // update the lca result
            }
        }

        return temp; // temp == p || q || null
    }
}
