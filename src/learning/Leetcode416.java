package learning;

import java.util.HashMap;
import java.util.Map;

public class Leetcode416 {
    public final boolean canPartition1(int[] nums) {
        //边界
        if (nums == null || nums.length < 2) {
            return false;
        }
        //剪枝
        int sum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = max > nums[i] ? max : nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }
        if (max == target) {
            return true;
        }
        //回溯遍历
        return hasSubarray(nums, target);
    }

    public final boolean hasSubarray(int[] nums, int target) {
        if (target < 0) {
            return false;
        }
        if (target == 0) {
            return true;
        }
        boolean re = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) {
                continue;
            }
            int numI = nums[i];
            nums[i] = -1;
            re = hasSubarray(nums, target - numI);
            if (re == true) {
                return true;
            }
            nums[i] = numI;
        }
        return false;
    }


    /**
     * @Author Niuxy
     * @Date 2020/6/29 9:05 下午
     * @Description 上述回溯思路中，之所以需要回溯是因为每次递归调用都是在 nums 数组中选择了一个元素
     * 该元素不能被后续选择
     * 如果我们要找连续的子数组，可以将思路逆转一下
     * 变选择某元素为不选择某元素，将数组分割为两个连续的部分，尝试所有连续的组合
     * 这样就不必再进行回溯处理，因为没有选择某元素
     * 但是本题要求的子数组是不连续的，可以跳着选元素
     * 将整体分割为两个整体后，每个整体可以承担 target 的任意部分，这使得遍历所有可能变得很困难
     * 用多个子问题的解表示问题的解行不通，尝试用单个子问题的解表示问题的解
     * 用上面的暴力解法，没有找到子问题的重复结构
     * 每次从整体中随机抽取一个元素，遍历所有可能的组合
     * 将视角从整体转到个体，每个个体都有两种可能，选择和不选择
     * 遍历每个个体的所有可能，并求它们的笛卡尔积，也可以遍历整个解空间，并且可以逐个有序的遍历元素
     */
    public boolean dp(int[] nums, int n, int target, Map<Integer, Boolean> cache) {
        if (n == 0) {
            return target == nums[0];
        }
        int key = n * 23 + target * 131071;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        boolean re = dp(nums, n - 1, target, cache) || dp(nums, n - 1, target - nums[n], cache);
        cache.put(key, re);
        return re;
    }

    public final boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int sum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = max > nums[i] ? max : nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }
        if (max == target) {
            return true;
        }
        //to-do
        Map<Integer, Boolean> cache = new HashMap<Integer, Boolean>();
        return dp(nums, nums.length - 1, target, cache);
    }

}
