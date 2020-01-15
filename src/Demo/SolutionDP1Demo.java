package Demo;//在二维网格 grid 上，有 4 种类型的方格：////        1 表示起始方格。且只有一个起始方格。//        2 表示结束方格，且只有一个结束方格。//        0 表示我们可以走过的空方格。//        -1 表示我们无法跨越的障碍。//        返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目，每一个无障碍方格都要通过一次。public class SolutionDP1Demo {    public static void mai1n(String[] args) {        SolutionDP1 sdp = new SolutionDP1();        int[][] grid = new int[][]{{1, 0, 0, 0}, {0, 0, -1, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};        int[][] grid2 = new int[][]{{1, 0, 0, 0}, {0, 0, -1, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}};        int re1 = sdp.uniquePathsIII(grid);        System.out.print("---re1 :   " + sdp.uniquePathsIII(grid));        System.out.print("---re2  :   " + uniquePathsIII(grid2));    }    /**     * 递归解法     *     * @param grid     * @return     */    public static final int uniquePathsIII(int[][] grid) {        if (grid == null) {            return 0;        }        int x = grid.length;        int y = grid[0].length;        int m = 0;        int n = 0;        for (int i = 0; i < x; i++) {            for (int j = 0; j < y; j++) {                if (grid[i][j] == 2) {                    m = i;                    n = j;                }            }        }        int[][] cache = new int[x][y];        int re = uniquePaths1(m, n, grid);        System.out.print("  " + cache[m][n]);        return re;    }    /**     * 递归解法     *     * @param m     * @param n     * @param grid     * @return     */    public static final int uniquePaths1(int m, int n, int[][] grid) {        int x = grid.length;        int y = grid[0].length;        int flag = 0;        int endflag = 0;        /*减越界*/        if (m < 0 || n < 0) {            return 0;        }        /*加越界*/        if (m > x - 1 || n > y - 1) {            return 0;        }        /*路线不通*/        if (grid[m][n] == -1) {            return 0;        }        /*到达终点*/        if (grid[m][n] == 1) {            int isAllFlag = 0;            /*是否全部走完*/            for (int i = 0; i < x; i++) {                for (int j = 0; j < y; j++) {                    if (grid[i][j] == 0) {                        isAllFlag = 1;                    }                }            }            /*未走完全部0不是有效路径*/            if (isAllFlag == 1) {                return 0;            }            return 1;        }        /*走过路径标识为不可重复经过*/        if (grid[m][n] == 0) {            grid[m][n] = -1;            flag = 1;        }        /*终点不可经过*/        if (grid[m][n] == 2) {            grid[m][n] = -1;            endflag = 1;        }        /*状态转移计算*/        int re = uniquePaths1(m - 1, n, grid) + uniquePaths1(m, n - 1, grid)                    + uniquePaths1(m + 1, n, grid) + uniquePaths1(m, n + 1, grid);        /*恢复现场-已走过路径*/        if (flag == 1) {            grid[m][n] = 0;        }        /*恢复现场-终点*/        if (endflag == 1) {            grid[m][n] = 2;        }        return re;    }}