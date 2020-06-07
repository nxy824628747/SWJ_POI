package learning;

/**
 * @Author Niuxy
 * @Date 2020/6/5 11:40 下午
 * @Description 740 删除与获得点数
 */
public class LeetCode740 {
    public int deleteAndEarn(int[] nums) {
        return 0;
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/5 11:41 下午
     * @Description
     * 每次取得一个点数，便会失去与其相差 1 的点数
     * 首先不改变问题定义，尝试推演状态转移关系
     * G(n) 表示前 n 个元素能获取的最大点数，因为无法判断第 n+1 个元素是否可被选取，无法列出状态转移关系
     * 尝试使函数可以体现最后一个取得的点数，以此为状态转移过程提供转移依据：
     * G(n,i) 表示前 n 个元素最后取第 i 个元素可获得的最大点数。问题这样定义依然很难获得状态转移关系，
     * G(n+1,n+1)=Max ( G(n,i)+ (nums[i]-nums[n+1])==1||(nums[i]-nums[n+1])==-1 ? 0 : nums[n+1] ) | i 为 0 到 n
     * n==1 时回归，回归 nums[n]
     *
     *
     */
}
