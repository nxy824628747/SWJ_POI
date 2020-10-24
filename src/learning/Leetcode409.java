package learning;

import java.util.HashSet;
import java.util.Set;

public class Leetcode409 {
    public final int longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) return length;
        Set<Character> set = new HashSet<Character>();
        int re = 0;
        for (int i = 0; i < length; i++) {
            char curr = s.charAt(i);
            if (!set.contains(curr)) set.add(curr);
            else {
                set.remove(curr);
                re += 2;
            }
        }
        return re < s.length() ? re + 1 : re;
    }
}
