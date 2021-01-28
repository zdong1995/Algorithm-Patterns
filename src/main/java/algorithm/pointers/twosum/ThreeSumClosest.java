package algorithm.pointers.twosum;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target.
 * Find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 */
public class ThreeSumClosest {
    // Sort + TwoPointers
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
}
