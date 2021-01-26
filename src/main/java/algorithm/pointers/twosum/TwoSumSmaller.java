package algorithm.pointers.twosum;

import java.util.Arrays;

/**
 * Determine the number of pairs of elements in a given array
 * that sum to a value smaller than the given target number.
 */
public class TwoSumSmaller {
  // Sort + TwoPointers
  // Time O(nlogn + n) = O(n), Space O(n)
  public int twoSumSmaller(int[] array, int target) {
    if (array == null || array.length == 0) {
      return 0;
    }
    Arrays.sort(array);
    int left = 0;
    int right = array.length - 1;
    int count = 0;

    while (left < right) {
      int sum = array[left] + array[right];
      if (sum < target) {
        // all the pair that right in the range (left, right]
        // will be smaller than target
        count += right - left;
        left++;
      } else {
        right--;
      }
    }
    return count;
  }
}
