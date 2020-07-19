package learning;

import java.util.HashMap;
import java.util.Map;

public class Leetcode1218 {
    public final int longestSubsequenceDP1(int[] arr, int difference) {
        Map<Integer, Integer> dp = new HashMap<Integer, Integer>();
        dp.put(arr[arr.length-1],1);
        int an = 0;
        for (int i = arr.length - 2; i >= 0; i--) {
            int pre = 1;
            int key=arr[i]+difference;
            if(dp.containsKey(key)){pre=dp.get(key)+1;}
            dp.put(arr[i], pre);
            an = Math.max(an, pre);
        }
        return an;
    }


    public final int longestSubsequenceDP0(int[] arr, int difference) {
        if (arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        int an = 0;
        for (int i = arr.length - 2; i >= 0; i--) {
            int temp = 0;
            for (int j = i + 1; j < arr.length; j++) {
                int currentDiff = arr[j] - arr[i];
                if (currentDiff != difference) {
                    continue;
                }
                temp = Math.max(temp, dp[j] + 1);
            }
            dp[i] = temp;
            an = Math.max(an, temp);
        }
        return an+1;
    }


    int an = 0;

    public final int longestSubsequence(int[] arr, int difference) {
        if (arr.length == 0) {
            return 0;
        }
        longestSubsequence(arr, difference, 0, new int[arr.length]);
        return an + 1;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/15 9:47 上午
     * @Description G(point) 表示以 arr[point] 为头元素的最长定差子序列的长度
     */
    public final int longestSubsequence(int[] arr, int difference, int point, int[] cache) {
        if (point == arr.length - 1) {
            return 0;
        }
        if (cache[point] != 0) {
            return cache[point];
        }
        int an = 0;
        for (int i = point + 1; i < arr.length; i++) {
            int currentDiff = arr[i] - arr[point];
            int next = longestSubsequence(arr, difference, i, cache);
            if (currentDiff != difference) {
                continue;
            }
            an = Math.max(an, 1 + next);
        }
        cache[point] = an;
        this.an = Math.max(an, this.an);
        return an;
    }


    public static void main(String[] args) {
        int[] nums = {1, 5, 7, 8, 5, 3, 4, 2, 1};
        int diff = -2;
        Leetcode1218 l = new Leetcode1218();
        l.longestSubsequence(nums, diff);
    }
}
