package learning;

import java.util.LinkedList;
import java.util.List;

public class Leetcode0809 {
    List<String> reList = new LinkedList<String>();

    public List<String> generateParenthesis(int n) {
        search(0, n, n, new StringBuilder());
        return reList;
    }

    private void search(int before, int beforeN, int n, StringBuilder sb) {
        if (n == 0) {
            reList.add(sb.toString());
            return;
        }
        int len = sb.length();
        if (beforeN > 0 && before == 0) {
            search(1, beforeN - 1, n, sb.append('('));
            sb.delete(len, len + 1);
        } else {
            search(before - 1, beforeN, n - 1, sb.append(')'));
            sb.delete(len, len + 1);
            if (beforeN > 0) {
                search(before + 1, beforeN - 1, n, sb.append('('));
            }
            sb.delete(len, len + 1);
        }
    }
}
