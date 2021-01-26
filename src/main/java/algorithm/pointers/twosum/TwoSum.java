package algorithm.pointers.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 */
public class TwoSum {
  // HashMap: Time O(n), Space O(n)
  public int[] twoSum(int[] array, int target) {
    if (array == null || array.length == 0) {
      return new int[] {-1, -1};
    }
    Map<Integer, Integer> seen = new HashMap<>(); // <value, index>
    for (int i = 0; i < array.length; i++) {
      if (seen.containsKey(target - array[i])) {
        return new int[] {i, seen.get(target - array[i])};
      } else {
        seen.put(array[i], i);
      }
    }
    return new int[] {-1, -1};
  }
}
