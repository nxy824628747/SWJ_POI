package learning;

public class Leetcode441 {
    public final int arrangeCoins(int n) {
        return arrange(n, 1);
    }

    private final int arrange(int n, int level) {
        if (n < level) return 0;
        return 1 + arrange(n - level, level + 1);
    }

    public final int arrangeCoins1(int n) {
        if (n < 1) return 0;
        int level = 1;
        while (n >= level) {
            n -= level;
            level++;
        }
        return level-1;
    }
}
