package learning;

import java.util.*;

public class Leetcode491 {
    List<List<Integer>> reList = new LinkedList<List<Integer>>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        search(nums, -1, new Stack<Integer>());
        return reList;
    }

    private final void search(int[] nums, int point, Stack<Integer> stack) {
        int pre = stack.size() == 0 ? Integer.MIN_VALUE : stack.get(stack.size() - 1);
        Set<Integer> set = new HashSet<Integer>();
        for (int i = point + 1; i < nums.length; i++) {
            if (nums[i] < pre || set.contains(nums[i])) {
                continue;
            }
            stack.push(nums[i]);
            set.add(nums[i]);
            if (stack.size() > 1) {
                reList.add(new ArrayList<Integer>(stack));
            }
            search(nums, i, stack);
            stack.pop();
        }
    }

}
