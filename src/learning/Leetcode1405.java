package learning;

import java.util.Arrays;

public class Leetcode1405 {

    class CharAndNum implements Comparable {
        char ch;
        int count;

        CharAndNum(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }

        @Override
        public int compareTo(Object o) {
            CharAndNum other = (CharAndNum) o;
            return other.count-count;
        }
    }

    public String longestDiverseString1(int a, int b, int c) {
        CharAndNum[] chars = new CharAndNum[]{
                new CharAndNum('a', a),
                new CharAndNum('b', b),
                new CharAndNum('c', c)
        };
        StringBuilder sb = new StringBuilder();
        while (true) {
            System.out.println(sb.toString());
            Arrays.sort(chars);
            if (isEnd(chars, sb)) {
                break;
            }
            if (canInsert(sb, chars[0].ch)) {
                sb.append(chars[0].ch);
                chars[0].count--;
            } else if (chars[1].count > 0) {
                sb.append(chars[1].ch);
                chars[1].count--;
            }
        }
        return sb.toString();
    }

    private final boolean isEnd(CharAndNum[] chars, StringBuilder sb) {
        if (chars[0].count == 0) {
            return true;
        }
        if (!canInsert(sb, chars[0].ch)) {
            if (chars[1].count == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean canInsert(StringBuilder sb, char ch) {
        int length = sb.length();
        if (length < 2) {
            return true;
        }
        char pre;
        if ((pre = sb.charAt(length - 1)) == sb.charAt(length - 2)) {
            if (pre == ch) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/20 11:17 下午
     * @Description 穷举
     */
    String longestStr = "";

    public String longestDiverseString(int a, int b, int c) {
        longest(a, b, c, "");
        return longestStr;
    }

    public final void longest(int a, int b, int c, String s) {
        longestStr = s.length() > longestStr.length() ? s : longestStr;
        if (a == 0 && b == 0 && c == 0) {
            return;
        }
        if (a > 0 && canInsert('a', s)) {
            longest(a - 1, b, c, s + "a");
        }
        if (b > 0 && canInsert('b', s)) {
            longest(a, b - 1, c, s + "b");
        }
        if (c > 0 && canInsert('c', s)) {
            longest(a, b, c - 1, s + "c");
        }
    }

    private final boolean canInsert(char ch, String s) {
        int length = s.length();
        if (length < 2) {
            return true;
        }
        char char0;
        if ((char0 = s.charAt(length - 1)) == s.charAt(length - 2)) {
            if (char0 == ch) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Leetcode1405 l = new Leetcode1405();
        l.longestDiverseString1(1, 1, 7);
    }
}
