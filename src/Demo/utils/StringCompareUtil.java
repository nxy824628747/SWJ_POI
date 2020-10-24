package Demo.utils;

import Demo.utils.MathUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Niuxy
 * @Date 2020/9/30 1:12 下午
 * @Description 字符串模糊匹配
 */
public class StringCompareUtil {

    /**
     * @Author Niuxy
     * @Date 2020/9/30 2:08 下午
     * @Description str1, str2 待比较字符串，threshold: 比较阈值，redundances: 冗余信息项
     */
    public static boolean isSame(String str1, String str2, double threshold, String[] redundances) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0)
            throw new NullPointerException("str1 or str2 is null");
        if (str1.contains("结婚") || str2.contains("结婚")) System.out.println(str1 + "," + str2);
        str1 = deleteRedundances(str1, redundances);
        str2 = deleteRedundances(str2, redundances);
        int length = Math.max(str1.length(), str2.length());
        return isSame(str1, str2, length, threshold);
    }

    //比较重合率与阈值
    public static boolean isSame(String str1, String str2, int length, double threshold) {
        double re = coincidenceRate(str1, str2, length);
        return re >= threshold;
    }

    //计算重合率
    private static double coincidenceRate(String str1, String str2, int length) {
        int coincidenc = longestCommonSubsequence(str1, str2);
        return MathUtils.txfloat(coincidenc, length);
    }

    //去处冗余
    private static String deleteRedundances(String str, String[] redundances) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (String redundance : redundances) {
            int index = stringBuilder.indexOf(redundance);
            if (index != -1) stringBuilder.replace(index, index + redundance.length(), "");
        }
        return stringBuilder.toString();
    }

    //计算最长公共子序列
    public static int longestCommonSubsequence(String str1, String str2) {
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

    public static int longestCommonSubsequenceDP(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        int m = str1.length(), n = str2.length();
        Map<Long, Integer> cache = new HashMap<Long, Integer>();
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                long key = getKey(i, j);
                if (str1.charAt(i) == str2.charAt(j)) cache.put(key, cache.getOrDefault(getKey(i + 1, j + 1),0)+1);
                else cache.put(key, Math.max(cache.getOrDefault(getKey(i, j + 1),0), cache.getOrDefault(getKey(i + 1, j),0)));
            }
        }
        return cache.get(getKey(0, 0));
    }

    private static long getKey(int i, int j) {
        return ((long)i<<32)|j;
    }

}
