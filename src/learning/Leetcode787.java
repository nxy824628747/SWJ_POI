package learning;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leetcode787 {
    public final int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int re = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i][0] != src) continue;
            int temp = findCheapestPrice(flights, i, flights[i][1], dst, K - 1, map);
            if (temp != -1) re = re > temp ? temp : re;
        }
        return re == Integer.MAX_VALUE ? -1 : re;
    }

    public final int findCheapestPrice(int[][] flights, int point, int src, int dst, int K, Map<Integer, Integer> map) {
        if (K < -1) return -1;
        if (src == dst) return flights[point][2];
        int key = K * 100 + src + point * 10000;
        if (map.containsKey(key)) return map.get(key);
        int re = Integer.MAX_VALUE;
        for (int i = 0; i < flights.length; i++) {
            if (flights[point][1] != flights[i][0]) continue;
            int temp = findCheapestPrice(flights, i, flights[i][1], dst, K - 1, map);
            if (temp == -1) continue;
            re = Math.min(re, temp);
        }
        map.put(key, re == Integer.MAX_VALUE ? -1 : re + flights[point][2]);
        return map.get(key);
    }

    /**
     * @Author Niuxy
     * @Date 2020/10/18 4:53 下午
     * @Description 按出发地选中开头航班后，不断通过比较目的地与下一出发地寻找可能的下一航班
     * 因为每趟航班的目的地是确定的，不管从哪条路线来到该航班，最终结果只受剩余可乘坐航班次数的限制
     * 因此可以通过航班坐标 flag 及剩余可乘坐航班次数 K 来进行结果的缓存
     * 那么可以以这两个维度进行 DP 递推
     */
    public final int findCheapestPriceDP(int n, int[][] flights, int src, int dst, int K) {
        int len = flights.length;
        int[][] cache = new int[K + 1][len];
        int re = Integer.MAX_VALUE;
        for (int i = 0; i < cache[0].length; i++) {
            if (flights[i][0] != src) continue;
            if (flights[i][1] == dst) re = Math.min(re, flights[i][2]);
            else cache[K][i] = flights[i][2];
        }
        for (int i = K - 1; i >= 0; i--) {
            for (int j = 0; j < len; j++) {
                int minPreCost = Integer.MAX_VALUE;
                for (int k = 0; k < len; k++) {
                    if (cache[i + 1][k] == 0 || flights[j][0] != flights[k][1]) continue;
                    else minPreCost = Math.min(minPreCost, cache[i + 1][k]);
                }
                if (minPreCost != Integer.MAX_VALUE)
                    if (flights[j][1] == dst) re = Math.min(re, minPreCost + flights[j][2]);
                    else cache[i][j] = minPreCost + flights[j][2];
            }
        }
        return re == Integer.MAX_VALUE ? -1 : re;
    }

    /**
     * @Author Niuxy
     * @Date 2020/10/20 10:48 下午
     * @Description dp[k][i] 表示经历 k 步到达 i 的最小化费
     */
    public final int findCheapestPriceDP2(int n, int[][] flights, int src, int dst, int K) {
        int flag = Integer.MAX_VALUE / 2;
        int[][] dp = new int[2][n];
        dp[0][src] = dp[1][src] = 0;
        Arrays.fill(dp[0], flag);
        Arrays.fill(dp[1], flag);
        for (int k = 0; k <= K; k++) {
            for (int[] info : flights) {
                dp[k & 1][info[1]] = Math.min(dp[k & 1][info[1]], dp[~k & 1][info[0]] + info[2]);
            }
        }
        return dp[K & 1][dst] < Integer.MAX_VALUE / 2 ? dp[K & 1][dst] : -1;
    }

}
