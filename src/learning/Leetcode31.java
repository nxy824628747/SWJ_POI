package learning;

import java.util.Arrays;

public class Leetcode31 {
    public void nextPermutationError(int[] nums) {
        int length = nums.length;
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    int tempI = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tempI;
                    return;
                }
            }
        }
        Arrays.sort(nums);
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/27 12:04 上午
     * @Description 从后开始，将更后面的且大于该元素的最小元素向前推一次
     */
    public void nextPermutation(int[] nums) {
        int length = nums.length;
        for (int i = length - 2; i >= 0; i--) {
            int target = i;
            int min = Integer.MAX_VALUE;
            for (int j = i + 1; j < length; j++) {
                if (nums[j] > nums[i] && nums[j] < min) {
                    target = j;
                    min = nums[j];
                }
            }
            if (target != i) {
                int temp = nums[i];
                nums[i] = nums[target];
                nums[target] = temp;
                sort(target + 1, nums);
                return;
            }
        }
        Arrays.sort(nums);
    }

    //从 begin 位置开始冒泡排序
    public void sort(int begin, int[] nums) {
        int length = nums.length;
        for (int i = begin; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/26 4:06 下午
     * @Description
     * 解题，正确的思路是最难找的
     * 如果有充分的时间，在有充分的理由确定思路正确前，不要过早的深入下去
     * 否则非常容易成为自己的桎梏
     * 一旦以一个错误的前提开始深入，路会越走越窄，或者遇到的问题会越来越多
     * 人很容易为自己的错误找到合适的借口
     * 思路错了，一切以该思路为前提的后续工作都是错误的
     * 在错的道路上找局部问题的解决方案，整体的问题可能也会逐步得到解决
     * 但在最合适的思路上，上述局部问题可能根本就不会存在
     * 娴熟的方法是前提，问题的定义是关键
     * 广义上的取反，是找到正确的问题定义的利器
     * 整体视角转到局部视角、局部转到整体、极大化为极小、逐个解决子问题转为略过某个子问题求解其它所有子问题、
     * 甚至不再着眼于现有问题，依靠解决其它问题来解决现有问题
     * 等等等等，各个层面正反两个逻辑的组合足以覆盖所有问题定义的方式，在合理的逻辑上尝试取反，找到最巧妙的组合，四两拨千斤
     * 逆向思维不是万能的，但很多巧妙的思路都是应用逆向思维的得出的
     * 在决定解题思路的阶段，应当使用逆向思维帮助拓展思路
     *
     */
}
