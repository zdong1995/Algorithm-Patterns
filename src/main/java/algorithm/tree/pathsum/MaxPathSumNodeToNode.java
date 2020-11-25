package algorithm.tree.pathsum;

import datastrcture.TreeNode;

public class MaxPathSumNodeToNode {
  // sum = the largest sum path that ends at root
  private void helper(TreeNode root, int[] max, int sum) {
    // base case
    if (root == null) {
      return;
    }

    if(sum < 0) {
      sum = root.val;
    } else {
      sum += root.val;;
    }
    max[0] = Math.max(max[0], sum); // pre-order traversal

    helper(root.left, max, sum);
    helper(root.right, max, sum);
  }
}
