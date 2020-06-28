package learning;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetcodeF1611 {

    public final int[] diviBoard1(int shorter, int longer, int k) {
        int length = pow(2, k);
        int[] re = new int[length];
        while (k >= 0) {

        }
        return null;
    }

    private final int pow(int d, int u) {
        int temp = d;
        for (int i = 0; i < u - 1; i++) {
            temp *= d;
        }
        return temp;
    }

    List<Integer> reList = new LinkedList<Integer>();

    public final int[] divingBoard(int shorter, int longer, int k) {
        diving(shorter, longer, k, 0);
        int[] reArr = new int[reList.size()];
        for (int i = 0; i < reArr.length; i++) {
            reArr[i] = reList.get(i);
        }
        Arrays.sort(reArr);
        return reArr;
    }

    public final void diving(int shorter, int longer, int k, int re) {
        if (k == 0) {
            if (!reList.contains(re)) {
                reList.add(re);
            }
            return;
        }
        diving(shorter, longer, k - 1, re + longer);
        diving(shorter, longer, k - 1, re + shorter);
    }

}
