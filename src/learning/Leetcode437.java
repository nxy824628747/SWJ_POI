package learning;

import java.util.HashMap;
import java.util.Map;

public class Leetcode437 {

    /**
     * @Author Niuxy
     * @Date 2020/7/15 11:04 下午
     * @Description 双递归
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSum0(root, sum, 0) + pathSum(root.right, sum) + pathSum(root.left, sum);
    }

    public int pathSum0(TreeNode node, int sum, int preSum) {
        if (node == null) {
            return 0;
        }
        int currentSum = preSum + node.val;
        int re = pathSum0(node.left, sum, currentSum) + pathSum0(node.right, sum, currentSum);
        if (sum == currentSum) {
            re++;
        }
        return re;
    }


    /**
     * @Author Niuxy
     * @Date 2020/7/15 11:52 下午
     * @Description 前缀和解法
     */
    public final int pathSum1(TreeNode root, int sum) {
        return pathSum1(root, sum, 0, new HashMap<Integer, Integer>());
    }

    public final int pathSum1(TreeNode root, int sum, int preSum, Map<Integer, Integer> cache) {
        if (root == null) {
            return 0;
        }
        int currentRe = 0;
        int currentSum = preSum + root.val;
        if (currentSum == sum) {
            currentRe++;
        }
        //以该节点为末尾节点，向上寻找满足条件的路径数: currentRe - x = sum -> x = currentSum - sum
        currentRe += cache.getOrDefault(currentSum - sum, 0);
        //本节点前缀和
        cache.put(currentSum, cache.getOrDefault(currentSum, 0) + 1);
        currentRe += pathSum1(root.left, sum, currentSum, cache) + pathSum1(root.right, sum, currentSum, cache);
        cache.put(currentSum, cache.get(currentSum) - 1);
        return currentRe;
    }
}
