package learning;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Leetcode220 {
    /**
     * @Author Niuxy
     * @Date 2020/7/5 8:54 下午
     * @Description Hash 表查找，O( N * t )
     */
    public final boolean containsNearbyAlmostDuplicate0(int[] nums, int k, int t) {
        Set<Integer> window = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= t; j++) {
                int target0 = nums[i] + j;
                if (window.contains(target0)) {
                    return true;
                }
                int target1 = nums[i] - j;
                if (window.contains(target1)) {
                    return true;
                }
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
     * @Date 2020/7/5 9:05 下午
     * @Description 二叉排序树查找最近元素，O( N * 2logN )
     */
    public final boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        TreeSet<Integer> window = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            Integer pre = window.floor(nums[i]);
            if (pre != null && nums[i] <= t + pre) {
                return true;
            }
            Integer next = window.ceiling(nums[i]);
            if (next != null && next <= t + nums[i]) {
                return true;
            }
            window.add(nums[i]);
            if (window.size() > k) {
                window.remove(nums[i - k]);
            }
        }
        return false;
    }

}
