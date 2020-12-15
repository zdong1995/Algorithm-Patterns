# 1.2 Two Sum Pattern

## I. Summary

#### ⌨ 15 problems in this section.

There will be two main method to solve the Two Sum Pattern problem: Two Points and HashMap.

The solution depends on how we clarify the problem:

* Sorted array: Prefer two pointers.
* Unsorted array: One pass scan and store in HashMap will be better.
* With duplicates: HashMap may need extra HashSet to deduplicate, Two Pointers may need while loop to skip duplicates.

### 1. Two Pointers

Use two pointers `left` and `right` start from beginning and the end. Each time check the sum `array[left] + array[right]` comparing to target.

* If `sum == target` : return solution
* If `sum > target` : sum is large decrease the right number `right--`
* If `sum < target` : sum is small, increase the left number `left++`

Time Complexity: O\(n\), Space Complexity: O\(1\)

### 2. HashMap

Iterate over the whole array, for each number check whether `target - number` is in the HashMap.

* If `map.contains(target - number)` : return solution
* Else : add current number to the HashMap

Time Complexity: O\(n\), Space Complexity: O\(n\)

In the following I only list one solution for some problems. If you are interested in different solutions, you can find [my source code of Two Sum Pattern problems](https://github.com/zdong1995/coding-interview/tree/master/src/main/java/algorithm/array/twosum).

## II. Two Sum

### 2 Sum - Unsorted

> [LC 1](https://leetcode.com/problems/two-sum/): Given an unsorted array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

HashMap to store the visited element, for each iteration, check whether the complement in the map.

```java
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
```

### 2 Sum - Sorted

> [LC 167](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/): Given an sorted array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

Two pointers from begin and end, decrement right when `sum > target`, increment left when `sum < target`.

```java
// Two Pointers: Time O(n), Space O(1)
public int[] twoSumSorted(int[] nums, int target) {
  if (nums == null || nums.length == 0) {
    return new int[] {-1, -1};
  }
  int left = 0;
  int right = nums.length - 1;
  while (left < right) {
    int sum = nums[left] + nums[right];
    if (sum == target) {
      return new int[] {left, right};
    } else if (sum > target) {
      right--;
    } else {
      left++;
    }
  }
  return new int[] {-1, -1};
}
```

### 2 Sum - All Pairs Indices

> Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.

HashMap to store number and list of indices, for each iteration, check the complement and enumerate all possible indices pairs of current number pair.

```java
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
```

### 2 Sum - All Unique Pairs

> [LT 587](https://www.lintcode.com/problem/two-sum-unique-pairs/): Given an unsorted array with duplicates and target value. Find all pairs of elements that sum to the target number. Return all the distinct pairs of values.

#### Solution 1: Sort + Two Pointers
Use two pointers to find the pairs, need to skip duplicates after finding pairs

```java
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
```

#### Solution 2: HashSet

One HashSet to record vistied number and another HashSet to deduplicate pair

```java
// Solution 2: HashSet
// Time O(n), Space O(n)
public List<List<Integer>> twoSumAllUniquePairsHashMap(int[] array, int target) {
  List<List<Integer>> res = new ArrayList<>();
  if (array == null || array.length == 0) {
    return res;
  }

  Set<Integer> visited = new HashSet<>(); // record unique numbers visited
  Set<Integer> used = new HashSet<>(); // indices used in solution pairs to deduplicate

  for (int num : array) {
    int diff = target - num;
    if (visited.contains(diff)) {
      // need to check whether this pair is already used before
      if (!used.contains(num) && !used.contains(diff)) { // haven't found current pair
        res.add(Arrays.asList(num, diff));
        used.add(num);
        used.add(diff);
      }
    } else {
      visited.add(num);
    }
  }
  return res;
}
```

### 2 Sum - Greater

> [LT 443](https://www.lintcode.com/problem/two-sum-greater-than-target): Given an array of integers, find how many pairs in the array such that their sum is bigger than a specific target number. Return the number of pairs.

Sort + Two Pointers, when sum is greater than target, than for current right, the pair sum with left in range `[left, right)` will all be greater than target.

```java
// Sort + TwoPointers
// Time O(nlogn + n) = O(n), Space O(n)
public int twoSumGreater(int[] array, int target) {
  if (array == null || array.length == 0) {
    return 0;
  }
  Arrays.sort(array);
  int left = 0;
  int right = array.length - 1;
  int count = 0;

  while (left < right) {
    int sum = array[left] + array[right];
    if (sum > target) {
      // all the pair that left in the range [left, right)
      // will be greater than target
      count += right - left;
      right--;
    } else {
      left++;
    }
  }
  return count;
}
```

### 2 Sum - Smaller

> [LT 609](https://www.lintcode.com/problem/two-sum-less-than-or-equal-to-target): Given an array of integers, find how many pairs in the array such that their sum is smaller than a specific target number. Return the number of pairs.

Sort + Two Pointers, when sum is smaller than target, than for current left, the pair sum with right in range `(left, right]` will all be smaller than target.

```java
// Sort + TwoPointers
// Time O(nlogn + n) = O(n), Space O(n)
public int twoSumSmaller(int[] array, int target) {
  if (array == null || array.length == 0) {
    return 0;
  }
  Arrays.sort(array);
  int left = 0;
  int right = array.length - 1;
  int count = 0;

  while (left < right) {
    int sum = array[left] + array[right];
    if (sum < target) {
      // all the pair that right in the range (left, right]
      // will be smaller than target
      count += right - left;
      left++;
    } else {
      right--;
    }
  }
  return count;
}
```

### 2 Sum - Closest

> [LT 533](https://www.lintcode.com/problem/two-sum-closest-to-target): Find the pair of elements in a given array that sum to a value that is closest to the given target number. Return the values of the two numbers.

Sort + Two Pointers, maintain one global min difference, update minDiff if the difference between sum and target is smaller.

```java
// Sort + Two Pointers
// Time O(nlogn + n) = O(nlogn), Space O(n)
public int twoSumClosest(int[] array, int target) {
  if (array == null || array.length == 0) {
    return -1;
  }
  Arrays.sort(array);

  int minDiff = Integer.MAX_VALUE;

  int left = 0;
  int right = array.length - 1;

  while (left < right) {
    int sum = array[left] + array[right];
    minDiff = Math.min(Math.abs(target - sum), minDiff);
    if (sum == target) {
      return 0;
    } else if (sum < target) {
      left++;
    } else {
      right--;
    }
  }
  return minDiff == Integer.MAX_VALUE ? - 1 : minDiff;
}
```

### 2 Sum - Data Structure Design

> [LC 170](https://leetcode.com/problems/two-sum-iii-data-structure-design/): Design and implement a TwoSum class. It should support the following operations:

* **add**: Add the number to an internal data structure.
* **find**:Find if there exists any pair of numbers which sum is equal to the value.

Solutions to optimize `add()`:

* Solution 1: HashMap for counter, add O\(1\) and find O\(n\)
* Solution 2: ArrayList + Sort + Two Pointers to find, add O\(1\) and find O\(nlogn\)

Solutions to optimize `find()`:

* Solution 1: HashMap for counter + Set for all sums, add O\(n\) and find O\(1\) 
* Solution 2: ArrayList + Insertion Sort + Two Pointers to find, add O\(n\) and find O\(n\)

```java
public class TwoSumDesign {
  // HashMap -> Optimize add
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
```

### 2 Sum - Difference

> [LT 610](https://www.lintcode.com/problem/two-sum-difference-equals-to-target): Given an sorted array of integers, find two numbers that their difference equals to a target value. Return a list with two number \[num1, num2\] that num1 is less than num2.

- Solution 1: Two Pointers similar to sliding window, guarantee `left < right` and not out of bound
- Solution 2: HashMap and check whether `array[i] + target` or `array[i] - target` in the map

```java
// Two Pointers: Time O(n), Space O(1)
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
```

## III. Three Sum

### 3 Sum

> [LC 15](https://leetcode.com/problems/3sum/): Given an unsorted array of integers. Find all unique triplets in the array which gives the sum of target.

#### Solution 1: Sort + Two Pointers
For each fixed number `array[i]`, find two sum that sums to `target - array[i]`

```java
// Method 1: Sort + For loop 2 Sum
// Time O(n^2 + nlogn) = O(n^2), Space O(n) for sort
public static List<List<Integer>> threeSum(int[] array, int target) {
  List<List<Integer>> res = new ArrayList<>();
  if (array == null || array.length < 3) {
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
```

#### Solution 2: HashSet without Sorting
For each fixed number `array[i]`, use two HashSet to find two sum that sums to `target - array[i]` , need one more HashSet to dedupliate fixed number

```java
// Method 2: HashSet without Sort
// Time: O(n^2) Space: O(n) for set
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
```

#### Solution 3: Sort + HashMap
Count the occurrence of each number, for for loop to find a triplet that `array[left] <= array[right] <= (target - array[left] - array[right])` in order to deduplicate

```java
// Method 2: Sort + HashMap to store first two sum
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
```

### 3 Sum - Greater \(Count Triangles\)

> [LC 611](https://leetcode.com/problems/valid-triangle-number): Given an array of positive integers representing side length of triangle. Take three numbers each time to construct a triangle. Count how many triangles we could construct using the given numbers.

Sort + Two Pointers, for each fixed number `array[i]`, find two sum that sums greater than`target - array[i]`. When sum is greater than `target - array[i]`, than for current right, the pair sum with left in range `[left, right)` will all be greater than target.

```java
// Sort + TwoPointers
// Time O(n^2 + nlogn) = O(n^2), Space O(n) for sort
public int threeSumGreater(int[] array, int target) {
  if (array == null || array.length < 3) {
    return 0;
  }
  Arrays.sort(array);
  int count = 0;

  for (int i = 0; i < array.length; i++) {
    count += countTwoSumGreater(array, i, target - array[i]);
  }
  return count;
}

private int countTwoSumGreater(int[] array, int start, int target) {
  // count number of pairs that sum to target
  int count = 0;
  int left = start + 1;
  int right = array.length - 1;
  while (left < right) {
    int sum = array[left] + array[right];
    if (sum > target) {
      count += right - left;
    } else {
      left++;
    }
  }
  return count;
}
```

### 3 Sum - Smaller

> [LC 259](https://leetcode.com/problems/3sum-smaller/): Given an array of n integers nums and an integer target, find the number of triplets whose sum is smaller than target.

Sort + Two Pointers, for each fixed number `array[i]`, find two sum that sums smaller than`target - array[i]`. When sum is greater than `target - array[i]`, than for current left, the pair sum with right in range `(left, right]` will all be smaller than target.

```java
// Sort + Two Pointers
// Time O(n^2 + nlogn) = O(n^2), Space O(n) for sort
public int threeSumSmaller(int[] array, int target) {
  if (array == null || array.length < 3) {
    return 0;
  }
  Arrays.sort(array);
  int count = 0;

  for (int i = 0; i < array.length; i++) {
    count += countTwoSumSmaller(array, i, target - array[i]);
  }
  return count;
}

private int countTwoSumSmaller(int[] array, int start, int target) {
  // count number of pairs that sum to target
  int count = 0;
  int left = start + 1;
  int right = array.length - 1;
  while (left < right) {
    int sum = array[left] + array[right];
    if (sum < target) {
      count += right - left;
    } else {
      right--;
    }
  }
  return count;
}
```

### 3 Sum - Closest

> [LC 16](https://leetcode.com/problems/3sum-closest/): Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers.

Sort + Two Pointers, for each fixed number `array[i]`, find two sum that sums closest to`target - array[i]`. The trick here is to maintain a signed global `minDiff`, and we keep the same strategy to update it as `minDiff = target - sum`. Thus whether the `minDiff` is positive or negative, the final result will only be the `target - minDiff`, so each time we just need to update one variable, the global `minDiff`.

```java
// Sort + Two Pointers
// Time O(n^2 + nlogn) = O(n^2), Space O(n) for sort
public int threeSumCloset(int[] array, int target) {
  if (array == null || array.length == 0) {
    return 0;
  }
  int minDiff = Integer.MAX_VALUE; // only need to maintain global minDiff with + or -
  Arrays.sort(array);

  for (int i = 0; i < array.length; i++) {
    // find two sum closest to target - array[i]
    int left = i + 1;
    int right = array.length - 1;
    while (left < right) {
      int sum = array[left] + array[right] + array[i];
      // update minDiff for each pair
      if (Math.abs(target - sum) < Math.abs(minDiff)) {
        minDiff = target - sum; // minDiff will be positive or negative
      }
      if (sum == target) {
        return target;
      } else if (sum > target) {
        right--;
      } else {
        left++;
      }
    }
  }
  return target - minDiff; // sum will always be target - minDiff
}
```

## IV. Four Sum and K Sum

### Four Sum

> [LC 18](https://leetcode.com/problems/4sum/): Given an array of integers and an integer target. Find all unique quadruplets in the array which gives the sum of target.

#### Solution 1: Sort + Two Pointers to reduce to 3 Sum.

For each fixed number `array[i]`, find three sum equals to`target - array[i]`. Since sorting helped us to let duplicate number to be adjacent, For `array[i]` we need check to skip duplicate. The same procedure in the Three Sum helper function.

```java
// Method 1: Sort + Two Pointer for Three Sum
// Time O(n^3), Space O(n) for sort
public List<List<Integer>> fourSum2(int[] nums, int target) {
  Arrays.sort(nums);
  List<List<Integer>> res = new ArrayList<>();

  // enumerate all possible first number in the quadruplet
  for (int i = 0; i < nums.length - 3; i++) {
    if (i > 0 && nums[i] == nums[i - 1]) { // skip all duplicate first number
      continue;
    }
    findThreeSum(res, nums, target - nums[i], i);
  }
  return res;
}

private void findThreeSum(List<List<Integer>> res, int[] nums, int target, int start) {
  for (int i = start + 1; i < nums.length; i++) {
    if (i > start + 1 && nums[i] == nums[i - 1]) { // skip duplicate start index
      continue;
    }
    // find two sum pairs equal to target - nums[i]
    int left = i + 1;
    int right = nums.length - 1;
    while (left < right) {
      int sum = nums[left] + nums[right];
      if (sum == target - nums[i]) {
        res.add(Arrays.asList(nums[start], nums[i], nums[left++], nums[right--]));
        // skip duplicate
        while (left < right && nums[left] == nums[left - 1]) {
          left++;
        }
        while (left < right && nums[right] == nums[right + 1]) {
          right--;
        }
      } else if (sum > target - nums[i]) {
        right--;
      } else {
        left++;
      }
    }
  }
}
```

#### Solution 2: Sort + HashMap to reduce to two 2 Sum problem. 

The key point is how to deduplicate to find two pairs of two-sum pair that sum to target. Since sorting helped us maintain the order, we just need to guarantee for pair 1 `(left1, right1)` and pair 2 `(left2, right2)`, we strictly have the order `left1 < right1 < left2 < right2`. Then the quadruples will be unique.

Thus we need two pass with O\(n^2\) time complexity, the first pass is to build Two Sum pair map, and the second pass is to **iterate all possible left position of second pair**. Since we may have difference pair for the same Two-Sum value, the map should use a list to store all possible unqiue pairs of Two-Sum.

```java
// Method 2: HashMap + HashSet
// Time O(n^2), Space O(n)
public List<List<Integer>> fourSum(int[] nums, int target) {
  Set<List<Integer>> set = new HashSet<>();
  Map<Integer, List<List<Integer>>> map = new HashMap<>();
  Arrays.sort(nums);
  // find the 2 sum pair as first pair in the 4 sum solution
  // i1 < j1 < i2 < j2 in pair (i1, j1, i2, j2), thus i1 < n-3, j1 < n-2
  for (int i = 0; i < nums.length - 3; i++) { // left index of the first pair
    for (int j = i + 1; j < nums.length - 2; j++) {
      int curSum = nums[i] + nums[j];
      List<List<Integer>> pairs = map.getOrDefault(curSum, new ArrayList<>());
      pairs.add(Arrays.asList(i, j));
      map.put(curSum, pairs);
    }
  }
  // deal with the second pair in the 4 sum solution
  // i2 > 2, j2 > 3
  for (int i = 2; i < nums.length - 1; i++) {
    for (int j = i + 1; j < nums.length; j++) {
      int curSum = nums[i] + nums[j];
      List<List<Integer>> firstPairs = map.get(target - curSum);
      if (firstPairs == null) { // can't find complement pair to current pair
        continue;
      }
      for (List<Integer> pair : firstPairs) {
        if (pair.get(1) < i) {
          // the right index of complement pair should be smaller
          // than the current pair's left index, to avoid duplicate
          set.add(Arrays.asList(nums[pair.get(0)], nums[pair.get(1)], nums[i], nums[j]));
        }
      }
    }
  }
  return new ArrayList<>(set);
}
```

### K Sum

Similar to 4 Sum problem, we can find that 4 Sum can be reduced to 3 Sum, and 3 Sum can be reduced to 2 Sum. So a general way to find unique K Sum pairs is to recursive reduction.

The recusion function `findKSum(int[] array, int k, int target, int start)` will return a list of size K pairs that sum to target. And the index of each element is starting from `start + 1`.

* Base case: `k == 2` for Two Sum.
* Recursion rule: For the result of subproblem \(K-1\) Sum, we need to add current fixed number to each list, then we will have a list of K Sum.

```java
// Reduction to Two Sum
// Time : O(n^(k-1))
public List<List<Integer>> kSum(int[] array, int k, int target) {
  if (array == null || array.length < k || k < 2) {
    return new ArrayList<>();
  }
  Arrays.sort(array); // sort array in main function
  return findKSum(array, k, target, 0);
}

private List<List<Integer>> findKSum(int[] array, int k, int target, int start) {
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

private List<List<Integer>> findTwoSum(int[] array, int target, int start) {
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
```
