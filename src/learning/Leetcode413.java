package learning;

import java.util.HashSet;
import java.util.Set;

public class Leetcode413 {
    /**
     * @Author Niuxy
     * @Date 2020/6/17 8:01 下午
     * @Description G(n) 为前 n 个元素中，以第 n 个元素结尾的等差数列的个数
     * if A[n]-A[n-1]=A[n-1]-A[n-2]
         * if G(n-1)!=0
            * G(n)=G(n-1)*2+1
        * else if A[n-1]-A[n-2]==A[n-2]-A[n-3]
             * G(n)=1
         * else
            * G(n)=0
     */

    public final int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int[] dp = new int[A.length];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = (A[2] - A[1]) == (A[1] - A[0]) ? 1 : 0;
        int re = dp[2];
        for (int i = 3; i < dp.length; i++) {
            if ((A[i] - A[i - 1]) == (A[i - 1] - A[i - 2])) {
                if (dp[i - 1] != 0) {
                    dp[i] = dp[i - 1] + 1;
                } else if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                    dp[i] = 1;
                }
            } else {
                dp[i] = 0;
            }
            re += dp[i];
        }
        return re;
    }


    public final int numberOfArithmeticSlices2(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int pre = (A[2] - A[1]) == (A[1] - A[0]) ? 1 : 0;
        int re = pre;
        for (int i = 3; i <A.length; i++) {
            if ((A[i] - A[i - 1]) == (A[i - 1] - A[i - 2])) {
                if (pre != 0) {
                    pre += 1;
                } else if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                    pre = 1;
                }
            } else {
                pre = 0;
            }
            re += pre;
        }
        return re;
    }
}
