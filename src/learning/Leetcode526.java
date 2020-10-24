package learning;

import java.util.HashSet;
import java.util.Set;

public class Leetcode526 {
    int an = 0;

    public final int countArrangement(int N) {
        search(N, new HashSet<Integer>(), 0);
        return an;
    }

    private final void search(int n, Set<Integer> history, int rePoint) {
        if (rePoint == n) {
            an++;
            return;
        }
        int nextPoint = rePoint + 1;
        for (int i = 1; i <= n; i++) {
            if (history.contains(i) || (i % nextPoint != 0 && nextPoint % i != 0)) {
                continue;
            }
            history.add(i);
            search(n, history, nextPoint);
            history.remove(i);
        }
    }
}
