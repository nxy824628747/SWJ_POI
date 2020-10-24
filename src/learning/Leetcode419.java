package learning;

public class Leetcode419 {
    public final int countBattleships(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    if ((i == 0 || (i > 0 && board[i - 1][j] != 'X')) && (j == 0 || (j > 0 && board[i][j - 1] != 'X')))
                        res++;
                }
            }
        }
        return res;
    }

    public final int countBattleshipsDFS(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return 0;
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    sign(board, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private final void sign(char[][] board, int x, int y) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return;
        if (board[x][y] != 'X') return;
        board[x][y] = '-';
        sign(board, x + 1, y);
        sign(board, x - 1, y);
        sign(board, x, y + 1);
        sign(board, x, y - 1);
    }
}
