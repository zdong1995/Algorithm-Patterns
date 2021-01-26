package algorithm.pointers.twosum;

import java.util.*;

/**
 * Design and implement a TwoSum class. It should support the following operations:
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 */
public class TwoSumDesign {
  // Method 1: HashMap -> Optimize add
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

class TwoSumSetVersion {
  // Method 2: HashMap + Set -> Optimize find
  // Time: add O(n), find O(1)
  // Space: O(n)
  Map<Integer, Integer> counter;
  Set<Integer> sums; // eager computation for all possible sums

  public TwoSumSetVersion() {
    this.counter = new HashMap<>();
    this.sums = new HashSet<>();
  }

  public void add(int number) {
    for (int num : counter.keySet()) {
      sums.add(num + number);
    }
    counter.put(number, counter.getOrDefault(number, 0) + 1);
  }

  public boolean find(int value) {
    return sums.contains(value);
  }
}

class TwoSumListVersion {
  // method 3: ArrayList + Two Pointers -> Optimized add
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

  // method 4: ArrayList Insertion Sort + Two Pointers -> Optimize find
  // Time: add O(n), find O(n)
  // Space: O(n)
  public void add4(int number) {
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

  public boolean find4(int value) {
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