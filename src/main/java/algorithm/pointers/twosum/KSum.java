package algorithm.pointers.twosum;

import java.util.*;

/**
 * Given an array of integers, an integer target and an integer K.
 * Find all unique pairs with size = K in the array which gives the sum of target.
 */
public class KSum {
  public static List<List<Integer>> kSum(int[] array, int k, int target) {
    if (array == null || array.length < k || k < 2) {
      return new ArrayList<>();
    }
    Arrays.sort(array); // sort array in main function
    return findKSum(array, k, target, 0);
  }

  private static List<List<Integer>> findKSum(int[] array, int k, int target, int start) {
    // assumption: array.length > k && k >= 2
    // base case
    if (k == 2) { // 2 sum
      return findTwoSum(array, target, start);
    }
    // recursive reduction
    List<List<Integer>> res = new ArrayList<>();
    for (int i = start; i < array.length - k + 1; i++) {
      if (i > start && array[i] == array[i - 1]) { // skip duplicate for the fixed first number
        continue;
      }
      List<List<Integer>> subPairs = findKSum(array, k - 1, target - array[i], i + 1);
      for (List<Integer> pair : subPairs) {
        pair.add(0, array[i]); // k-1 sum + current fixed number -> k sum
      }
      res.addAll(subPairs);
    }
    return res;
  }

  private static List<List<Integer>> findTwoSum(int[] array, int target, int start) {
    // find all unique two sum pairs with sum to target
    // assumption: array is sorted, array.length > 2
    List<List<Integer>> res = new ArrayList<>();
    int left = start;
    int right = array.length - 1;
    while (left < right) {
      int sum = array[left] + array[right];
      if (sum == target) {
        res.add(new ArrayList<>(Arrays.asList(array[left++], array[right--])));
        // skip duplicate
        while (left < right && array[left] == array[left - 1]) {
          left++;
        }
        while (left < right && array[right] == array[right + 1]) {
          right--;
        }
      } else if (sum > target) {
        right--;
      } else {
        left++;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    int[] nums = {0, 1, 1, -4, 0, 6, 9, -11, 16, 8, 5, 0, 0};
    int[] targets = {5, 0, -10, 16};
    for (int target : targets) {
      System.out.println("Target is " + target);
      print(kSum(nums, 4, target));
    }
    for (int target : targets) {
      System.out.println("Target is " + target);
      print(kSum(nums, 5, target));
    }
  }

  private static void print(List<List<Integer>> list) {
    if (list == null || list.size() == 0 || list.get(0) == null || list.get(0).size() == 0) {
      return;
    }
    for (List<Integer> subList : list) {
      System.out.println(subList);
    }
  }
}
