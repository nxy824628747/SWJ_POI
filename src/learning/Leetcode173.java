package learning;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Leetcode173 {

    class BSTIterator2 {
        final Stack<TreeNode> stack;

        public BSTIterator2(TreeNode root) {
            stack = new Stack<TreeNode>();
            pushLeft(root);
        }

        private void pushLeft(TreeNode node) {
            if (node == null) {
                return;
            }
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * @return the next smallest number
         */
        public final int next() {
            if (stack.size() == 0) {
                return -1;
            }
            TreeNode node = stack.pop();
            if (node.right != null) {
                pushLeft(node.right);
            }
            return node.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public final boolean hasNext() {
            return stack.size() != 0;
        }
    }


    class BSTIterator {
        final List<TreeNode> treeList;
        int currentPoint;
        int length;

        public BSTIterator(TreeNode root) {
            treeList = new LinkedList<TreeNode>();
            toList(root);
            currentPoint = 0;
            length = treeList.size();
        }

        private final void toList(TreeNode node) {
            if (node == null) {
                return;
            }
            toList(node.left);
            treeList.add(node);
            toList(node.right);
        }

        /**
         * @return the next smallest number
         */
        public final int next() {
            if (currentPoint == length) {
                throw new RuntimeException("Out of index " + currentPoint + " with length of " + length);
            }
            currentPoint++;
            return treeList.get(currentPoint - 1).val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public final boolean hasNext() {
            return currentPoint < length;
        }
    }
}
