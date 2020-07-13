package learning;

public class Leetcode1143 {
    /**
     * @Author Niuxy
     * @Date 2020/7/9 11:52 下午
     * @Description 暴力解法
     */
    public final int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequence(text1, 0, text2, 0, new int[text1.length()][text2.length()]);
    }

    public final int longestCommonSubsequence(String text1, int point1, String text2, int point2, int[][] cache) {
        if ((point1 == text1.length()) || (point2 == text2.length())) {
            return 0;
        }
        if (cache[point1][point2] != 0) {
            return cache[point1][point2];
        }
        int re = 0;
        if (text1.charAt(point1) == text2.charAt(point2)) {
            re = longestCommonSubsequence(text1, point1 + 1, text2, point2 + 1, cache) + 1;
        } else {
            int re0 = longestCommonSubsequence(text1, point1, text2, point2 + 1, cache);
            int re1 = longestCommonSubsequence(text1, point1 + 1, text2, point2 + 1, cache);
            int re2 = longestCommonSubsequence(text1, point1 + 1, text2, point2, cache);
            re = Math.max(re0, re1);
            re = Math.max(re, re2);
        }
        cache[point1][point2] = re;
        return re;
    }

    public final int longestCommonSubsequenceDP(String text1, String text2) {
        int length1 = text1.length();
        int length2 = text2.length();
        int[][] dp = new int[length1][length2];
        if (length1 == 0 || length2 == 0) {
            return 0;
        }
        dp[length1 - 1][length2 - 1] = text1.charAt(length1 - 1) == text2.charAt(length2 - 1) ? 1 : 0;
        for (int i = length2 - 2; i >= 0; i--) {
            dp[length1 - 1][i] = text1.charAt(length1 - 1) == text2.charAt(i) || dp[length1 - 1][i + 1] == 1 ? 1 : 0;
        }
        for (int i = length1 - 2; i >= 0; i--) {
            dp[i][length2 - 1] = text1.charAt(i) == text2.charAt(length2 - 1) || dp[i + 1][length2 - 1] == 1 ? 1 : 0;
        }
        for (int i = length1 - 2; i >= 0; i--) {
            for (int j = length2 - 2; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j + 1]);
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                }
            }
        }
        return dp[0][0];
    }

}
