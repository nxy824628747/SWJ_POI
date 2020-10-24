package learning;

public class Leetcode204 {
    public final int countPrimes(int n) {
        int re = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimes(i)) {
                re++;
            }
        }
        return re;
    }

    private final boolean isPrimes(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        int half = (int) Math.sqrt(n);
        for (int i = 2; i <= half; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
