package learning;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Leetcode95 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public final List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/6 10:48 下午
     * @Description 从问题本身找递归结构
     * 选取一个根节点分割数组，以该节点为根节点的所有可能的树为
     * 同类问题在两边子数组中解的笛卡尔积
     */
    public final List<TreeNode> generateTrees(int begin, int end) {
        List<TreeNode> anList = new LinkedList<TreeNode>();
        if (begin > end) {
            anList.add(null);
            return anList;
        }
        if (begin == end) {
            anList.add(new TreeNode(begin));
            return anList;
        }
        for (int i = begin; i <= end; i++) {
            List<TreeNode> left = generateTrees(begin, i - 1);
            List<TreeNode> right = generateTrees(i + 1, end);
            for (int j = 0; j < left.size(); j++) {
                for (int k = 0; k < right.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = left.get(j);
                    root.right = right.get(k);
                    anList.add(root);
                }
            }
        }
        return anList;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/6 2:45 下午
     * @Description 节点插入二叉搜索树
     */
    private void insertNode(TreeNode root, TreeNode node) {
        if (root == null || root.val == node.val) {
            return;
        }
        if (node.val < root.val) {
            if (root.left == null) {
                root.left = node;
                return;
            }
            insertNode(root.left, node);
        } else {
            if (root.right == null) {
                root.right = node;
                return;
            }
            insertNode(root.right, node);
        }
    }

    public List<TreeNode> generateTrees0(int n) {
        List<TreeNode> list = new LinkedList<TreeNode>();
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 1; i < n; i++) {
            set.clear();
            for (int j = 1; j < n - 1; j++) {

            }
        }
        return null;
    }

    List<TreeNode> list = new LinkedList<TreeNode>();

    private void generateTrees(int n, TreeNode root, boolean isRoot, Set<Integer> set) {
        if (set.size() == n) {
            list.add(root);
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (isRoot) {
                root = new TreeNode(i);
                set.add(i);
                continue;
            }
            if (set.contains(i)) {
                continue;
            }
            set.add(i);
            TreeNode node = new TreeNode(i);
            insertNode(root, node);
            generateTrees(n, root, false, set);
            //回溯
            set.remove(i);
        }
    }

}
