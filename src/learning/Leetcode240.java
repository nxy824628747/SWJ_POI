package learning;

public class Leetcode240 {
    public final boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0||matrix[0].length==0) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (target > matrix[i][matrix[0].length - 1]) {
                continue;
            }
            if (target < matrix[i][0]) {
                return false;
            }
            if (searchRow(matrix, target, i)) {
                return true;
            }
        }
        return false;
    }

    private final boolean searchRow(int[][] matrix, int target, int rowNum) {
        int[] row = matrix[rowNum];
        int start = 0;
        int end = row.length;
        int mid = (start + end) / 2;
        while (start < end) {
            if (start == end - 1) {
                if (row[start] == target || row[end] == target) {
                    return true;
                }
                return false;
            }
            if (row[mid] == target) {
                return true;
            }
            if (row[mid] < target) {
                start = mid;
            } else if (row[mid] > target) {
                end = mid;
            }
            mid = (start + end) / 2;
        }
        return false;
    }

}
