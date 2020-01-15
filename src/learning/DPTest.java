package learning;

class DPTest {
    public static void main(String[] args) {
        int[][] re=new int[5][6];
        System.out.print(re.getClass());
    }

    public static int minDistance2(String word1, String word2) {

        if (word1 == null && word2 != null) {
            return word2.length();
        }
        if (word1 != null && word2 == null) {
            return word1.length();
        }
        if (word1 == null && word2 == null) {
            return 0;
        }
        int l1 = word1.length();
        int l2 = word2.length();
        int[][] re = new int[l1 + 1][l2 + 1];
        int result = getMin(word1, word2, re);

        return result;
    }

    public static int minDistance(String word1, String word2) {
        if (word1 == null && word2 != null) {
            return word2.length();
        }
        if (word1 != null && word2 == null) {
            return word1.length();
        }
        if (word1 == null && word2 == null) {
            return 0;
        }
        int l1 = word1.length();
        int l2 = word2.length();
        if (l1 == 0 && l2 != 0) {
            return word2.length();
        }
        if (l1 != 0 && l2 == 0) {
            return word1.length();
        }
        if (l1 == 0 && l2 == 0) {
            return 0;
        }
        char c1 = word1.charAt(l1 - 1);
        char c2 = word2.charAt(l2 - 1);
        if (c1 == c2) {
            return minDistance(word1.substring(0, l1 - 1), word2.substring(0, l2 - 1));
        }
        int min = 0;
        if (c1 != c2) {
            int update = minDistance(word1.substring(0, l1 - 1), word2.substring(0, l2 - 1)) + 1;
            int insert = minDistance(word1.substring(0, l1), word2.substring(0, l2 - 1)) + 1;
            int delete = minDistance(word1.substring(0, l1 - 1), word2.substring(0, l2)) + 1;
            min = min(update, insert, delete);
        }
        return min;
    }

    public static int min(int i1, int i2, int i3) {
        int min = i1;
        if (min > i2) {
            min = i2;
        }
        if (min > i3) {
            min = i3;
        }
        return min;
    }


    public static int getMin(String word1, String word2, int[][] re) {
        int l1 = word1.length();
        int l2 = word2.length();
        if (l1 == 0 && l2 != 0) {
            return word2.length();
        }
        if (l1 != 0 && l2 == 0) {
            return word1.length();
        }
        if (l1 == 0 && l2 == 0) {
            return 0;
        }
        char c1 = word1.charAt(l1 - 1);
        char c2 = word2.charAt(l2 - 1);
        if (c1 == c2) {
            if (re[l1 - 1][l2 - 1] != 0) {
                return re[l1 - 1][l2 - 1];
            }
            return getMin(word1.substring(0, l1 - 1), word2.substring(0, l2 - 1), re);
        }
        int min = 0;
        int update;
        int insert;
        int delete;
        if (re[l1 - 1][l2 - 1] != 0) {
            update = re[l1 - 1][l2 - 1] + 1;
        } else {
            update = minDistance(word1.substring(0, l1 - 1), word2.substring(0, l2 - 1)) + 1;
        }
        if (re[l1][l2 - 1] != 0) {
            insert = re[l1][l2 - 1] + 1;
        } else {
            insert = minDistance(word1.substring(0, l1), word2.substring(0, l2 - 1)) + 1;
        }
        if (re[l1 - 1][l2] != 0) {
            delete = re[l1 - 1][l2] + 1;
        } else {
            delete = minDistance(word1.substring(0, l1 - 1), word2.substring(0, l2)) + 1;
        }
        min = min(update, insert, delete);
        re[l1][l2] = min;
        return min;
    }

    public static int minDistance3(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // if one of the strings is empty
        if (n * m == 0)
            return n + m;

        // array to store the convertion history
        int [][] d = new int[n + 1][m + 1];

        // init boundaries
        for (int i = 0; i < n + 1; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            d[0][j] = j;
        }

        // DP compute
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = d[i - 1][j] + 1;
                int down = d[i][j - 1] + 1;
                int left_down = d[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1))
                    left_down += 1;
                d[i][j] = Math.min(left, Math.min(down, left_down));

            }
        }
        return d[n][m];
    }

}