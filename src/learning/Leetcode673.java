package learning;

import java.util.HashMap;
import java.util.Map;

public class Leetcode673 {

    /**
     * @Author Niuxy
     * @Date 2020/6/20 11:30 上午
     * @Description 解决问题时先推导一下，问题是否可以被自己解决。
     * 若果不行，尝试转化问题。
     * 比如找出所有递增序列，并按长度计数
     */
    public int findNumberOfLIS(int[] nums) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            int size = 1;
            int node = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > node) {
                    node = nums[j];
                    size++;
                }
            }
            if (size != 1) {
                maxLength = Math.max(maxLength, size);
                if (m.containsKey(size)) {
                    m.put(size, m.get(size) + 1);
                } else {
                    m.put(size, 1);
                }
            }
        }
        return maxLength == 0 ? nums.length : m.get(maxLength);
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/21 8:35 下午
     * @Description 上一个解法的问题在于，逐个元素进行比较，无法覆盖所有解空间。
     * 比如 [1，3，3，4] ，选择完第一个元素后，可以选择第二个也可以选择第三个，而上个解法只会考虑选择第二个的情况。
     * 另外我们发现，自前向后遍历时，存在许多重复计算。
     * 重复计算存在于：
     * 计算以 num[1] 开头的最长子序列时，后续计算结果可以被计算以 num[n]（某个可作为 num[1] 开头的最长子序列的后续元素）
     * 开头的最长子序列复用
     * 那么以 n 为坐标建立缓存，定义 G(n) 可以记录以 nums[n] 开头的最递增子序列的个数
     */
    public int findNumberOfLIS1(int[] nums) {
        An[] cache=new An[nums.length];
        for (int i = 0; i < nums.length; i++) {
            G(nums, i,cache);
        }
        return reAn.length == 1 ? nums.length : reAn.num;
    }

    class An {
        int length;
        int num;

        An(int length, int num) {
            this.length = length;
            this.num = num;
        }
    }

    ;

    An reAn = new An(1, 0);

    public An G(int[] nums, int n, An[] cache) {
        if (n > nums.length - 1) {
            return new An(1, 1);
        }
        if (cache[n] != null) {
            An an = cache[n];
            if (an.length == reAn.length) {
                reAn.num += an.num;
            }
            if (an.length > reAn.length) {
                reAn.length = an.length;
                reAn.num = an.num;
            }
            return an;
        }
        int reLen = 1;
        int reNum = 1;
        for (int i = n + 1; i < nums.length; i++) {
            if (nums[i] <= nums[n]) {
                continue;
            }
            An an = G(nums, i, cache);
            if (an.length == reLen - 1) {
                reNum += an.num;
            } else if (an.length >= reLen) {
                reLen = an.length + 1;
                reNum = an.num;
            }
        }
        if (reLen == reAn.length) {
            reAn.num += reNum;
        }
        if (reLen > reAn.length) {
            reAn.length = reLen;
            reAn.num = reNum;
        }
        cache[n] = new An(reLen, reNum);
        return cache[n];
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        Leetcode673 l = new Leetcode673();
        System.out.println(l.findNumberOfLIS1(nums));
    }

}
