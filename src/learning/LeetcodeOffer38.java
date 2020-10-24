package learning;

import java.util.*;

public class LeetcodeOffer38 {

    public final String[] permutation(String s) {
        Set<String> reSet = new HashSet<String>();
        search(s, 0, reSet, new StringBuilder(), new HashSet<Integer>());
        return reSet.toArray(new String[reSet.size()]);
    }

    private final void search(String s, int len, Set<String> reSet, StringBuilder sb, Set<Integer> path) {
        if (len == s.length()) {
            String reStr = sb.toString();
            if (!reSet.contains(reStr)) {
                reSet.add(reStr);
            }
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (path.contains(i)) {
                continue;
            }
            char currentChar = s.charAt(i);
            path.add(i);
            sb.append(currentChar);
            int nextLen = len + 1;
            search(s, nextLen, reSet, sb, path);
            path.remove(i);
            sb.deleteCharAt(len);
        }
    }
}
