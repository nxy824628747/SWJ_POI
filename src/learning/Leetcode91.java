package learning;

public class Leetcode91 {
    public final int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        //虚拟边界
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < s.length() + 1; i++) {
            if (s.charAt(i - 1) == '0') {
                // 0 无法解码
                char pre = s.charAt(i - 2);
                if ((pre == '0') || ((pre != '1') && (pre != '2'))) {
                    return 0;
                }
                // 0 解码
                dp[i] = dp[i - 2];
                continue;
            }
            dp[i] = dp[i - 1];
            if (s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) < 27) {
                if (i - 2 >= 0) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[s.length()];
    }

    public final int numDecodings0(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        //虚拟边界
        int pre1 = 1;
        int pre2 = 1;
        for (int i = 2; i < s.length() + 1; i++) {
            int current = 0;
            char preChar = s.charAt(i - 2);
            char currentChar = s.charAt(i - 1);
            if (currentChar == '0') {
                // 0 无法解码
                if ((preChar == '0') && Integer.valueOf(s.substring(i - 2, i)) < 27) {
                    return 0;
                }
                // 0 解码
                pre1 = pre2;
                continue;
            }
            current = pre1;
            if (preChar != '0' && (int) preChar < 51 && (int) currentChar < 55) {
                current += pre2;
            }
            pre2 = pre1;
            pre1 = current;
        }
        return pre1;
    }

    public static void main(String[] args) {
        System.out.println(Integer.valueOf('2'));
        System.out.println((int) '3');
    }

}
