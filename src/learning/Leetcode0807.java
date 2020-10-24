package learning;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class Leetcode0807 {
    public final String[] permutation(String S) {
        Set<String> reSet = new HashSet<String>();
        permutation(S.toCharArray(), new StringBuilder(), reSet);
        return setToStrArr(reSet);
    }

    private final void permutation(char[] sChars, StringBuilder sb, Set<String> reSet) {
        if (sb.length() == sChars.length) {
            String str = sb.toString();
            if (!reSet.contains(str)) {
                reSet.add(str);
            }
            return;
        }
        for (int i = 0; i < sChars.length; i++) {
            if (sChars[i] == '-') {
                continue;
            }
            char currentChar = sChars[i];
            sChars[i] = '-';
            sb.append(currentChar);
            permutation(sChars, sb, reSet);
            sb.deleteCharAt(sb.length() - 1);
            sChars[i] = currentChar;
        }
    }

    private final String[] setToStrArr(Set<String> reSet) {
        if (reSet == null || reSet.size() == 0) {
            return new String[0];
        }
        String[] reStrArr = new String[reSet.size()];
        Iterator iterator = reSet.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            reStrArr[i] = (String) iterator.next();
            i++;
        }
        return reStrArr;
    }
}
