package learning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author niuxy
 * @Date 2020/7/26 7:39 下午
 * @Description NQueen
 */
public class Leetcode0812 {

    public List<List<String>> solveNQueens(int n) {
        //棋盘
        char[][] board = new char[n][n];
        //结果集合
        List<List<String>> reList = new LinkedList<List<String>>();
        setQueen(0, n, board, reList);
        return reList;
    }

    //回溯函数，逐行放置皇后
    private final void setQueen(int x, int boardLen, char[][] board, List<List<String>> reList) {
        if (x == boardLen) {
            reList.add(boardToString(board, boardLen));
            return;
        }
        for (int i = 0; i < boardLen; i++) {
            if (!canSet(x, i, boardLen, board)) {
                continue;
            }
            board[x][i] = 'Q';
            setQueen(x + 1, boardLen, board, reList);
            board[x][i] = '.';
        }
    }

    //判断 x，y 坐标是否可以放置皇后
    private final boolean canSet(int x, int y, int boardLen, char[][] board) {
        for (int i = 0; i <= x; i++) {
            //判断竖线
            if (board[i][y] == 'Q') {
                return false;
            }
            int point = x - i;
            //判断左下对角线
            if (inBoard(i, y - point, boardLen) && (board[i][y - point] == 'Q')) {
                return false;
            }
            //判断右下对角线
            if (inBoard(i, y + point, boardLen) && board[i][y + point] == 'Q') {
                return false;
            }
        }
        return true;
    }

    //判断 x，y 坐标是否在棋盘内
    private final boolean inBoard(int x, int y, int boardLen) {
        if (x >= 0 && y >= 0 && x < boardLen && y < boardLen) {
            return true;
        }
        return false;
    }

    //将棋盘转为 String 集合
    private final List<String> boardToString(char[][] board, int boardLen) {
        List<String> re = new ArrayList<String>(boardLen);
        for (int i = 0; i < boardLen; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < boardLen; j++) {
                char ch = board[i][j] == 'Q' ? 'Q' : '.';
                sb.append(ch);
            }
            re.add(sb.toString());
        }
        return re;
    }

    private void print(List<List<String>> reList) {
        for (int i = 0; i < reList.size(); i++) {
            System.out.println(" anwser :");
            List<String> column = reList.get(i);
            for (int j = 0; j < column.size(); j++) {
                System.out.println("  " + column.get(j));
            }
        }
    }

    public static void main(String[] args) {
        Leetcode0812 l = new Leetcode0812();
        l.print(l.solveNQueens(4));
    }

}
