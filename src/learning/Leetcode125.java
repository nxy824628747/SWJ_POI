package learning;

public class Leetcode125 {

    public final boolean isPalindrome(String s) {
        int length = s.length();
        int leftPoint = 0;
        int rightPoint = length - 1;
        s = s.replaceAll("[\\pP‘’“”``]", " ");
        while (leftPoint < rightPoint) {
            char leftChar = s.charAt(leftPoint);
            while (leftPoint < rightPoint && leftChar == ' ') {
                leftPoint++;
                leftChar = s.charAt(leftPoint);
            }
            char rightChar = s.charAt(rightPoint);
            while (leftPoint < rightPoint && rightChar == ' ') {
                rightPoint--;
                rightChar = s.charAt(rightPoint);
            }
            if (leftPoint < rightPoint) {
                leftChar = Character.toLowerCase(leftChar);
                rightChar = Character.toLowerCase(rightChar);
                if (leftChar != rightChar) {
                    return false;
                }
                leftPoint++;
                rightPoint--;
            }
        }
        return true;
    }

    public boolean isPalindrome0(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "`l;`` 1o1 ??;l`";
        Leetcode125 l = new Leetcode125();
        l.isPalindrome(s);
    }
}
