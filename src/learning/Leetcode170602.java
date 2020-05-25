package learning;

public class Leetcode170602 {

    public int numberOf2sInRange(int n) {
        return g(n);
    }

    /**
     * @Author Niuxy
     * @Date 2020/5/24 9:19 下午
     * @Description 计算从 0 到 n 中，出现 2 到次数
     * 问题本身具有最优子结构性质，可以以 n 为边界分割问题
     * G(n) 表示本问题，则：
     * G(n+1) = G(n)+ (n+1).contains("2")?1:0
     * 以 n 为纬度建立缓存没有必要，因为 G(n) 仅依赖 G(n-1),计算过程不存在重复计算问题
     */
    public final int g(int n) {
        int con = 0;
        StringBuilder nStr = new StringBuilder(String.valueOf(n));
        int index;
        while ((index = nStr.lastIndexOf("2")) != -1) {
            nStr.deleteCharAt(index);
            con++;
        }
        if (n == 0) {
            return con;
        }
        return g(n - 1) + con;
    }


}
