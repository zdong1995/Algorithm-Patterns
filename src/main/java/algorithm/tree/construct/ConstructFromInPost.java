package algorithm.tree.construct;

import datastrcture.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructFromInPost {
  public TreeNode constructFromInPost(int[] post, int[] in) {
    // assumption: post.length = in.length >= 1
    Map<Integer, Integer> nodeIndex = new HashMap<>();
    // record the TreeNode and index pair to avoid duplicate search in recursion
    for (int i = 0; i < in.length; i++) {
      nodeIndex.put(in[i], i);
    }
    return construct(post, 0, post.length - 1, in, 0, in.length - 1, nodeIndex);
  }

  private TreeNode construct(int[] post, int postLeft, int postRight,
                             int[] in, int inLeft, int inRight,
                             Map<Integer, Integer> nodeIndex) {
    // base case
    if (postLeft > postRight) { // the null after leaf node
      return null;
    }
    // find the root node of current level to divide to left and right part
    TreeNode root = new TreeNode(post[postRight]);
    // find the index of root in in-order sequence
    int rootIdx = nodeIndex.get(root.val);
    int leftSize = rootIdx - inLeft; // record the size of left subtree
    // construct left and right recursively
    root.left = construct(post, postLeft, postLeft + leftSize - 1,
                          in, inLeft, rootIdx, nodeIndex);
    root.right = construct(post, postLeft + leftSize, postRight - 1,
                           in, rootIdx + 1, inRight, nodeIndex);
    return root;
  }
}
