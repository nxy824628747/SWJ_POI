package learning;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Leetcode219 {
    /**
     * @Author Niuxy
     * @Date 2020/7/4 11:17 下午
     * @Description 朴素解法 O(N * min(K,nums.length-N))
     */
    public boolean containsNearbyDuplicate0(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int maxJ = Math.min(nums.length - 1, i + k);
            for (int j = i + 1; j <= maxJ; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/4 11:18 下午
     * @Description 使用二叉搜索树构建滑动窗口 O(N*logN)
     */
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Set<Integer> window = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (window.contains(nums[i])) {
                return true;
            }
            window.add(nums[i]);
            if (window.size() > k) {
                window.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/4 11:22 下午
     * @Description 使用哈希表构建滑动窗口 O(N)
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> window = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (window.contains(nums[i])) {
                return true;
            }
            window.add(nums[i]);
            if (window.size() > k) {
                window.remove(nums[i-k]);
            }
        }
        return false;
    }
}
