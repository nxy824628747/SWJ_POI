package learning;


import java.util.Stack;

public class Leetcode623 {


    public final TreeNode addOneRowDFS(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(root.val);
            newRoot.left = root.left;
            newRoot.right = root.right;
            root.left = newRoot;
            root.right = null;
            root.val = v;
            return root;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        dfs(root, v, d, stack);
        addRow(stack, v);
        return root;
    }

    public final void dfs(TreeNode node, int v, int d, Stack<TreeNode> stack) {
        if (node == null) {
            return;
        }
        if (d == 2) {
            stack.push(node);
            return;
        }
        dfs(node.left, v, d - 1, stack);
        dfs(node.right, v, d - 1, stack);
    }


    private final void addRow(Stack<TreeNode> stack, int v) {
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            TreeNode newNodeLeft = new TreeNode(v);
            TreeNode newNodeRight = new TreeNode(v);
            newNodeLeft.left = node.left;
            newNodeRight.right = node.right;
            node.left = newNodeLeft;
            node.right = newNodeRight;
        }
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/17 11:17 上午
     * @Description BFS
     */
    public final TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(root.val);
            newRoot.left = root.left;
            newRoot.right = root.right;
            root.left = newRoot;
            root.right = null;
            root.val = v;
            return root;
        }
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.push(root);
        int length = 1;
        while (!(stack1.empty() && stack1.empty())) {
            while (!stack1.empty()) {
                boolean isInsert = isInsert(stack1, stack2, d, v, length, root);
                if (isInsert) {
                    return root;
                }
            }
            length++;
            while (!stack2.empty()) {
                boolean isInsert = isInsert(stack2, stack1, d, v, length, root);
                if (isInsert) {
                    return root;
                }
            }
            length++;
        }
        return root;
    }

    private final boolean isInsert(Stack<TreeNode> stack1, Stack<TreeNode> stack2, int d,
                                   int v, int length, TreeNode root) {
        if (length == d - 1) {
            addRow(stack1, v);
            return true;
        }
        TreeNode node = stack1.pop();
        if (node.left != null) {
            stack2.push(node.left);
        }
        if (node.right != null) {
            stack2.push(node.right);
        }
        return false;
    }


}
