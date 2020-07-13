package learning;

public class Leetcode300 {
    public final int lengthOfLIS(int[] nums) {
        int[] cache = new int[nums.length];
        int re = 0;
        for (int i = 0; i < nums.length; i++) {
            re = Math.max(re, lengthOfLIS(nums, i, cache));
        }
        return re;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/6 11:15 下午
     * @Description 暴力法需要遍历以每个元素为头元素的最长数组的长度
     * 问题间存在递归关系，且存在大量重复计算
     * G(i) 为以第 i 个元素为头元素的最长递增数组的长度
     */
    public final int lengthOfLIS(int[] nums, int begin, int[] cache) {
        if (begin == nums.length - 1) {
            return 1;
        }
        if (cache[begin] != 0) {
            return cache[begin];
        }
        int maxLength = 1;
        for (int i = begin + 1; i < nums.length; i++) {
            if (nums[i] <= nums[begin]) {
                continue;
            }
            maxLength = Math.max(maxLength, lengthOfLIS(nums, i, cache) + 1);
        }
        cache[begin] = maxLength;
        return maxLength;
    }

    public final int lengthOfLISDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int re = 1;
        int[] cache = new int[nums.length];
        cache[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            cache[i] = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] <= nums[i]) {
                    continue;
                }
                cache[i] = Math.max(cache[i], cache[j] + 1);
            }
            re = Math.max(cache[i], re);
        }
        return re;
    }

}
