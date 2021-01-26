package algorithm.pointers.twosum;

import java.util.*;

/**
 * Find all pairs of elements in a given array that sum to the given target number.
 * Return all the pairs of indices.
 */
public class TwoSumAllPairs {
  // HashMap: Time O(n), Space O(n)
  public List<List<Integer>> twoSumAllPairs(int[] array, int target) {
    List<List<Integer>> res = new ArrayList<>();
    Map<Integer, List<Integer>> visited = new HashMap<>();
    // key = number, value = list of indices

    for (int i = 0; i < array.length; i++) {
      int diff = target - array[i];
      List<Integer> indices = visited.get(diff);
      if (indices != null) {
        // enumerate all possible indices pairs of current (array[i], diff) pair
        for (int idx : indices) {
          res.add(Arrays.asList(i, idx));
        }
      }
      visited.putIfAbsent(array[i], new ArrayList<>());
      visited.get(array[i]).add(i);
    }
    return res;
  }
}
