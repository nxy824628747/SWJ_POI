package learning;

import java.util.Arrays;
import java.util.Stack;

public class Leetcode56 {
    public final int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, (num1, num2) -> num1[0] - num2[0]);
        int len = intervals.length;
        int[][] reArr = new int[len][2];
        int point = -1;
        for (int i = 0; i < intervals.length; i++) {
            if (point == -1 || intervals[i][0] > reArr[point][1]) {
                reArr[++point] = intervals[i];
                continue;
            }
            reArr[point][1] = Math.max(intervals[i][1], reArr[point][1]);
        }
        return Arrays.copyOf(reArr, point + 1);
    }


    public final int[][] merge(int[][] intervals) {
        sort(intervals);
        Stack<int[]> reStack = new Stack<int[]>();
        for (int i = 0; i < intervals.length; i++) {
            if (reStack.size() == 0) {
                reStack.push(intervals[i]);
                continue;
            }
            int[] pre = reStack.get(reStack.size() - 1);
            int[] curr = intervals[i];
            if (curr[0] > pre[1]) {
                reStack.push(curr);
                continue;
            }
            if (curr[1] > pre[1]) {
                pre[1] = curr[1];
            }
        }
        return stackToArr(reStack);
    }

    private final int[][] stackToArr(Stack<int[]> stack) {
        int[][] arr = new int[stack.size()][2];
        for (int i = stack.size() - 1; i >= 0; i--) {
            arr[i] = stack.pop();
        }
        return arr;
    }

    private final void sort(int[][] source) {
        for (int i = 0; i < source.length; i++) {
            for (int j = i + 1; j < source.length; j++) {
                if (source[j][0] < source[i][0]) {
                    int[] temp = source[i];
                    source[i] = source[j];
                    source[j] = temp;
                }
            }
        }
    }
}
