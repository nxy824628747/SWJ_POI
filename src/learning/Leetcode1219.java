package learning;

public class Leetcode1219 {

    int an = 0;

    public final int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                getMaximumGold(grid, i, j, 0);
            }
        }
        return an;
    }

    public final void getMaximumGold(int[][] grid, int x, int y, int sum) {
        if (x >= grid.length || x < 0 || y >= grid[0].length || y < 0 || grid[x][y] == 0 || grid[x][y] == -1) {
            an = Math.max(an, sum);
            return;
        }
        int current = grid[x][y];
        int currentSum = sum + current;
        grid[x][y] = -1;
        getMaximumGold(grid, x + 1, y, currentSum);
        getMaximumGold(grid, x - 1, y, currentSum);
        getMaximumGold(grid, x, y + 1, currentSum);
        getMaximumGold(grid, x, y - 1, currentSum);
        grid[x][y] = current;
    }

}
