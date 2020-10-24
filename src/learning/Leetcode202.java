package learning;

public class Leetcode202 {
    public final boolean isHappy(int n) {
        int next = next(n);
        while (n != 1 && n != next) {
            n = next(n);
            next = next(next(next));
        }
        return n == 1;
    }

    private final int next(int n) {
        int re = 0;
        while (n > 0) {
            int curr = n % 10;
            n /= 10;
            re += curr * curr;
        }
        return re;
    }
}
