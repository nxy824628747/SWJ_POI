package learning;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Leetcode935 {
    BigInteger mod = new BigInteger("1000000007");

    public int knightDialer(int N) {
        if (N == 0) {
            return 0;
        }
        Map<String, BigInteger> cache = new HashMap<String, BigInteger>();
        BigInteger re = new BigInteger("0");
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 4; y++) {
                re = re.add(g(x, y, N - 1, cache));
            }
        }
        return re.mod(mod).intValue();
    }

    /**
     * @Author Niuxy
     * @Date 2020/5/24 10:06 下午
     * @Description 问题为骑士跳 N 步有多少种跳法
     * 我们以步数 N 分割问题，此时发现，影响问题解的不只是步数，还在于骑士当前处在什么位置
     * 因为这决定了骑士下一步会不会越界
     * 所以我们以骑士所处的位置 x,y 以及剩余跳的步数 n 来定义问题
     * g(x,y,n) 表示处在 x，y 位置的骑士跳 n 步有多少种跳法
     * 首先可以肯定的是，x,y,n 三个纬度可以唯一确定一个解，那么我们再来尝试推演状态转移关系
     * 如果存在正确的状态转移关系，则该定义可用
     * 明显的，状态转移关系为：
     * g(x,y,n)=g(x-1,y-2,n-1)+g(x+1,y-2,n-1)+g(x+2,y-1,n-1)+g(x+2,y+1,n-1)+g(x+1,y+2,n-1)
     * +g(x-1,y+2,n-1)+g(x-2,y+1,n-1)+g(x-2,y-1,n-1)
     * 在号码盘上，我们要首先处理处理回归边界，再判断越界
     * x>2 , y>3 ,(0,0),(2,0) 为越界情况
     * n = 0 为 回归条件，回归 1
     * 解空间为一颗 8 叉树（暂且这么叫吧），每一层的 8 个节点发散下去后，难免存在与同层其他节点发散到相同子问题的情况，
     * 事实上该情况还比较多
     * 那么建立缓存可以很好的帮助我们避免重复计算，或者换句话说，该问题定义方式帮我们找出了许多解空间中可重复利用的部分
     * 对时间复杂度要求不是很极端的话，该思路应该可以通过
     */
    public final BigInteger g(int x, int y, int n, Map<String, BigInteger> cache) {
        if (x < 0 || y < 0 || x > 2 || y > 3 || (x == 2 && y == 0) || (x == 0 && y == 0)) {
            return new BigInteger("0");
        }
        if (n == 0) {
            return new BigInteger("1");
        }
        String key = String.valueOf(x) + "|" + String.valueOf(y) + "|" + String.valueOf(n);
        if (cache.keySet().contains(key)) {
            return cache.get(key);
        }
        BigInteger re = new BigInteger("0");
        re = re.add(g(x - 1, y + 2, n - 1, cache));
        re = re.add(g(x + 1, y + 2, n - 1, cache));
        re = re.add(g(x + 2, y + 1, n - 1, cache));
        re = re.add(g(x + 2, y - 1, n - 1, cache));
        re = re.add(g(x + 1, y - 2, n - 1, cache));
        re = re.add(g(x - 1, y - 2, n - 1, cache));
        re = re.add(g(x - 2, y - 1, n - 1, cache));
        re = re.add(g(x - 2, y + 1, n - 1, cache));
        cache.put(key, re);
        return re;
    }
}
