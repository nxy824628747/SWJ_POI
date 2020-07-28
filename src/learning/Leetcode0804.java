package learning;

import org.kohsuke.rngom.digested.DContainerPattern;

import java.util.*;

public class Leetcode0804 {
    List<List<Integer>> reList = new LinkedList<List<Integer>>();

    public final List<List<Integer>> subsets1(int[] nums) {
        reList.add(new ArrayList<Integer>());
        Stack<Integer> stack = new Stack<Integer>();
        //所有可能长度
        for (int i = 1; i <= nums.length; i++) {
            //所有可能头元素
            for (int j = 0; j <= nums.length - i; j++) {
                subWay(nums, j, i, stack);
            }
        }
        return reList;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/22 2:26 下午
     * @Description 遍历已 flag 为头元素，长度为 length 的所有可能子序列
     */
    private final void subWay(int[] nums, int flag, int length, Stack<Integer> stack) {
        if (length == 1) {
            if (flag < nums.length) {
                stack.push(nums[flag]);
                reList.add(new ArrayList<Integer>(stack));
                stack.pop();
            }
            return;
        }
        if (length > nums.length - flag || flag > nums.length) {
            return;
        }
        stack.push(nums[flag]);
        for (int i = flag + 1; i < nums.length; i++) {
            subWay(nums, i, length - 1, stack);
        }
        stack.pop();
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/22 4:21 下午
     * @Description 位图解法
     */
    public final List<List<Integer>> subsets(int[] nums) {
        int source = (int) Math.pow(2, nums.length);
        List<List<Integer>> reList = new LinkedList<List<Integer>>();
        for (int i = 1; i <= source; i++) {
            List<Integer> inList = new LinkedList<Integer>();
            for (int j = 0; j < nums.length; j++) {
                if (((i >>> j) & 1) == 1) {
                    inList.add(nums[j]);
                }
            }
            reList.add(inList);
        }
        return reList;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Leetcode0804 l = new Leetcode0804();
        l.subWay(nums, 0, 2, new Stack<Integer>());
        System.out.println("111");
    }
}
