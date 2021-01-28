package algorithm.pointers.twosum;

import java.util.*;

/**
 * Given an array of positive integers representing side length of triangle.
 * Take three numbers each time to construct a triangle.
 * Count how many triangles we could construct using the given numbers.
 */
public class ThreeSumGreater {
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
}
