package learning;

public class Leetcode1617 {
    int an = Integer.MIN_VALUE;

    /**
     * @Author Niuxy
     * @Date 2020/7/1
     * @Description 遍历以每个元素开头的所有可能长度的连续子数组的和
     */
    public int maxSubArray0(int[] nums) {
        int[][] cache = new int[nums.length][nums.length];
        for (int begin = 0; begin < nums.length; begin++) {
            for (int length = 1; length <= nums.length - begin; length++) {
                maxSubArrayDP(nums, begin, length, cache);
            }
        }
        return an;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/1
     * @Description 具有递归结构的问题定义，求以 begin 开头，长度为 length 的子数组的和
     */
    public int maxSubArrayDP(int[] nums, int begin, int length, int[][] cache) {
        if (length == 1) {
            an = an > nums[begin] ? an : nums[begin];
            return nums[begin];
        }
        if (cache[begin][length] != 0) {
            return cache[begin][length];
        }
        int re = maxSubArrayDP(nums, begin + 1, length - 1, cache) + nums[begin];
        cache[begin][length] = re;
        an = an > re ? an : re;
        return re;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/1
     * @Description 转为 递推，DP 解法
     */
    public final int maxSubArray(int[] nums) {
        int an = nums[0];
        int[][] cache = new int[nums.length][nums.length + 1];
        for (int i = 1; i < nums.length; i++) {
            cache[i][1] = nums[i];
            an = an > cache[i][1] ? an : cache[i][1];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 2; j <= nums.length - i; j++) {
                cache[i][j] = cache[i + 1][j - 1] + nums[i];
                an = an > cache[i][j] ? an : cache[i][j];
            }
        }
        return an;
    }


    /**
     * @Author Niuxy
     * @Date 2020/7/1
     * @Description dp 优化空间复杂度
     */
    public final int maxSubArray2(int[] nums) {
        int an = nums[0];
        int[] thisLevel = new int[nums.length + 1];
        int[] beforeLevel = new int[nums.length + 1];
        for (int i = 1; i < nums.length; i++) {
            an = an > nums[i] ? an : nums[i];
        }
        beforeLevel[1] = nums[nums.length - 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 2; j <= nums.length - i; j++) {
                thisLevel[j] = beforeLevel[j - 1] + nums[i];
                an = an > thisLevel[j] ? an : thisLevel[j];
            }
            //缓存换位,beforeLevel 复用
            int[] temp = beforeLevel;
            beforeLevel = thisLevel;
            thisLevel = beforeLevel;
            beforeLevel[1] = nums[i];
        }
        return an;
    }

}
