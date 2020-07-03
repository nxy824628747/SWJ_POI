package learning;

import java.util.Stack;

public class SigmentTree {
    /**
     * @Author Niuxy
     * @Date 2020/6/29 3:57 下午
     * @Description 线段树节点
     */
    class TreeNode {
        TreeNode(int val, int start, int end) {
            this.val = val;
            this.start = start;
            this.end = end;
        }

        TreeNode left;
        TreeNode right;
        int val;
        int start;
        int end;
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/29 4:05 下午
     * @Description 构造线段树
     */
    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start], start, end);
        }
        TreeNode node = new TreeNode(0, start, end);
        TreeNode left = buildTree(nums, start, (start + end) / 2);
        TreeNode right = buildTree(nums, (start + end) / 2 + 1, end);
        node.val = left.val + right.val;
        node.left = left;
        node.right = right;
        return node;
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/29 4:05 下午
     * @Description BFS 打印线段树
     */
    private void printTree(TreeNode root) {
        Stack<TreeNode> level0 = new Stack<TreeNode>();
        Stack<TreeNode> level1 = new Stack<TreeNode>();
        level0.push(root);
        while (!(level0.empty() && level1.empty())) {
            while (!level0.empty()) {
                TreeNode node = level0.pop();
                if (node == null) {
                    continue;
                }
                level1.push(node.right);
                level1.push(node.left);
                System.out.print(node.val + " , ");
            }
            System.out.println("------level------");
            while (!level1.empty()) {
                TreeNode node = level1.remove(0);
                if (node == null) {
                    continue;
                }
                level0.push(node.right);
                level0.push(node.left);
                System.out.print(node.val + " , ");
            }
            System.out.println("------level------");
        }
    }

    public static void main(String[] args) {
        SigmentTree l = new SigmentTree();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 10, 11, 12, 13, 15, 16, 17, 18, 18, 19, 20, 22};
        TreeNode root = l.buildTree(nums, 0, nums.length - 1);
        l.printTree(root);
    }
}
