package learning;

public class Leetcode74 {
    public final boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return search(matrix, target, 0);
    }

    private final boolean search(int[][] source, int target, int row) {
        if (row == source.length) {
            return false;
        }
        int len = source[row].length;
        if (source[row][len - 1] < target) {
            return search(source, target, row + 1);
        }
        int begin = 0;
        int end = len - 1;
        int mid = (begin + end) / 2;
        while (begin <= end) {
            if (begin == end || begin == end - 1) {
                if (source[row][begin] == target || source[row][end] == target) {
                    return true;
                }
                return false;
            }
            if (source[row][mid] == target) {
                return true;
            }
            if (source[row][mid] > target) {
                end = mid;
            } else {
                begin = mid;
            }
            mid = (begin + end) / 2;
        }
        return false;
    }
}
