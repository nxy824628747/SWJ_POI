package learning;

public class Leetcode813 {

    public final double largestSumOfAveragesDP(int[] A, int K) {
        int len = A.length;
        double[][][] dp = new double[len][len][K + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                dp[i][j][1] = getAbs(A, i, j);
                for (int k = 2; k <= Math.min(K, j - i + 1); k++) {
                    double re = Integer.MIN_VALUE;
                    for (int h = i; h < j; h++) {
                        double temp = getAbs(A, i, h) + dp[h + 1][j][k - 1];
                        re = Math.max(re, temp);
                        dp[i][j][k] = re;
                    }
                }
            }
        }
        return dp[0][len - 1][K];
    }


    public final double largestSumOfAverages(int[] A, int K) {
        int len = A.length;
        double re = largest(A, K, 0, len - 1, new double[len][len][K + 1]);
        return re == Integer.MIN_VALUE ? getAbs(A, 0, A.length - 1) : re;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/22 10:50 下午
     * @Description 视角放在问题层面，而不是穷举层面，用问题解决自己
     */
    private final double largest(int[] A, int k, int begin, int end, double[][][] cache) {
        if (k == 1) {
            return getAbs(A, begin, end);
        }
        if (k > end - begin + 1) {
            return 0;
        }
        if (cache[begin][end][k] != 0) {
            return cache[begin][end][k];
        }
        double re = Integer.MIN_VALUE;
        int nextK = k - 1;
        for (int i = begin; i < end; i++) {
            double temp = getAbs(A, begin, i) + largest(A, nextK, i + 1, end, cache);
            re = Math.max(temp, re);
        }
        cache[begin][end][k] = re;
        return re;
    }

    private final double getAbs(int[] A, int begin, int end) {
        return getSum(A, begin, end) / (end - begin + 1);
    }

    private final double getSum(int[] A, int begin, int end) {
        int re = 0;
        for (int i = begin; i <= end; i++) {
            re += A[i];
        }
        return re;
    }
}
