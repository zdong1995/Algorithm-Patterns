package algorithm.pointers.twosum;

import java.util.Arrays;

/**
 * Given an array of n integers nums and an integer target.
 * Find the number of triplets whose sum is smaller than target.
 */
public class ThreeSumSmaller {
  // Sort + TwoPointers
  // Time O(n^2 + nlogn) = O(n^2), Space O(n) for sort
  public int threeSumSmaller(int[] array, int target) {
    if (array == null || array.length < 3) {
      return 0;
    }
    Arrays.sort(array);
    int count = 0;

    for (int i = 0; i < array.length; i++) {
      count += countTwoSumSmaller(array, i, target - array[i]);
    }
    return count;
  }

  private int countTwoSumSmaller(int[] array, int start, int target) {
    // count number of pairs that sum to target
    int count = 0;
    int left = start + 1;
    int right = array.length - 1;
    while (left < right) {
      int sum = array[left] + array[right];
      if (sum < target) {
        count += right - left;
      } else {
        right--;
      }
    }
    return count;
  }
}
