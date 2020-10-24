package learning;

public class Leetcode495 {
    public final int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries.length == 0 || duration == 0) {
            return 0;
        }
        int cur = 1;
        int re = duration;
        while (cur < timeSeries.length) {
            int time = timeSeries[cur] - timeSeries[cur - 1] + 1;
            if (time > duration) {
                re += duration;
            } else {
                re += time - 1;
            }
            cur++;
        }
        return re;
    }
}
