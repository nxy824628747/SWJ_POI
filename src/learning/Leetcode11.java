package learning;

public class Leetcode11 {
    /**
     * @Author Niuxy
     * @Date 2020/7/9 9:49 下午
     * @Description 暴力解法
     */
    public final int maxArea0(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int length = Math.min(height[j], height[i]);
                length = length < 0 ? length * -1 : length;
                max = Math.max(max, (j - i) * length);
            }
        }
        return max;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/9 11:18 下午
     * @Description 贪心算法
     */
    public final int maxArea(int[] height) {
        int begin = 0;
        int end = height.length - 1;
        int max = 0;
        while (begin < end) {
            max = Math.max(max, (end - begin) * Math.min(height[begin], height[end]));
            if (height[begin] > height[end]) {
                end--;
            } else {
                begin++;
            }
        }
        return max;
    }

}
