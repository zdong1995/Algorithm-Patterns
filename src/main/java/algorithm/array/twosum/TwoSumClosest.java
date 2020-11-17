package algorithm.array.twosum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find the pair of elements in a given array that sum to a value that is closest to the given target number.
 * Return the values of the two numbers.
 */
public class TwoSumClosest {
  // Sort + Two Pointers
  // Time O(nlogn + n) = O(nlogn), Space O(n)
  public List<Integer> twoSumClosest(int[] array, int target) {
    List<Integer> res = new ArrayList<>();
    if (array == null || array.length == 0) {
      return res;
    }
    Arrays.sort(array);
    int left = 0;
    int right = array.length - 1;
    int minDiff = Integer.MAX_VALUE;

    while (left < right) {
      int sum = array[left] + array[right];
      if (sum == target) {
        return Arrays.asList(array[left], array[right]);
      } else if (sum < target) {
        if (target - sum < minDiff) {
          minDiff = target - sum;
          res = Arrays.asList(array[left], array[right]);
        }
        left++;
      } else {
        if (sum - target < minDiff) {
          minDiff = sum - target;
          res = Arrays.asList(array[left], array[right]);
        }
        right--;
      }
    }
    return res;
  }
}
