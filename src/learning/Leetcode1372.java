package learning;

import java.util.HashMap;
import java.util.Map;

public class Leetcode1372 {
    static final int LEFT = -1;
    static final int RIGHT = 1;
    int max = 0;

    public final int longestZigZag(TreeNode root) {
        //缓存
        Map<String, Integer> cache = new HashMap<String, Integer>();
        longestZigZagDP(root, LEFT, cache);
        longestZigZagDP(root, RIGHT, cache);
        return max == 0 ? 0 : max - 1;
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/28 8:26 下午
     * @Description G(node, next) 为以 node 为头结点，向 next 方向前进的交错树的长度
     * G(node) 需在 G(node.left)、G(node.right) 中取其大,因为答案可能以任一节点为头节点，因此要遍历以所有头结点的子树
     */
    public final int longestZigZagDP(TreeNode node, int next, Map<String, Integer> cache) {
        if (node == null) {
            return 0;
        }
        String key = node.hashCode() + String.valueOf(next);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int reLe = longestZigZagDP(node.left, RIGHT, cache) + 1;
        int reRi = longestZigZagDP(node.right, LEFT, cache) + 1;
        cache.put(node.hashCode() + String.valueOf(LEFT), reLe);
        cache.put(node.hashCode() + String.valueOf(RIGHT), reRi);
        //局部最优解
        int re = reLe > reRi ? reLe : reRi;
        //全局最优解
        max = max > re ? max : re;
        return next == LEFT ? reRi : reLe;
    }
}
