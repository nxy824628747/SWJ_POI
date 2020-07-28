package learning;

public class Leetcode79 {
    String word;
    char[][] board;

    public final boolean exist(char[][] board, String word) {
        this.word = word;
        this.board = board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(0, i, j))
                    return true;
            }
        }
        return false;
    }

    private final boolean search(int strPoint, int xPoint, int yPoint) {
        if (strPoint == word.length()) {
            return true;
        }
        if (xPoint < 0 || yPoint < 0 || xPoint >= board.length || yPoint >= board[0].length || board[xPoint][yPoint] == '-') {
            return false;
        }
        char currentChar = board[xPoint][yPoint];
        if (currentChar != word.charAt(strPoint)) {
            return false;
        }
        board[xPoint][yPoint] = '-';
        boolean re = search(strPoint + 1, xPoint + 1, yPoint)
                || search(strPoint + 1, xPoint - 1, yPoint)
                || search(strPoint + 1, xPoint, yPoint + 1)
                || search(strPoint + 1, xPoint, yPoint - 1);
        board[xPoint][yPoint] = currentChar;
        return re;
    }
}
