package learning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Leetcode216 {
    private List<List<Integer>> anList = new LinkedList<List<Integer>>();

    public final List<List<Integer>> combinationSum3(int k, int n) {
        combinationSum3DP(k, n, new Stack<Integer>(), 1);
        return anList;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/3 12:51 下午
     * @Description k 个和为 n 的子序列，需要找到所有序列，不能简单地定义状态转移方程。
     * 必须遍历整个解空间。
     * 每个元素从小到大随机选取，天然有序，不需要额外的去重工作
     * 背包问题变种，将视角从整体放到局部，每个元素都有两种状态，选择或不选择。
     */
    public final void combinationSum3DP(int k, int n, Stack<Integer> anStack, int flag) {
        if (n <= 0 && k != 0) {
            return;
        }
        if (k == 0) {
            if (n != 0) {
                return;
            }
            anList.add(new ArrayList<Integer>(anStack));
            return;
        }
        for (int i = flag; i < 10; i++) {
            if (anStack.contains(i)) {
                continue;
            }
            anStack.push(i);
            combinationSum3DP(k - 1, n - i, anStack, +1);
            //回溯
            anStack.pop();
        }
    }


}
