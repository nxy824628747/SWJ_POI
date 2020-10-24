package learning;

public class Leetcode136 {
    public final int singleNumber(int[] nums) {
        int re = 0;
        for (int i = 0; i < nums.length; i++) {
            re ^= nums[i];
        }
        return re;
    }

    public final int singleNumber0(int[] nums) {
        int x = 0, y = 0;
        for (int i = 0; i < nums.length; i++) {
            int z = nums[i];
            x = (x & ~y & ~z) | (z & (x ^ y));
            y = (~x & (y ^ z)) | (x & ~y & z);
        }
        return y;
    }
}
