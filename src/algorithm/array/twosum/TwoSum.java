package algorithm.array.twosum;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
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
}
