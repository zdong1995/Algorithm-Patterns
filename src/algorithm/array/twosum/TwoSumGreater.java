package algorithm.array.twosum;

import java.util.Arrays;

/**
 * Given an array of integers, find how many pairs in the array such that their sum is bigger than a specific target number.
 * Please return the number of pairs.
 */
public class TwoSumGreater {
  // Sort + TwoPointers
  // Time O(nlogn + n) = O(n), Space O(n)
  public int twoSumGreater(int[] array, int target) {
    if (array == null || array.length == 0) {
      return 0;
    }
    Arrays.sort(array);
    int left = 0;
    int right = array.length - 1;
    int count = 0;

    while (left < right) {
      int sum = array[left] + array[right];
      if (sum > target) {
        // all the pair that left in the range [left, right)
        // will be greater than target
        count += right - left;
        right--;
      } else {
        left++;
      }
    }
    return count;
  }
}
