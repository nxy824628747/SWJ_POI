package learning;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Leetcode404 {
    public final int sumOfLeftLeaves(TreeNode root) {
        List<TreeNode> leftLeafs = new LinkedList<TreeNode>();
        searchLeftLeaves(root, false, leftLeafs);
        return sumLeftLeafs(leftLeafs);
    }

    private final void searchLeftLeaves(TreeNode node, boolean isLeft, List<TreeNode> leftLeafs) {
        if (node == null) return;
        if (isLeft && node.left == null && node.right == null) {
            leftLeafs.add(node);
        }
        searchLeftLeaves(node.left, true, leftLeafs);
        searchLeftLeaves(node.right, false, leftLeafs);
    }

    private final int sumLeftLeafs(List<TreeNode> leftLeafs) {
        int res = 0;
        for (TreeNode node : leftLeafs) {
            res += node.val;
        }
        return res;
    }

    public final int sumOfLeftLeaves0(TreeNode root) {
        return sumOfLeftLeavesDFS(root);
    }

    public final int sumOfLeftLeavesDFS(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        if (root.left != null) {
            if (isLeaf(root.left)) res += root.left.val;
            else res += sumOfLeftLeavesDFS(root.left);
        }
        if (root.right != null && !isLeaf(root.right)) res += sumOfLeftLeavesDFS(root.right);
        return res;
    }

    private final boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public final int sumOfLeftLeavesBFS(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (isLeaf(node.left)) res += node.left.val;
                else queue.offer(node.left);
            }
            if (node.right != null && !isLeaf(node.right)) queue.offer(node.right);
        }
        return res;
    }

}