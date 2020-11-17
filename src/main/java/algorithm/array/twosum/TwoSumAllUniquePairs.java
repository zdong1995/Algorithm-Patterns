package algorithm.array.twosum;

import java.util.*;

/**
 * Given an unsorted array with duplicates and target value.
 * Find all pairs of elements that sum to the target number.
 * Return all the distinct pairs of values.
 */
public class TwoSumAllUniquePairs {
  // Solution 1: Sort + Two pointers
  // Time O(nlogn + n) = O(nlogn), Space O(n)
  public List<List<Integer>> twoSumAllUniquePairs(int[] array, int target) {
    List<List<Integer>> res = new ArrayList<>();
    if (array == null || array.length == 0) {
      return res;
    }
    Arrays.sort(array);
    int left = 0;
    int right = array.length - 1;
    while (left < right) {
      int sum = array[left] + array[right];
      if (sum == target) {
        res.add(Arrays.asList(array[left++], array[right--]));
        // deduplicate
        while (left < right && array[left] == array[left - 1]) {
          left++;
        }
        while (left < right && array[right] == array[right + 1]) {
          right--;
        }
      } else if (sum > target){
        right--;
        while (left < right && array[right] == array[right + 1]) { // optional
          right--;
        }
      } else {
        left++;
        while (left < right && array[left] == array[left - 1]) { // optional
          left++;
        }
      }
    }
    return res;
  }

  // Solution 2: HashMap
  // Time O(n), Space O(n)
  public List<List<Integer>> twoSumAllUniquePairsHashMap(int[] array, int target) {
    List<List<Integer>> res = new ArrayList<>();
    if (array == null || array.length == 0) {
      return res;
    }

    Map<Integer, Integer> visited = new HashMap<>(); // number, first appeared index
    Set<Integer> used = new HashSet<>(); // indices used in solution pairs

    for (int i = 0; i < array.length; i++) {
      int diff = target - array[i];
      if (visited.containsKey(diff)) {
        // need to check whether this pair is already used before
        if (!used.contains(array[i]) && !used.contains(diff)) {
          res.add(Arrays.asList(array[i], diff));
          used.add(array[i]);
          used.add(diff);
        }
      } else {
        visited.put(array[i], i);
      }
    }
    return res;
  }

  /**
   * Follow-up: Only return count of all unique pairs
   * HashMap: Time O(n), Space O(n)
   */
  public int twoSumAllUniquePairsCount(int[] array, int target) {
    if (array == null || array.length == 0) {
      return 0;
    }

    Map<Integer, Boolean> visited = new HashMap<>();
    // key = number, value = whether used in solution pair
    int count = 0;

    for (int num : array) {
      int diff = target - num;
      if (visited.containsKey(diff)) {
        // need to check whether this pair is already used before
        if (!visited.get(diff)) {
          count++;
          visited.put(diff, true);
          visited.put(num, true);
        }
      } else {
        visited.put(num, false);
      }
    }
    return count;
  }
}
