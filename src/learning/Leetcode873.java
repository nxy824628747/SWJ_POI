package learning;

import java.util.HashSet;
import java.util.Set;

public class Leetcode873 {
    int max = 0;

    public int lenLongestFibSubseq(int[] A) {
        int[][] cache = new int[A.length][A.length];
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                lenLongestFibSubseqDP(A, i, j, cache);
            }
        }
        return max;
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/28 10:08 下午
     * @Description 想要确定一个斐波那契数列，需要确定头两个元素
     * 因此要遍历数列中所有的两两组合，求出以其作为头元素和第二个元素的斐波那契额数列的长度，取最大值
     * G(f,s) 为以下标为 f，s 的元素为头元素与第二个元素的斐波那契数列长度
     */
    public int lenLongestFibSubseqDP(int[] A, int f, int s, int[][] cache) {
        if (s == A.length - 1) {
            return 2;
        }
        if (cache[f][s] != 0) {
            return cache[f][s];
        }
        int next = A[f] + A[s];
        for (int i = s + 1; i < A.length; i++) {
            if (A[i] != next) {
                continue;
            }
            int re = lenLongestFibSubseqDP(A, s, i, cache) + 1;
            max = max > re ? max : re;
            cache[f][s] = re;
            return re;
        }
        cache[f][s] = 2;
        return 2;
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/28 11:35 下午
     * @Description DP 解法，将递归转为递推
     */
    public int lenLongestFibSubseqDP2(int[] A) {
        int max = 0;
        int[][] cache = new int[A.length][A.length];
        Set<Integer> aSet = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            cache[A.length - 1][i] = 2;
            aSet.add(A[i]);
        }
        for (int i = A.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < A.length; j++) {
                if (cache[i][j] != 0) {
                    continue;
                }
                int next = A[j] + A[i];
                if (!aSet.contains(next)) {
                    cache[i][j] = 2;
                    continue;
                }
                for (int k = j + 1; k < A.length; k++) {
                    if (A[k] == next) {
                        int re = cache[j][k] + 1;
                        max = max > re ? max : re;
                        cache[i][j] = re;
                    }
                }
                cache[i][j] = cache[i][j] == 0 ? 2 : cache[i][j];
            }
        }
        return max;
    }


}
