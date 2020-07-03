package learning;

import java.util.HashMap;
import java.util.Map;

public class LeetcodeJZOffer12 {
    public final boolean exist(char[][] board, String word) {
        if (board == null || word == null || word.length() > board.length * board[0].length) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, word, i, j, 0) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean exist(char[][] board, String word, int x, int y, int flag) {
        if (flag == word.length()) {
            return true;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '0') {
            return false;
        }
        char sourceChar = board[x][y];
        if (word.charAt(flag) != sourceChar) {
            return false;
        }
        board[x][y] = '0';
        boolean re = exist(board, word, x + 1, y, flag + 1) || exist(board, word, x - 1, y, flag + 1) ||
                exist(board, word, x, y + 1, flag + 1) || exist(board, word, x, y - 1, flag + 1);
        board[x][y] = sourceChar;
        return re;
    }
}
