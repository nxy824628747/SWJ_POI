package learning;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode1546 {
    public final int maxNonOverlapping(int[] nums, int target) {
        int re = 0;
        int point = 0;
        while (point < nums.length) {
            int sum = 0;
            Set<Integer> preSums = new HashSet<Integer>();
            preSums.add(0);
            while (point < nums.length) {
                sum += nums[point];
                preSums.add(sum);
                if (preSums.contains(sum - target)) {
                    re++;
                    break;
                } else {
                    point++;
                }
            }
            point++;
        }
        return re;
    }

    public final int maxNonOverlapping1(int[] nums, int target) {
        int re = 0;
        for (int i = 0; i < nums.length; i++) {
            re += search(nums, target, i);
        }
        return re;
    }

    private final int search(int[] nums, int target, int point) {
        if (target == 0) {
            return 1;
        }
        if (point < 0 || nums[point] == Integer.MIN_VALUE) {
            return 0;
        }
        if (search(nums, target - nums[point], point - 1) > 0) {
            nums[point] = Integer.MIN_VALUE;
            return 1;
        }
        return 0;
    }
}
