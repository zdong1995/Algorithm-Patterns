package algorithm.array.twosum;

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
    if (array == null || array.length == 0) {
      return res;
    }
    Arrays.sort(array);

    for (int i = 0; i < array.length - 2; i++) {
      // run two sum to find pair equal to - target in range (i, n-1]
      if (i > 0 && array[i] == array[i - 1]) { // skip duplicate start index
        continue;
      }
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

  // Method 2: Sort + HashMap to store first two sum
  // Time: O(n^2 + nlogn) = O(n^2), Space = O(n) for sort + map
  public static List<List<Integer>> threeSum2(int[] array, int target) {
    List<List<Integer>> res = new ArrayList<>();
    if (array == null || array.length == 0) {
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
    int[] nums = {0, 1, -4, 6, 9, -11, 16, 8, 5, 0};
    int[] targets = {5, 0, -10, 16};
    for (int target : targets) {
      System.out.println("Target is " + target);
      print(threeSum(nums, target));
    }
    for (int target : targets) {
      System.out.println("Target is " + target);
      print(threeSum2(nums, target));
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
