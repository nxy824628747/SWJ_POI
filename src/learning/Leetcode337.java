package learning;

import java.util.HashMap;
import java.util.Map;

public class Leetcode337 {
    Map<TreeNode, Integer> t = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();

    public final int robDP(TreeNode root) {
        search(root);
        return Math.max(t.getOrDefault(root, 0), f.getOrDefault(root, 0));
    }

    private final void search(TreeNode root) {
        if (root == null) {
            return;
        }
        search(root.left);
        search(root.right);
        t.put(root, f.getOrDefault(root.left, 0) + f.getOrDefault(root.right, 0) + root.val);
        f.put(root, Math.max(t.getOrDefault(root.left, 0), f.getOrDefault(root.left, 0))
                + Math.max(t.getOrDefault(root.right, 0), f.getOrDefault(root.right, 0)));
    }

    public final int rob(TreeNode root) {
        Map<String, Integer> cache = new HashMap<String, Integer>();
        return Math.max(rob(root, true, cache), rob(root, false, cache));
    }

    /**
     * @Author Niuxy
     * @Date 2020/9/1 1:52 下午
     * @Description 以 root 为根的树，可获取的最大收益;
     */
    public final int rob(TreeNode root, boolean stro, Map<String, Integer> cache) {
        if (root == null) {
            return 0;
        }
        String key = root.hashCode() + String.valueOf(stro);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int leftTrue = 0;
        int rightTrue = 0;
        int leftFalse = 0;
        int rightFalse = 0;
        if (root.left != null) {
            leftTrue = rob(root.left, true, cache);
            leftFalse = rob(root.left, false, cache);
        }
        if (root.right != null) {
            rightTrue = rob(root.right, true, cache);
            rightFalse = rob(root.right, false, cache);
        }
        int an = 0;
        if (stro) {
            an = rightFalse + leftFalse + root.val;
        } else {
            an = Math.max(rightTrue + leftTrue, rightFalse + leftTrue);
            an = Math.max(an, rightTrue + leftFalse);
            an = Math.max(an, rightFalse + leftFalse);
        }
        cache.put(key, an);
        return an;
    }

}
