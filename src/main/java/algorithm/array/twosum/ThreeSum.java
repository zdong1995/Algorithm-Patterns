package algorithm.array.twosum;

import com.sun.tools.javac.code.Attribute;

import java.util.*;

/**
 * Given an unsorted array of integers.
 * Find all unique triplets in the array which gives the sum of target.
 */
public class ThreeSum {
  // Method 1: Sort + For loop 2 Sum
  // Time O(n^2 + nlogn) = O(n^2), Space O(n) for sort
  public static List<List<Integer>> threeSum(int[] array, int target) {
    List<List<Integer>> res = new ArrayList<>();
    if (array == null || array.length < 3) {
      return res;
    }
    Arrays.sort(array);

    for (int i = 0; i < array.length - 2; i++) {
      if (i > 0 && array[i] == array[i - 1]) { // skip duplicate start index
        continue;
      }
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
        } else if (sum < target - array[i]) {
          left++;
        } else {
          right--;
        }
      }
    }
    return res;
  }

  // Method 2: HashSet without Sort
  // Time: O(n^2) Space: O(n)
  public static List<List<Integer>> threeSum3(int[] array, int target) {
    List<List<Integer>> res = new ArrayList<>();
    if (array == null || array.length < 3) {
      return res;
    }
    Set<Integer> first = new HashSet<>();

    for (int i = 0; i < array.length; i++) { // enumerate each first number
      if (first.contains(array[i])) { // skip duplicate for first number
        continue;
      }
      Set<Integer> visited = new HashSet<>();
      Set<Integer> used = new HashSet<>();
      int twoSum = target - array[i];
      // find all unique pairs that sum to twoSum
      for (int j = i + 1; j < array.length; j++) {
        // enumerate each number and check whether the diff in hash set
        int diff = twoSum - array[j];
        if (visited.contains(diff)) {
          if (!used.contains(array[j]) && !used.contains(diff)) {
            res.add(Arrays.asList(array[i], array[j], twoSum - array[j]));
            used.add(array[j]);
            used.add(diff);
          }
        } else {
          visited.add(array[j]);
        }
      }
      first.add(array[i]);
    }
    return res;
  }

  // Method 3: Sort + HashMap counter
  // Time: O(n^2 + nlogn) = O(n^2), Space = O(n) for sort + map
  public static List<List<Integer>> threeSum2(int[] array, int target) {
    List<List<Integer>> res = new ArrayList<>();
    if (array == null || array.length < 3) {
      return res;
    }
    Arrays.sort(array);

    Map<Integer, Integer> counter = new HashMap<>();
    for (int num : array) {
      counter.put(num, counter.getOrDefault(num, 0) + 1);
    }

    int left = 0;
    while (left < array.length - 1) {
      for (int right = left + 1; right < array.length; right++) {
        int diff = target - array[left] - array[right];
        int diffNeeded = 1;
        if (array[left] == diff) {
          diffNeeded++;
        }
        if (array[right] == diff) {
          diffNeeded++;
        }
        if (counter.getOrDefault(diff, 0) >= diffNeeded && diff >= array[right]) {
          // strictly maintain array[left] <= array[right] <= diff to deduplicate
          res.add(Arrays.asList(array[left], array[right], diff));
        }
        // deduplicate
        while (right < array.length - 1 && array[right] == array[right + 1]) {
          right++;
        }
      }
      // deduplicate
      while (left < array.length - 1 && array[left] == array[left + 1]) {
        left++;
      }
      left++;
    }
    return res;
  }

  public static void main(String[] args) {
    int[] nums = {0, 1, 1, -4, 0, 6, 9, -11, 16, 8, 5, 0, 0};
    int[] targets = {5, 0, -10, 16};
    for (int target : targets) {
      System.out.println("Target is " + target);
      print(threeSum(nums, target));
    }
    for (int target : targets) {
      System.out.println("Target is " + target);
      print(threeSum3(nums, target));
    }
  }

  private static void print(List<List<Integer>> list) {
    if (list == null || list.size() == 0 || list.get(0) == null || list.get(0).size() == 0) {
      return;
    }
    for (List<Integer> subList : list) {
      System.out.println("[" + subList.get(0) + ", " + subList.get(1) + ", " + subList.get(2) +"]");
    }
  }
}
