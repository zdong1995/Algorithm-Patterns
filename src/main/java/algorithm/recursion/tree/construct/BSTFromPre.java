package algorithm.recursion.tree.construct;

import datastrcture.TreeNode;

public class BSTFromPre {
  public TreeNode bstFromPre(int[] preorder) {
    // assumption: preorder.length >= 1
    return construct(preorder, 0, preorder.length - 1);
  }

  private TreeNode construct(int[] pre, int preLeft, int preRight) {
    // base case
    if (preLeft > preRight) { // the null after leaf node
      return null;
    }
    // find root from pre-order array to divide into left and right parts
    TreeNode root = new TreeNode(pre[preLeft]);
    int leftSize = 0;
    int leftIdx = preLeft;
    while (leftIdx + 1 < preRight && pre[leftIdx] < root.val) {
      leftSize++;
      leftIdx++;
    }
    // build left and right subtree recursively
    root.left = construct(pre, preLeft + 1,preLeft + leftSize);
    root.right = construct(pre, preLeft + leftSize + 1, preRight);

    return root;
  }
}
