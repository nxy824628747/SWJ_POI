package learning;

import java.util.Stack;

public class Leetcode38 {
    public final String countAndSay0(int n) {
        String[] cache = new String[n + 1];
        return countAndSay0(n, cache);
    }

    public final String countAndSay0(int n, String[] cache) {
        if (n == 1) {
            return "1";
        }
        if (n == 2) {
            return "11";
        }
        if (cache[n] != null) {
            return cache[n];
        }
        String pre = countAndSay0(n - 1, cache);
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < pre.length(); i++) {
            //末尾元素处理
            if (i == pre.length() - 1) {
                if (stack.size() == 0) {
                    sb.append("1" + pre.charAt(pre.length() - 1));
                } else if (pre.charAt(i) == stack.get(0)) {
                    sb.append(String.valueOf(stack.size() + 1)).append(String.valueOf(stack.get(0)));
                } else {
                    sb.append(String.valueOf(stack.size())).append(String.valueOf(stack.get(0)));
                    sb.append("1" + pre.charAt(pre.length() - 1));
                }
                break;
            }
            //普通元素处理
            if (stack.size() > 0 && pre.charAt(i) != stack.get(0)) {
                sb.append(String.valueOf(stack.size())).append(String.valueOf(stack.get(0)));
                stack.clear();
                stack.push(pre.charAt(i));
                continue;
            }
            stack.push(pre.charAt(i));
        }
        stack = null;
        //记录缓存
        cache[n] = sb.toString();
        return cache[n];
    }

    public final String countAndSay1(int n) {
        if (n == 1) {
            return "1";
        }
        String[] cache = new String[n + 1];
        cache[1] = "1";
        cache[2] = "11";
        Stack<Character> stack = new Stack<Character>();
        for (int i = 3; i <= n; i++) {
            String pre = cache[i - 1];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < pre.length(); j++) {
                //末尾元素处理
                if (j == pre.length() - 1) {
                    if (stack.size() == 0) {
                        sb.append("1").append(pre.charAt(pre.length() - 1));
                    } else if (pre.charAt(j) == stack.get(0)) {
                        sb.append(String.valueOf(stack.size() + 1)).append(String.valueOf(stack.get(0)));
                    } else {
                        sb.append(String.valueOf(stack.size())).append(String.valueOf(stack.get(0))).append("1").append(pre.charAt(pre.length() - 1));
                    }
                    stack.clear();
                    break;
                }
                //普通元素处理
                if (stack.size() > 0 && pre.charAt(j) != stack.get(0)) {
                    sb.append(String.valueOf(stack.size())).append(String.valueOf(stack.get(0)));
                    stack.clear();
                    stack.push(pre.charAt(j));
                    continue;
                }
                stack.push(pre.charAt(j));
            }
            cache[i] = sb.toString();
        }
        return cache[n];
    }

    public final String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        if (n == 2) {
            return "11";
        }
        StringBuilder pre = new StringBuilder("11");
        for (int i = 3; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            char currentChar = pre.charAt(0);
            int currentNum = 1;
            for (int j = 1; j < pre.length() + 1; j++) {
                if (j == pre.length()) {
                    sb.append(currentNum).append(currentChar);
                    break;
                }
                if (pre.charAt(j) == currentChar) {
                    currentNum++;
                    continue;
                }
                sb.append(currentNum).append(currentChar);
                currentNum = 1;
                currentChar = pre.charAt(j);
            }
            pre = sb;
        }
        return pre.toString();
    }
}
