package learning;

public class Leetcode1139 {
    /**
     * @Author Niuxy
     * @Date 2020/7/8 9:20 下午
     * @Description 暴力解法，遍历以每一个元素开头，所有可能边长的长方形
     */
    public final int largest1BorderedSquare(int[][] grid) {
        int re = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                int maxLen = Math.min(grid.length - i, grid.length - j);
                while (true) {
//                    System.out.println("maxLen : "+maxLen);
                    if (maxLen == 1) {
                        break;
                    }
                    boolean isTrue = false;
                    for (int k = maxLen - 1; k > 0; k--) {
                        int left = grid[i][j + k];
                        int right = grid[i + maxLen - 1][j + k];
                        int top = grid[i + k][j];
                        int bottom = grid[i + k][j + maxLen - 1];
                        if (left == 0 || right == 0 || top == 0 || bottom == 0) {
                            maxLen--;
                            break;
                        }
                        isTrue = true;
                    }
                    if (isTrue) {
                        re = Math.max(maxLen, re);
                        break;
                    }
                }
            }
        }
        return re * re;
    }

    public static void main(String[] args) {
        Leetcode1139 l = new Leetcode1139();
        int[][] source = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 0}};
        System.out.println(l.largest1BorderedSquare(source));
    }
}
