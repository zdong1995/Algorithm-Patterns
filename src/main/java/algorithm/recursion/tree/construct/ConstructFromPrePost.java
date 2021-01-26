package algorithm.recursion.tree.construct;

import datastrcture.TreeNode;

import java.util.*;

public class ConstructFromPrePost {
  public TreeNode constructFromPrePost(int[] pre, int[] post) {
    // assumption: pre.length = post.length >= 1
    Map<Integer, Integer> nodeIndex = new HashMap<>();
    // record the TreeNode and index pair to avoid duplicate search in recursion
    for (int i = 0; i < post.length; i++) {
      nodeIndex.put(post[i], i);
    }
    return construct(pre, 0, pre.length - 1, post, 0, post.length - 1, nodeIndex);
  }

  private TreeNode construct(int[] pre, int preLeft, int preRight,
                             int[] post, int postLeft, int postRight,
                             Map<Integer, Integer> nodeIndex) {
    // base case
    if (preLeft > preRight) { // the null after leaf node
      return null;
    }
    // find the root node of current level to divide to left and right part
    TreeNode root = new TreeNode(pre[preLeft]);
    // the second node in pre-order will be the left subtree root
    // avoid out of bound before get pre[preLeft + 1]
    if (preLeft + 1 > preRight) {
      return root;
    }
    // find the index of left root in post-order sequence
    int leftRootIdx = nodeIndex.get(pre[preLeft + 1]);
    int leftSize = leftRootIdx - postLeft + 1; // record the size of left subtree
    // construct left and right recursively
    root.left = construct(pre, preLeft + 1, preLeft + leftSize,
                          post, postLeft, leftRootIdx, nodeIndex);
    root.right = construct(pre, preLeft + leftSize + 1, preRight,
                           post, leftRootIdx + 1, postRight, nodeIndex);
    return root;
  }
}
