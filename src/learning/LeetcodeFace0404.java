package learning;

import java.util.HashMap;
import java.util.Map;

public class LeetcodeFace0404 {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int diff = getDepth(root.left) - getDepth(root.right);
        diff = diff > 0 ? diff : diff * -1;
        if ((diff <= 1)) {
            return false;
        }
        return isBalanced(root.right) && isBalanced(root.left);
    }

    public int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }
}
