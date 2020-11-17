package algorithm.array.twosum;

import java.util.*;

/**
 * Given an unsorted array of integers.
 * Find all unique triplets in the array which gives the sum of target.
 */
public class ThreeSum {
  // Method 1: Sort + For loop 2 Sum
  public List<List<Integer>> threeSum(int[] array, int target) {
    List<List<Integer>> res = new ArrayList<>();
    if (array == null || array.length == 0) {
      return res;
    }
    Arrays.sort(array);

    for (int i = 0; i < array.length; i++) {
      // run two sum to find pair equal to - target in range (i, n-1]
      int left = i + 1;
      int right = array.length - 1;
      while (left < right) {
        int sum = array[left] + array[right];
        if (sum == target - array[i]) {
          res.add(Arrays.asList(array[i], array[left++], array[right--]));
          // skip duplicates
          while (left < right && array[left] == array[left - 1]) {
            left++;
          }
          while (left < right && array[right] == array[right + 1]) {
            right--;
          }
        } else if (sum < target) {
          left++;
          while (left < right && array[left] == array[left - 1]) {
            left++;
          }
        } else {
          right--;
          while (left < right && array[right] == array[right + 1]) {
            right--;
          }
        }
      }
    }
    return res;
  }
}
