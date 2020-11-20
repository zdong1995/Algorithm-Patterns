package algorithm.tree.construct;

import algorithm.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructFromInPre {
  public TreeNode constructFromInPost(int[] pre, int[] in) {
    // assumption: pre.length = in.length >= 1
    Map<Integer, Integer> nodeIndex = new HashMap<>();
    // record the TreeNode and index pair to avoid duplicate search in recursion
    for (int i = 0; i < in.length; i++) {
      nodeIndex.put(in[i], i);
    }
    return construct(pre, 0, pre.length - 1, in, 0, in.length - 1, nodeIndex);
  }

  private TreeNode construct(int[] pre, int preLeft, int preRight,
                             int[] in, int inLeft, int inRight,
                             Map<Integer, Integer> nodeIndex) {
    // base case
    if (preLeft > preRight) {
      return null;
    }
    // find the root node of current level to divide to left and right part
    TreeNode root = new TreeNode(pre[preLeft]);
    // find the index of root in in-order sequence
    int rootIdx = nodeIndex.get(root.val);
    int leftSize = rootIdx - inLeft; // record the size of left subtree
    // construct left and right recursively
    root.left = construct(pre, preLeft + 1, preLeft + leftSize,
                          in, inLeft, rootIdx, nodeIndex);
    root.right = construct(pre, preLeft + leftSize + 1, preRight,
                           in, rootIdx + 1, inRight, nodeIndex);
    return root;
  }
}
