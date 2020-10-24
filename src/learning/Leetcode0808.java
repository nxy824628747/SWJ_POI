package learning;

import java.util.*;

public class Leetcode0808 {
    List<String> reList = new LinkedList<String>();

    public final String[] permutation2(String S) {
        char[] chars = S.toCharArray();
        Arrays.sort(chars);
        search2(chars, new StringBuilder());
        return listToArr(reList);
    }

    private final void search2(char[] chars, StringBuilder sb) {
        if (sb.length() == chars.length) {
            reList.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '-' || (i > 0 && chars[i] == chars[i - 1])) {
                continue;
            }
            char currentChar = chars[i];
            chars[i] = '-';
            search2(chars, sb.append(currentChar));
            chars[i] = currentChar;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private final String[] listToArr(List<String> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    //set 去重
    final Set<String> reSet = new HashSet<String>();

    public final String[] permutation(String S) {
        search(S, new HashSet<Integer>(), new StringBuilder());
        return setToString(reSet);
    }

    private final void search(String s, Set<Integer> path, StringBuilder sb) {
        if (sb.length() == s.length()) {
            String re = sb.toString();
            if (!reSet.contains(re)) {
                reSet.add(re);
            }
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (path.contains(i)) {
                continue;
            }
            char currentChar = s.charAt(i);
            sb.append(currentChar);
            path.add(i);
            search(s, path, sb);
            path.remove(i);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private final String[] setToString(Set<String> set) {
        String[] reArr = new String[set.size()];
        Iterator iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            reArr[i] = (String) iterator.next();
            i++;
        }
        return reArr;
    }

}
