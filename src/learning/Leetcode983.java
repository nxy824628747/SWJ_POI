package learning;

public class Leetcode983 {

    public final int mincostTickets(int[] days, int[] costs) {
        return minCost(days, costs, days.length - 1, new int[days.length]);
    }

    private final int minCost(int[] days, int[] costs, int end, int[] cache) {
        if (end < 0) {
            return 0;
        }
        if (end == 0) {
            return costs[0];
        }
        if (cache[end] != 0) {
            return cache[end];
        }
        int min = Integer.MAX_VALUE;
        for (int begin = 0; begin <= end; begin++) {
            min = Math.min(min, minCost(days, costs, begin - 1, cache) + cost(days[begin], days[end], costs));
        }
        cache[end] = min;
        return min;
    }

    private final int cost(int begin, int end, int[] costs) {
        if (begin == end) {
            return costs[0];
        }
        int travelDay = end - begin + 1;
        int thir = travelDay / 30;
        int seven = (travelDay % 30) / 7;
        int one = travelDay - thir * 30 - seven * 7;
        int cost1 = thir * costs[2] + seven * costs[1] + one * costs[0];
        int cost2 = (travelDay / 7) * costs[1] + (travelDay % 7) * costs[0];
        int cost3 = travelDay * costs[0];
        int min1 = Math.min(Math.min(cost1, cost2), cost3);
        int cost4 = (thir + ((travelDay % 30) > 0 ? 1 : 0)) * costs[2];
        int cost5 = (travelDay / 7 + (travelDay % 7 > 0 ? 1 : 0)) * costs[1];
        return Math.min(Math.min(min1, cost4), cost5);
    }

    public final int mincostTicketsDP(int[] days, int[] costs) {
        int[] cache = new int[days.length];
        cache[0] = Math.min(Math.min(costs[0], costs[1]), costs[2]);
        for (int end = 1; end < cache.length; end++) {
            int min = Integer.MAX_VALUE;
            for (int begin = 0; begin <= end; begin++) {
                if (begin == 0) {
                    min = Math.min(min, cost(days[begin], days[end], costs));
                } else {
                    min = Math.min(min, cache[begin - 1] + cost(days[begin], days[end], costs));
                }
            }
            cache[end] = min;
        }
        return cache[cache.length - 1];
    }
}
