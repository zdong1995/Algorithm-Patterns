package algorithm.pointers.twosum;

import java.util.*;

/**
 * Given an array of integers and an integer target.
 * Find all unique quadruplets in the array which gives the sum of target.
 */
public class FourSum {
    // Method 1: HashMap + HashSet
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

    // Method 2: Sort + Two Pointer for Three Sum
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
}
