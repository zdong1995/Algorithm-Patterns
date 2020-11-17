package algorithm.array.twosum;

import java.util.*;

/**
 * Given an sorted array of integers, find two numbers that their difference equals to a target value.
 * Return a list with two number [num1, num2] that num1 is less than num2.
 */
public class TwoSumDifference {
  // Method 1: Two Pointers
  // Time O(n), Space O(1)
  public static int[] twoSumDifference(int[] array, int target) {
    if (array == null || array.length == 0) {
      return new int[0];
    }
    target = Math.abs(target); // min - max = -1 * (max - min)
    int left = 0;
    int right = 1;

    while (left < right && right < array.length) {
      int diff = array[right] - array[left];
      if (diff == target) {
        return new int[] {array[left], array[right]};
      } else if (diff < target) { // right is not big enough
        right++;
      } else { // left is a little small
        left++;
        if (left == right) { // left and right can't be the same
          right++;
        }
      }
    }
    return new int[] {-1, -1};
  }

  // Solution 2: HashSMap
  // Time O(n), Space O(n)
  public static int[] twoSumDifference2(int[] array, int target) {
    if (array == null || array.length == 0) {
      return new int[0];
    }
    Map<Integer, Integer> visited = new HashMap<>();

    for (int i = 0; i < array.length; i++) {
      // the value added into map before array[i] will be smaller than array[i]
      if (visited.containsKey(array[i] - target)) { // target > 0
        return new int[] {array[i] - target, array[i]};
      } else if (visited.containsKey(array[i] + target)) { // target < 0
        return new int[] {array[i] + target, array[i]};
      } else {
        visited.put(array[i], i);
      }
    }
    return new int[] {-1, -1};
  }

  public static void main(String[] args) {
    int[] nums = {0, 1, 4, 6, 9, 11, 16, 18, 20};
    int[] targets = {8, -3, 21, -6};
    for (int target : targets) {
      System.out.println(Arrays.toString(twoSumDifference(nums, target)));
      System.out.println(Arrays.toString(twoSumDifference2(nums, target)));
    }
  }
}
