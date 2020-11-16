package algorithm.array.twosum;

import java.util.*;

/**
 * Design and implement a TwoSum class. It should support the following operations:
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 */
public class TwoSumDesign {
  // Method 1: HashSet
  // Time: add O(1), find O(n)
  // Space: O(n)
  Map<Integer, Integer> counter;

  public TwoSumDesign() {
    this.counter = new HashMap<>();
  }

  public void add(int number) {
    counter.put(number, counter.getOrDefault(number, 0) + 1);
  }

  public boolean find(int value) {
    for (int num : counter.keySet()) {
      int diff = value - num;
      int countNeeded = num == diff ? 2 : 1;
      if (counter.getOrDefault(diff, 0) >= countNeeded ) {
        return true;
      }
    }
    return false;
  }
}

class TwoSumListVersion {
  // method 2: ArrayList + Two Pointers -> Optimized add
  // Time: add O(1), find O(nlogn)
  // Space: O(n)
  List<Integer> nums;

  public TwoSumListVersion() {
    this.nums = new ArrayList<>();
  }

  public void add(int number) {
    nums.add(number);
  }

  public boolean find(int value) {
    Collections.sort(nums);
    int left = 0;
    int right = nums.size() - 1;

    while (left < right) {
      int sum = nums.get(left) + nums.get(right);
      if (sum == value) {
        return true;
      } else if (sum > value) {
        right--;
      } else {
        left++;
      }
    }
    return false;
  }

  // method 3: ArrayList Insertion Sort + Two Pointers -> Optimize find
  // Time: add O(n), find O(n)
  // Space: O(n)
  public void add3(int number) {
    nums.add(number);
    int idx = nums.size() - 1;
    // insertion sort to swap until maintain the correct order
    while (idx > 0 && nums.get(idx - 1) > nums.get(idx)) { // iterate until idx = 1
      int tmp = nums.get(idx - 1);
      nums.set(idx - 1, nums.get(idx));
      nums.set(idx, tmp);
      idx--;
    }
  }

  public boolean find3(int value) {
    int left = 0;
    int right = nums.size() - 1;

    while (left < right) {
      int sum = nums.get(left) + nums.get(right);
      if (sum == value) {
        return true;
      } else if (sum > value) {
        right--;
      } else {
        left++;
      }
    }
    return false;
  }
}