package learning;

public class Leetcode646 {

    public final int findLongestChain(int[][] pairs) {
        sort(pairs);
        int[] dp = new int[pairs.length];
        dp[0] = 1;
        int maxRe = 0;
        for (int i = 1; i < dp.length; i++) {
            int maxTe = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (pairs[i][0] > pairs[j][1]) {
                    maxTe = Math.max(maxTe, dp[j] + 1);
                }
            }
            dp[i] = maxTe;
            maxRe = Math.max(maxRe, maxTe);
        }
        return maxRe;
    }

    private final void sort(int[][] pairs) {
        for (int i = 0; i < pairs.length; i++) {
            for (int j = i; j < pairs.length; j++) {
                if (pairs[i][0] > pairs[j][0]) {
                    int[] temp = pairs[i];
                    pairs[i] = pairs[j];
                    pairs[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] paris = {{-2, 1}, {3, 7}, {0, 10}, {-1, 8}, {0, 7}, {8, 9}, {7, 10}, {-9, -3}, {-4, -3}, {0, 6}};
        Leetcode646 l = new Leetcode646();
        l.sort(paris);
        printParis(paris);
        System.out.println(" --- ");
        l.sort(paris);
        printParis(paris);
    }

    private static void printParis(int[][] paris) {
        for (int i = 0; i < paris.length; i++) {
            System.out.print(paris[i][0] + "," + paris[i][1]);
            System.out.println("  ");
        }
    }
}
