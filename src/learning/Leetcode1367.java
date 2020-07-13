package learning;

import java.util.Stack;

public class Leetcode1367 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public final boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (stack.size() > 0) {
            TreeNode node = stack.pop();
            if (isSubPathDP(head, node)) {
                return true;
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return false;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/5 9:20 下午
     * @Description 暴力解法毫无疑问要求链表所有节点与二叉树所有节点的笛卡尔积
     * G(l,t) 为 链表中的 l 节点是否作为二叉树中的 t 节点,后续元素是否全部匹配
     */
    public final boolean isSubPathDP(ListNode l, TreeNode t) {
        if (l == null) {
            return true;
        }
        if (t == null || l.val != t.val) {
            return false;
        }
        return isSubPathDP(l.next, t.left) || isSubPathDP(l.next, t.right);
    }
}
