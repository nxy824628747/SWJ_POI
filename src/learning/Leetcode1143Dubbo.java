package learning;

public class Leetcode1143Dubbo {

    public static void main(String[] args) {
        Leetcode1143Dubbo l = new Leetcode1143Dubbo();
        String str1 = "abcde";
        String str2 = "ace";
        l.longestCommonSubsequence(str1, str2);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return searchSame(text1, text2, 0, 0, new int[text1.length()][text2.length()]);
    }
    /**
     * @Author Niuxy
     * @Date 2020/10/2 7:12 下午
     * @Description
     * 最长公共子序列实现中，每个节点将面临四种不同的选择情况，设 N=Max(str1.length,str2.length)，时间复杂度为 3 的 N 次幂
     * 函数 searchSame( point1，point2 ) 表示 在 point1，point2 指向的字节前的字符串最大的重合长度
     * 则可以发现，递归过程中，每对 point 指针指向的结果是可以被复用的
     * 建立缓存避免重复计算
     */
    private int searchSame(String str1, String str2, int point1, int point2, int[][] cache) {
        if (point1 == str1.length() || point2 == str2.length()) return 0;
        if (cache[point1][point2] != 0) return cache[point1][point2];
        int re = 0;
        if (str1.charAt(point1) == str2.charAt(point2)) re = searchSame(str1, str2, point1 + 1, point2 + 1, cache) + 1;
        re = Math.max(re, searchSame(str1, str2, point1 + 1, point2, cache));
        re = Math.max(re, searchSame(str1, str2, point1, point2 + 1, cache));
        re = Math.max(re, searchSame(str1, str2, point1 + 1, point2 + 1, cache));
        System.out.println("point 1:" + point1 + " ,point2:" + point2);
        return cache[point1][point2] = re;
    }

    /**
     * @Author Niuxy
     * @Date 2020/10/2 7:42 下午
     * @Description 该处 DP 难点在于状态转移路径，二维状态转移需从回归条件开始推演每个维度的转移过程，确定总体转移路径。
     * 经过缓存填充过程可以确定，两个维度均由 回归条件 开始回归，也就是 数组的两个下边界
     */
    public final int longestCommonSubsequenceDP(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        int m = str1.length(), n = str2.length();
        int[][] cache = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j)) cache[i][j] = cache[i + 1][j + 1] + 1;
                else cache[i][j] = Math.max(cache[i][j + 1], cache[i + 1][j]);
            }
        }
        return cache[0][0];
    }
}
