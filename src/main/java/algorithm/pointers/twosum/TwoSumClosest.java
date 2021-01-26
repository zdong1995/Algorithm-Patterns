package algorithm.pointers.twosum;

import java.util.Arrays;

/**
 * Find the pair of elements in a given array that sum to a value that is closest to the given target number.
 * Return the min difference.
 */
public class TwoSumClosest {
  // Sort + Two Pointers
  // Time O(nlogn + n) = O(nlogn), Space O(n)
  public int twoSumClosest(int[] array, int target) {
    if (array == null || array.length == 0) {
      return -1;
    }
    Arrays.sort(array);

    int minDiff = Integer.MAX_VALUE;

    int left = 0;
    int right = array.length - 1;

    while (left < right) {
      int sum = array[left] + array[right];
      minDiff = Math.min(Math.abs(target - sum), minDiff);
      if (sum == target) {
        return 0;
      } else if (sum < target) {
        left++;
      } else {
        right--;
      }
    }
    return minDiff == Integer.MAX_VALUE ? - 1 : minDiff;
  }
}
