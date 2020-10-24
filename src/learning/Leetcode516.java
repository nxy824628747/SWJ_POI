package learning;

import java.util.HashMap;
import java.util.Map;

public class Leetcode516 {

    public final int longestPalindromeSubseq(String s) {
        int re = 0;
        int len = s.length();
        int[][] cache = new int[len + 1][len + 1];
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        for (int i = 0; i < s.length(); i++) {
            re = Math.max(re, longestPalindromeSubseq(s, i, len, cache, map));
        }
        return re;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/27 9:27 下午
     * @Description 求最长回文子序列
     * 固定头元素，区间寻找尾元素
     */
    private final int longestPalindromeSubseq(String s, int begin, int end, int[][] cache,
                                              Map<Character, Integer> map) {
        if (begin > end) {
            return 0;
        }
        if (begin == end - 1) {
            return 1;
        }
        if (cache[begin][end] != 0) {
            return cache[begin][end];
        }
        //在 begin 与 end 之间找尾元素
        int endIn = begin;
        char beginChar = s.charAt(begin);
        int e;
        if (map.containsKey(beginChar) && (e = map.get(beginChar)) < end) {
            endIn = e;
        } else {
            for (int i = begin + 1; i < end; i++) {
                if (s.charAt(begin) == s.charAt(i)) {
                    endIn = i;
                }
            }
        }
        if (begin == endIn) {
            return 0;
        }
        //递归子序列
        int re = 0;
        for (int i = begin + 1; i < endIn; i++) {
            re = Math.max(re, longestPalindromeSubseq(s, i, endIn, cache, map));
        }
        re += 2;
        cache[begin][end] = re;
        return re;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/28 12:12 下午
     * @Description 对于分治问题的定义，为了维系问题间的关系，定义必须包含头元素与尾元素
     * 上一种方法固定一端在区间寻找另一端，在区间内寻找问题的递归结构
     * 换一种思路，每个元素只有两个状态，选择或者不选择
     * 尝试在这两个状态间切换，以此寻找问题间的递归关系
     */
    private final int longestPalindromeSubseq(String s, int begin, int end, int[][] cache) {
        if (begin == end) {
            return 1;
        }
        if (cache[begin][end] != 0) {
            return cache[begin][end];
        }
        int re = 0;
        //符合回文条件，首尾必选
        if (s.charAt(begin) == s.charAt(end)) {
            re = longestPalindromeSubseq(s, begin + 1, end - 1, cache) + 2;
        } else {
            //否则选一个，挑最长的
            re = Math.max(longestPalindromeSubseq(s, begin, end - 1, cache), longestPalindromeSubseq(s, begin + 1,
                    end, cache));
        }
        cache[begin][end] = re;
        return re;
    }

    public final int longestPalindromeSubseqDP(String s) {
        int len = s.length();
        return longestPalindromeSubseq(s, 0, len - 1, new int[len][len]);
    }
}