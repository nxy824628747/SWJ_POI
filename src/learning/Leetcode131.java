package learning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Leetcode131 {
    public final List<List<String>> partition(String s) {
        List<List<String>> reList = new LinkedList<List<String>>();
        partition(s, 0, new Stack<String>(), reList);
        return reList;
    }

    private final void partition(String s, int point, Stack<String> stack, List<List<String>> reList) {
        if (point >= s.length()) {
            reList.add(new ArrayList<String>(stack));
            return;
        }
        for (int end = point + 1; end <= s.length(); end++) {
            String inStr = s.substring(point, end);
            if (!isalindrome(inStr)) {
                continue;
            }
            stack.push(inStr);
            partition(s, end, stack, reList);
            stack.pop();
        }
    }

    private final boolean isalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static void main(String[] args) {
        String source = "aab";
        Leetcode131 l = new Leetcode131();
        l.partition(source);
    }
}
