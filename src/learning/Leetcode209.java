package learning;

public class Leetcode209 {
    /**
     * @Author Niuxy
     * @Date 2020/7/4 1:03 上午
     * @Description 朴素解法
     */
    public final int minSubArrayLen0(int s, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    minLength = Math.min(minLength, j - i + 1);
                    break;
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }


    int minLength = Integer.MAX_VALUE;
    /**
     * @Author Niuxy
     * @Date 2020/7/4 12:57 上午
     * @Description 上述暴力解法本质是在求以各元素开头的子数组的和
     * 计算过程是存在大量重复计算的，很明显的可以想到
     * 求子数组的区间和的函数具有递归结构
     */
    public final int subArray(int[] nums, int begin, int end, int[][] cache, int s) {
        if (begin == end) {
            if (nums[begin] >= s) {
                minLength = 1;
            }
            return nums[begin];
        }
        if (cache[begin][end] != 0) {
            return cache[begin][end];
        }
        int re = subArray(nums, begin, end - 1, cache, s) + nums[end];
        if (re >= s) {
            minLength = Math.min(minLength, end - begin + 1);
        }
        cache[begin][end] = re;
        return re;
    }

    public final int minSubArrayLen(int s, int[] nums) {
        int[][] cache = new int[nums.length][nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i; j < nums.length; j++) {
                subArray(nums, i, j, cache, s);
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public final int dp(int s, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int[][] cache = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            cache[i][i] = nums[i];
            if (cache[i][i] >= s) {
                return 1;
            }
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                cache[i][j] = cache[i][j - 1] + nums[j];
                if (cache[i][j] >= s) {
                    minLength = Math.min(minLength, j - i + 1);
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public final int xxx(int[] nums, int s) {
        int maxFlag = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                maxFlag = i;
                max = nums[i];
            }
        }
        //滑尺指针
        int left = maxFlag - 1;
        int right = maxFlag + 1;
        int sum = max;
        int re = 0;
        while (sum < s) {
            if (left < 0 && right >= nums.length) {
                return 0;
            }
            if (left < 0) {
                sum += nums[right];
                right++;
                continue;
            }
            if (right >= nums.length) {
                sum += nums[left];
                left--;
                continue;
            }
            if (nums[left] >= nums[right]) {
                sum += nums[right];
                right++;
                continue;
            }
            if (nums[left] < nums[right]) {
                sum += nums[left];
                left--;
                continue;
            }
        }
        left = left >= nums.length ? nums.length - 1 : left;
        right = right < 0 ? 0 : right;
        return right - left + 1;
    }
}