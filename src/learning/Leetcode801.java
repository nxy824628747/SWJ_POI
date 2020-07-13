package learning;

import java.util.HashMap;
import java.util.Map;

public class Leetcode801 {
    /**
     * @Author Niuxy
     * @Date 2020/7/12 6:41 下午
     * @Description 回溯法, 暴力搜索
     */
    public int minSwap0(int[] A, int[] B) {
        if (A.length == 0) {
            return 0;
        }
        minSwap(A, B, 0, 0);
        return an;
    }

    int an = Integer.MAX_VALUE;

    public void minSwap(int[] A, int[] B, int point, int nums) {
        if (point == A.length) {
            if (isIncrease(A) && isIncrease(B)) {
                an = Math.min(an, nums);
            }
            return;
        }
        change(A, B, point);
        minSwap(A, B, point + 1, nums + 1);
        change(A, B, point);
        minSwap(A, B, point + 1, nums);
    }

    private void change(int[] A, int[] B, int point) {
        int temp = A[point];
        A[point] = B[point];
        B[point] = temp;
    }

    private boolean isIncrease(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/12 7:10 下午
     * @Description 回溯算法的回溯点在于，每次交换元素后，该操作会对后续的判断产生影响
     * 题目要求求最小交换次数，也就是说经过有限次数的交换，两个数列最终一定会严格递增
     * 每个元素小于其相邻的后续元素是数列严格递增的充分条件
     * 可以通过不断比较相邻的两个元素来判断数列是否递增
     * 可以通过将可能被交换的元素存储在递归函数入参中，来替代回溯
     * 每个元素只有两种选择，交换与不交换，那么：
     * 1，当 A，B 在该元素上不是严格递增的，必须交换（剪枝）
     * 2，否则有交换与不交换两种可能
     * 遍历每个元素，求它们所有可能选择集合的笛卡尔积
     * 2 情况会导致计算过程中存在重复计算，通过缓存避免
     * 相比于回溯的暴力搜索，通过对元素区分情况 1，2 进行了剪枝，避免了部分无效计算
     * 同时通过缓存避免了部分重复计算
     */
    public int minSwap(int[] A, int[] B, int point, int a, int b, Map<Integer, Integer> cache) {
        if (point == A.length) {
            return 0;
        }
        int key = a * 3 + b * 1733 + point * 17333;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int re = 0;
        if ((A[point] <= a || B[point] <= b) && A[point] != B[point]) {
            re = minSwap(A, B, point + 1, B[point], A[point], cache) + 1;
        } else {
            re = minSwap(A, B, point + 1, A[point], B[point], cache);
            if (B[point] > a && A[point] > b) {
                re = Math.min(minSwap(A, B, point + 1, B[point], A[point], cache) + 1, re);
            }
        }
        cache.put(key, re);
        return re;
    }

    public int minSwap(int[] A, int[] B) {
        if (A.length == 0) {
            return 0;
        }
        return minSwap(A, B, 0, Integer.MIN_VALUE, Integer.MIN_VALUE, new HashMap<Integer, Integer>());
    }

}
