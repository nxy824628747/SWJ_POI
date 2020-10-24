package learning;

public class Leetcode509 {
    public final int fib0(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return fib0(N - 1) + fib0(N - 2);
    }

    public final int fib(int N) {
        int num0 = 0, num1 = 1;
        for (int i = 0; i < N / 2; i++) {
            num0 += num1;
            num1 += num0;
        }
        return N % 2 == 0 ? num0 : num1;
    }
}
