package learning;

public class Leetcode75 {

    public final void sortColors(int[] nums) {
        int num0 = 0;
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (curr == 0) {
                num0++;
            } else if (curr == 1) {
                num1++;
            } else {
                num2++;
            }
        }
        build(0, num0, 0, nums);
        build(num0, num0 + num1, 1, nums);
        build(num0 + num1, nums.length, 2, nums);
    }

    private final void build(int begin, int end, int target, int[] nums) {
        for (int i = begin; i < end; i++) {
            nums[i] = target;
        }
    }
}
