package learning;

import java.util.Arrays;

public class Leetcode137 {
    public final int singleNumber(int[] nums) {
        Integer X = 0, Y = 0;
        for (int i = 0; i < nums.length; i++) {
            int Z = nums[i];
            X = (X & ~Y & ~Z) | (X & Y & ~Z) | (X & Y & Z) | (~X & ~Y & Z);
            Y = (~X & Y & ~Z) | (X & Y & ~Z) | (X & ~Y & Z);
        }
        return Y;
    }

    public final int singleNumbe2r(int[] nums) {
        Integer re = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                sum += (num >> i) & 1;
            }
            re ^= (sum % 3) << i;
        }
        return re;
    }

    public final int singleNumber1(int[] nums) {
        int once = 0, thir = 0;
        for (int i = 0; i < nums.length; i++) {
            once = ~thir & (once ^ nums[i]);
            thir = ~once & (thir ^ nums[i]);
        }
        return once;
    }

    public final int singleNumber0(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                if (nums[i] == nums[i - 1]) {
                    continue;
                }
            }
            if (i < nums.length - 1) {
                if (nums[i] == nums[i + 1]) {
                    continue;
                }
            }
            return nums[i];
        }
        return nums[0];
    }
}
