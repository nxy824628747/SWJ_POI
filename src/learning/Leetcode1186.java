package learning;

public class Leetcode1186 {
    /**
     * @Author Niuxy
     * @Date 2020/7/7 3:49 下午
     * @Description 暴力解法, 重复计算很多
     */
    public final int maximumSum1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    min = Math.min(arr[k], min);
                }
                if (j != i) {
                    sum = Math.max(sum, sum - min);
                }
                max = Math.max(max, sum);
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    //分治解法
    public final int maximumSum2(int[] arr) {
        int an = Integer.MIN_VALUE;
        int[][][] cache = new int[arr.length][arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int[] reArr = maximumSum0(arr, i, j, cache);
                int reInt = Math.max(reArr[0], reArr[1]);
                an = Math.max(reInt, an);
            }
        }
        return an;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/7 8:14 下午
     * @Description 从上面的暴力解法可以看出，求解过程中存在很多关于区间结果的重复计算
     * 将问题定义为求取区间结果，尝试寻找区间结果间的递归结构
     * 问题间若想产生关联，除计算删除一个的最大值外，还要记录不删除的数组和
     */
    public final int[] maximumSum0(int[] arr, int begin, int end, int[][][] cache) {
        if (begin == end) {
            return new int[]{arr[begin], arr[begin]};
        }
        if (end < begin) {
            return new int[]{0, 0};
        }
        if (cache[begin][end][0] != 0) {
            return cache[begin][end];
        }
        int min = Integer.MAX_VALUE;
        int minFlag = begin;
        for (int i = begin; i <= end; i++) {
            if (arr[i] < arr[minFlag]) {
                minFlag = i;
                min = arr[i];
            }
        }
        int temp = maximumSum0(arr, begin, minFlag - 1, cache)[0] + maximumSum0(arr, minFlag + 1, end, cache)[0];
        cache[begin][end] = new int[]{temp + arr[minFlag], temp};
        return cache[begin][end];
    }

    // DP 解法 1
    public final int maximumSumDP0(int[] arr) {
        int[][][] cache = new int[arr.length][arr.length][2];
        int an = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            cache[i][i] = new int[]{arr[i], arr[i]};
            an = Math.max(an, arr[i]);
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < arr.length; j++) {
                int tempMin = arr[i];
                int minFlag = i;
                for (int k = i; k <= j; k++) {
                    if (arr[k] < arr[minFlag]) {
                        minFlag = k;
                        tempMin = arr[k];
                    }
                }
                int left = minFlag - 1 < i ? 0 : cache[i][minFlag - 1][0];
                int right = minFlag + 1 > j ? 0 : cache[minFlag + 1][j][0];
                int temp = left + right;
                cache[i][j][0] = temp + arr[minFlag];
                cache[i][j][1] = temp;
                an = Math.max(an, cache[i][j][0]);
                an = Math.max(an, cache[i][j][1]);
            }
        }
        return an;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/7 11:39 下午
     * @Description 将问题的定义再向上层扩大
     * 暴力解法中，遍历步骤大致分两步：1.以每一个元素开头的数组--->2.以每一个元素开头所有可能长度的数组
     * 步骤 2 是明显的存在大量重复计算的，上面的解法也是对步骤 2 进行了分治缓存
     * 但缓存的粒度太细，导致所需的额外空间太多
     * 向上一层，直接从步骤 1 寻找状态转移关系与缓存坐标
     * 缓存的粒度变粗，所需额外空间变小，但效率并不一定会变差
     * G(begin) 返回一个二维数组 re ，问题空间为以 arr[begin] 开头的子数组
     * re[0] 为删除 0 次的最大值
     * re[1] 为删除 1 次的最大值
     * 删除 0 次有两种可能：从 begin 元素与后续元素的和；begin 元素本身的值。
     * 删除 1 次也有，两种可能：删除 begin 元素；不删除 begin 元素，从后续元素挑一个删除。
     * 避免重复计算的方式有两种：使用缓存存下问题的计算结果；问题的结果被上层问题直接使用。
     */
    public final int[] maximumSumDP3(int[] arr, int begin) {
        if (begin == arr.length - 1) {
            max = Math.max(max, arr[begin]);
            return new int[]{arr[begin], 0};
        }
        int[] next = maximumSumDP3(arr, begin + 1);
        //不删除本元素
        int re0 = next[1] + arr[begin];
        //删除本元素
        int re1 = next[0];
        int[] re = new int[]{Math.max(re1 + arr[begin], arr[begin]), Math.max(re1, re0)};
        max = Math.max(max, re[0]);
        max = Math.max(max, re[1]);
        return re;
    }

    int max = Integer.MIN_VALUE;

    public final int maximumSum3(int[] arr) {
        maximumSumDP3(arr, 0);
        return max;
    }


    public final int maximumSum(int[] arr) {
        int max = arr[arr.length - 1];
        int[][] dp = new int[arr.length][2];
        dp[arr.length - 1][0] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            dp[i][0] = Math.max(arr[i], arr[i] + dp[i + 1][0]);
            max = Math.max(max, dp[i][0]);
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            dp[i][1] = Math.max(dp[i + 1][1] + arr[i], dp[i + 1][0]);
            max = Math.max(max, dp[i][1]);
        }
        return max;
    }


}
