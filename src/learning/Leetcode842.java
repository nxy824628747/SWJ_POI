package learning;

import java.util.LinkedList;
import java.util.List;

public class Leetcode842 {
    public final List<Integer> splitIntoFibonacci(String S) {
        List<Integer> reList = new LinkedList<Integer>();
        search(S, 0, reList, 0);
        return reList;
    }

    private final boolean search(String s, int flag, List<Integer> reList, int reLen) {
        if (flag == s.length() && reLen > 2) {
            return true;
        }
        int nextLen = reLen + 1;
        for (int i = flag + 1; i <= s.length(); i++) {
            String inStr = s.substring(flag, i);
            if (i > flag + 1 && '0' == inStr.charAt(0)) {
                continue;
            }
            if (i > flag + 10) {
                break;
            }
            long numLon = Long.valueOf(inStr);
            if (numLon > Integer.MAX_VALUE) {
                continue;
            }
            if (reLen >= 2) {
                if (reList.get(reLen - 2) + reList.get(reLen - 1) != numLon) {
                    continue;
                }
            }
            reList.add((int) numLon);
            if (search(s, i, reList, nextLen)) {
                return true;
            }
            reList.remove(reList.size() - 1);
        }
        return false;
    }
}
