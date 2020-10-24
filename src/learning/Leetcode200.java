package learning;

public class Leetcode200 {
    class UnionFind {
        int rootNum;
        int[] parent;

        UnionFind(char[][] grid) {
            this.parent = new int[grid.length * grid[0].length];
            this.rootNum = 0;
            initParent(grid);
        }

        private final void initParent(char[][] grid) {
            int len = grid[0].length;
            for (int i = 0; i < parent.length; i++) {
                int x = i / len;
                int y = i % len;
                if (grid[x][y] == '1') {
                    parent[i] = i;
                    this.rootNum++;
                } else {
                    parent[i] = -1;
                }
            }
        }

        private final int find(int num) {
            if (parent[num] == num) {
                return num;
            }
            return find(parent[num]);
        }

        private final void uion(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);
            if (root1 == root2) {
                return;
            }
            parent[root2] = root1;
            rootNum--;
        }
    }

    public final int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        UnionFind unionFind = new UnionFind(grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    int point = i * grid[0].length + j;
                    if (i > 0 && grid[i - 1][j] == '1') {
                        unionFind.uion(point, point - grid[0].length);
                    }
                    if (i < grid.length - 1 && grid[i + 1][j] == '1') {
                        unionFind.uion(point, point + grid[0].length);
                    }
                    if (j > 0 && grid[i][j - 1] == '1') {
                        unionFind.uion(point, point - 1);
                    }
                    if (j < grid[0].length - 1 && grid[i][j + 1] == '1') {
                        unionFind.uion(point, point + 1);
                    }
                }
            }
        }
        return unionFind.rootNum;
    }

    public final int numIslandsDFS(char[][] grid) {
        int re = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == '1') {
                    search(grid, x, y);
                    re++;
                }
            }
        }
        return re;
    }

    private final void search(char[][] grid, int x, int y) {
        if (!isInBoard(grid, x, y) || grid[x][y] == '0') {
            return;
        }
        grid[x][y] = '0';
        search(grid, x - 1, y);
        search(grid, x, y + 1);
        search(grid, x + 1, y);
        search(grid, x, y - 1);
    }

    private final boolean isInBoard(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return false;
        }
        return true;
    }

}
