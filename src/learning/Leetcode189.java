package learning;

public class Leetcode189 {
    /**
    * @Author Niuxy
    * @Date 2020/7/2 12:15 上午
    * @Description 朴素解法
    */
    public final void rotate(int[] nums, int k) {
        if (k > nums.length) {
            k %= nums.length;
        }
        for (int i = 0; i < k; i++) {
            rotateOne(nums);
        }
    }

    public final void rotateOne(int[] nums) {
        int last = nums[nums.length - 1];
        for (int i = nums.length - 1; i > 0; i--) {
            nums[i] = nums[i - 1];
        }
        nums[0] = last;
    }

    /**
    * @Author Niuxy
    * @Date 2020/7/2 12:15 上午
    * @Description 环形数组解法
    */
    public final void rotate2(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        if (k > nums.length) {
            k %= nums.length;
        }
        int[] temp = new int[k];
        System.arraycopy(nums, nums.length - k, temp, 0, k);
        System.arraycopy(nums, 0, nums, k, nums.length - k);
        System.arraycopy(temp,0,nums,0,k);
    }
}
