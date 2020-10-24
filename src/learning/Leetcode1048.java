package learning;

import java.util.Arrays;

public class Leetcode1048 {

    public final int longestStrChain(String[] words) {
        int len = words.length;
        if (len == 0) {
            return 0;
        }
        Arrays.sort(words, (str1, str2) -> {
                    return str1.length() - str2.length();
                }
        );
        int[] cache = new int[len];
        int re = 1;
        for (int i = 0; i < len; i++) {
            re = Math.max(longestStrChain(words, i, cache), re);
        }
        return re;
    }

    private final int longestStrChain(String[] words, int end, int[] cache) {
        if (cache[end] != 0) {
            return cache[end];
        }
        int re = 1;
        for (int i = 0; i < end; i++) {
            if (isChain(words[i], words[end])) {
                re = Math.max(re, longestStrChain(words, i, cache) + 1);
            }
        }
        cache[end] = re;
        return re;
    }

    private final boolean isChain(String str1, String str2) {
        if (str2.length() - str1.length() != 1) {
            return false;
        }
        int point1 = 0;
        int point2 = 0;
        while (point1 < str1.length() && str1.charAt(point1) == str2.charAt(point2)) {
            point1++;
            point2++;
        }
        if ( str1.substring(point1, str1.length()).equals(str2.substring(point2 + 1,
                str2.length()))) {
            return true;
        }
        return false;
    }

}
