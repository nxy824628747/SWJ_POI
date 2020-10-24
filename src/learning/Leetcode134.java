package learning;

public class Leetcode134 {
    public final int canCompleteCircuit2(int[] gas, int[] cost) {
        int begin = 0, hasGas = 0, path = 0, len = gas.length;
        for (int i = 0; i < len * 2; i++) {
            if (path == len-1) {
                return begin;
            }
            int trueI = i % 2;
            hasGas += gas[trueI];
            if (hasGas < cost[trueI]) {
                begin = trueI;
                hasGas = gas[trueI];
                path = 0;
            } else {
                hasGas -= cost[trueI];
                path++;
            }
        }
        return -1;
    }

    public final int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] - cost[i] >= 0 && running(gas, cost, i, i, 0, true)) {
                return i;
            }
        }
        return -1;
    }

    private final boolean running(int[] gas, int[] cost, int begin, int current, int hasGas, boolean isBegin) {
        if (current >= gas.length) {
            current %= gas.length;
        }
        if (!isBegin && current == begin) {
            return true;
        }
        int currGas = gas[current] + hasGas;
        if (currGas < cost[current]) {
            return false;
        }
        return running(gas, cost, begin, current + 1, currGas - cost[current], false);
    }
}
