package learning;

public class Leetcode88 {
    public void merge0(int[] nums1, int m, int[] nums2, int n) {
        int point1 = m - 1;
        int point2 = n - 1;
        int point3 = m + n - 1;
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        if (n == 0) {
            return;
        }
        while (point2 >= 0) {
            if (nums1[point1] < nums2[point2]) {
                nums1[point3] = nums2[point2];
            } else {
                nums1[point3] = nums1[point1];
                nums1[point1] = nums2[point2];
                int point4 = m - 1;
                while (point4 > 0 && nums1[point4] < nums1[point4 - 1]) {
                    int temp = nums1[point4];
                    nums1[point4] = nums1[point4 - 1];
                    nums1[point4 - 1] = temp;
                    point4--;
                }
            }
            point2--;
            point3--;
        }
    }

    /**
    * @Author Niuxy
    * @Date 2020/7/2 9:16 下午
    * @Description 双指针尾插法
    */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int point1 = m - 1;
        int point2 = n - 1;
        int point = m + n - 1;
        while ((point1 >= 0) && (point2 >= 0)) {
            nums1[point--] = (nums1[point1] < nums2[point2]) ? nums2[point2--] : nums1[point1--];
        }
        System.arraycopy(nums2, 0, nums1, 0, point2 + 1);
    }
}
