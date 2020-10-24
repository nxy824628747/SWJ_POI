package learning;

import java.util.Arrays;

public class Leetcode455 {
    public final int findContentChildren(int[] g, int[] s) {
        if (s.length == 0 || g.length == 0) {
            return 0;
        }
        Arrays.sort(s);
        Arrays.sort(g);
        int sPoint = 0;
        int re = 0;
        int sMax = s[s.length - 1];
        for (int i = 0; i < g.length; i++) {
            if (g[i] > sMax) {
                break;
            }
            for (int j = sPoint; j < s.length; j++) {
                if (s[j] >= g[i]) {
                    sPoint = j+1;
                    s[j] = -1;
                    re++;
                    break;
                }
            }
        }
        return re;
    }
}
