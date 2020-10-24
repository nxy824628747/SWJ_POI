package learning;

public class Leetcode1191 {

    public final int kConcatenationMaxSumDP2(int[] arr, int k) {
        long an = Math.max(0, arr[0]);
        long pre = Math.max(0, arr[0]);
        int sum = arr[0];
        for (int i = 1; i < Math.min(k, 2) * arr.length; i++) {
            int point = i % arr.length;
            pre = Math.max(arr[point], arr[point] + pre);
            an = Math.max(an, pre);
            if (i < arr.length) {
                sum += arr[i];
            }
        }
        while (sum > 0 && --k >= 2) {
            an += an + sum;
        }
        return (int) (an % 1000000007);
    }

    final int mod = 1000000007;
    int an = 0;

    public final int kConcatenationMaxSumDP(int[] arr, int k) {
        int len = arr.length;
        kConcatenationMaxSum2(arr, len - 1, k, new int[len]);
        return an;
    }

    /**
     * @Author Niuxy
     * @Date 2020/8/3 9:29 上午
     * @Description 上面的问题定义可以用来解决问题，但是空间复杂度略大
     * 本质为缓存的空间太大
     * 想要优化缓存，需要改变问题的定义
     * 定义问题是个很有意思的事情
     * 就好比程序员该怎么分类这种问题一样
     * 从不同的角度看有不同的答案
     * 比如可以分为 开发数据库的 和 使用数据库 的
     * 一个真正的程序员，确实要么在开发数据库，要么在使用数据库
     * 这种分类是正确的，因为两个分类的并集可以覆盖所有程序员群体
     * 在定义问题时，也应该去找这种可以完整切分问题的视角
     * 如果能更进一步找到问题本身的递归关系，那一个正确好用的问题定义就找到了
     * 对于本题，上面用的是最暴力的遍历，遍历每个可能的子数组的和
     * 除此之外，还可以定义问题为 以某个元素开头（或结尾）的子数组的最大和
     * 因为最大和子数组必然以某个元素开头或结尾，所以该角度可以覆盖整个问题解空间
     * 这里用 以某个元素结尾的最大子数组和 作为问题定义
     */
    public final int kConcatenationMaxSum2(int[] arr, int lastPoint, int k, int[] cache) {
        if (lastPoint == 0) {
            an = Math.max(arr[0], an);
            return arr[0] % mod;
        }
        int truePoint = lastPoint % arr.length;
        int current = arr[truePoint];
        if (cache[lastPoint] != 0) {
            return cache[lastPoint];
        }
        int re = 0;
        int pre = (kConcatenationMaxSum2(arr, lastPoint - 1, k, cache) + current) % mod;
        re = Math.max(pre, current % mod);
        cache[lastPoint] = re;
        an = Math.max(re, an);
        return re;
    }


    public final int kConcatenationMaxSum(int[] arr, int k) {
        int length = arr.length;
        int newLength = length * k;
        int[][] cache = new int[newLength][newLength + 1];
        for (int head = 0; head < newLength; head++) {
            for (int len = 1; len <= newLength - head; len++) {
                sum(head, len, arr, cache);
            }
        }
        return an;
    }

    /**
     * @Author Niuxy
     * @Date 2020/8/2 6:38 下午
     * @Description 暴力解法一定是需要遍历以每个元素为开头的每个子数组的和，取最大值
     * 最小粒度的问题为 以 head 开头，长度为 len 的子数组的和
     * D( head, len ) 表示以 point 为头元素，长度为 len 的子数组的 sum
     * 尝试推导状态转移关系
     * D( head , len ) = D( head+1 ,len-1 )+ arr[head]
     * 经考虑，该状态转移关系存在大量重复子问题，可用于动态规划
     */
    private final int sum(int head, int len, int[] arr, int[][] cache) {
        int trueHead = head % arr.length;
        if (len == 1) {
            an = Math.max(an, arr[trueHead]);
            return arr[trueHead];
        }
        if (cache[head][len] != 0) {
            return cache[head][len];
        }
        int re = sum(head + 1, len - 1, arr, cache) + arr[trueHead];
        re %= mod;
        cache[head][len] = re;
        an = Math.max(an, re);
        return re;
    }

}
