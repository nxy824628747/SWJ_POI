package learning;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Leetcode129 {

    StringBuilder sb = new StringBuilder();
    int re = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getNums(root);
        return re;
    }

    public void getNums(TreeNode root) {
        if (root.right == null && root.left == null) {
            sb.append(root.val);
            Integer num = Integer.valueOf(sb.toString());
            re += num;
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        sb.append(root.val);
        if (root.left != null) {
            getNums(root.left);
        }
        if (root.right != null) {
            getNums(root.right);
        }
        sb.deleteCharAt(sb.length() - 1);
    }


    public final int sumNumbers0(TreeNode root) {
        return getNums(root, 0);
    }

    public final int getNums(TreeNode root, int preSum) {
        if (root == null) {
            return 0;
        }
        int sum = preSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return getNums(root.right, sum) + getNums(root.left, sum);
    }
}
