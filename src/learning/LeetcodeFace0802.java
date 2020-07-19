package learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetcodeFace0802 {
    public final List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        Stack<List<Integer>> stack = new Stack<List<Integer>>();
        if (isHighWay(obstacleGrid, 0, 0, stack, new int[obstacleGrid.length][obstacleGrid[0].length])) {
            return new ArrayList<List<Integer>>(stack);
        }
        return new ArrayList<List<Integer>>();
    }

    public final boolean isHighWay(int[][] obstacleGrid, int x, int y, Stack<List<Integer>> stack, int[][] cache) {
        if (x >= obstacleGrid.length || y >= obstacleGrid[0].length || obstacleGrid[x][y] == 1) {
            return false;
        }
        List<Integer> listStep = new ArrayList<Integer>(2);
        listStep.add(x);
        listStep.add(y);
        if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1) {
            stack.push(listStep);
            return true;
        }
        if (cache[x][y] != 0) {
            return false;
        }
        stack.push(listStep);
        if (isHighWay(obstacleGrid, x, y + 1, stack, cache) || isHighWay(obstacleGrid, x + 1, y, stack, cache)) {
            return true;
        }
        stack.pop();
        cache[x][y] = 1;
        return false;
    }
}
