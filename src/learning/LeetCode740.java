package learning;

import java.util.*;

/**
 * @Author Niuxy
 * @Date 2020/6/5 11:40 下午
 * @Description 740 删除与获得点数
 */
public class LeetCode740 {

    /**
     * @Author Niuxy
     * @Date 2020/6/16 7:45 下午
     * @Description 那么定义问题需要：
     *             1. 因为问题是递归结构的，最大规模的问题必须可以覆盖解空间
     *             2. 解决问题的函数不能产生函数副作用
     *             3. 如果函数存在副作用，尝试重新定义问题。重新定义问题的首要任务是消除函数副作用。
     *             4. 消除函数的副作用，一般需要在某些逻辑上做反转。比如选取变为不选取，比如无序变为有序。
     */
    public int deleteAndEarn2(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int max=0;
        for(int i=0;i<nums.length;i++){
            max=Math.max(max,nums[i]);
        }
        int[] nums2=new int[max+1];
        for(int num:nums){
            nums2[num]++;
        }
        int[] dp=new int[nums2.length];
        if(nums2.length==1){
            return nums2[0];
        }
        if(nums2.length==2){
            return Math.max(nums2[0],nums2[1]);
        }
        if(nums2.length==3){
            return Math.max(nums2[1],nums2[0]+nums2[2]);
        }
        dp[0]=nums2[0];
        dp[1]=nums2[1];
        dp[2]=nums2[0]+nums2[2]*2;
        int maxRe=0;
        for(int i=3;i<dp.length;i++){
            dp[i]=Math.max(dp[i-2],dp[i-3])+nums2[i]*i;
            maxRe=Math.max(dp[i],maxRe);
        }
        return maxRe;
    }


    /**
     * @Author Niuxy
     * @Date 2020/6/5 11:41 下午
     * @Description 每次取得一个点数，便会失去与其相差 1 的点数
     * 首先不改变问题定义，尝试推演状态转移关系
     * G(n) 表示前 n 个元素能获取的最大点数，若要判断第 n+1 个元素是否可被选取，必须维护额外的数据结构用于存储 G(n) 中已选取过的元素。
     * 这样若针对 n 做缓存，除缓存其结果外，还要缓存其选过的元素，方法可行但空间复杂度太高，平方级的空间复杂度。
     * 算法最诱人的地方便是，算法锻炼的是解决问题的思维方式。这与实际生活中，解决问题的思维方式是互通的。通过算法的
     * 练习可以锻炼自己的思维，同时实际生活中解决问题的思路也可以为算法提供灵感。
     * 资治通鉴评价窦建德为解洛阳之围，在虎牢正面对抗李世民的决策。如果他采纳了凌敬的建议，渡过黄河威胁长安，洛阳之围自然而解，且可趁李世民主要
     * 兵力都在洛阳和虎牢之际，顺路将河东纳入囊中。倘若如此，后面的历史可能就是另外一番局面了。窦建德不顾解洛阳之围的主要目的，一心想借士气
     * 之威大破唐军，本末倒置，落得个惨淡收场，实在可惜。
     * 解决问题一定要抓住主要矛盾，摒弃其余琐碎的干扰因素。同时一条路不通可以尝试其它方式，保证结果不变的情况下转化问题的定义
     * 对于本题而言，因为每次拿都是随机的，且拿一次，值相邻的所有元素都不能再拿。
     * <p>
     * 是否可以用分治法的关键在于，将问题递归的分解为定义相同但规模更小的子问题后，原问题的解是否可以由子问题的解表示。
     * 因此使用回溯算法暴力求解的题目很让人头痛。回溯就代表着，递归结构中下一步操作不仅依赖上一步操作的结果，还依赖上一步操作对周遭环境产生
     * 的副作用。每一步的求解都会对周围环境产生副作用，这意味着在该情况下建立缓存非常困难。缓存的坐标不仅有入参，还必须包含环境的状态。
     * 就比如回溯算法的典型八皇后，并没有相应的 分治或 dp 解法。
     * 因此如果暴力解法包含回溯操作，想要依靠分治法降低时间复杂度，就必须尝试重新定义问题，将问题的求解对环境产生的副作用转移到问题与子问题的转移关系中。
     * 将副作用转移到状态转移关系中听起来很模糊，以本题为例：
     * 细想本题回溯过程中产生的副作用：用过的元素决定着哪些元素不可使用，因此需要记录用过哪些元素。
     * 使用元素的互斥关系为不能同时使用相邻的元素，因为数组不是顺序排列的，所以必须记录下来使用了哪些元素。
     * 如果元素是顺序排列的，那么可以通过求最值的方式将副作用转移到状态转移方程中。在使用第 n 个元素时，可能使用了第 n-2 个元素，也可能使用了第
     * n-3 个元素。这两种情况可以覆盖使用第 n 个元素获取最大点数的所有情况。
     * nums 为排序后的源数组，并对相同项进行了合并。设 G(n) 为以第 n 个元素结尾可拿到的最大点数。从 0 ~ nums.length-1 可以覆盖解空间。
     * G(n) = max{ G(n-2)+nums[n],G(n-3)+nums[n] } + nums[n]
     * n=0,1,2 时问题回归
     */
    LinkedList<Node> list = new LinkedList<Node>();
    Map<Integer, Node> map = new HashMap<Integer, Node>();

    class Node {
        Node(int value) {
            this.value = value;
            this.num = 1;
        }

        int value;
        int num;
    }

    private final void insert(int value) {
        if (map.containsKey(value)) {
            Node node = map.get(value);
            node.num = node.num + 1;
        } else {
            Node node = new Node(value);
            map.put(value, node);
            list.add(node);
        }
    }

    private final void sortAndCombine(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            insert(nums[i]);
        }
        list.sort(
                new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        return ((Integer) o1.value).compareTo(o2.value);
                    }
                }
        );
    }

    private final void addNull() {
        int length = list.size();
        for (int i = 1; i < length; i++) {
            if ((list.get(i).value - list.get(i - 1).value) > 1) {
                Node node = new Node(getNode(i - 1) + 1);
                node.num = 0;
                list.add(i, node);
                length++;
            }
        }
    }

    public int delete1(List<Node> nums, int flag, Map<Integer, Integer> cache) {
        if (nums.size() == 0 || flag < 0) {
            return 0;
        }
        if (cache.containsKey(flag)) {
            return cache.get(flag);
        }
        if (flag == 0) {
            return getNode(0);
        }
        if (flag == 1) {
            return getNode(1);
        }
        if (flag == 2) {
            return getNode(0) + getNode(2);
        }
        int preTwo = delete1(nums, flag - 2, cache);
        int preThree = delete1(nums, flag - 3, cache);
        cache.put(flag, Math.max(preTwo, preThree) + getNode(flag));
        return cache.get(flag);
    }

    private int getNode(int i) {
        Node node = list.get(i);
        return node.value * node.num;
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/14 9:02 下午
     * @Description 暴力回溯 超时但结果正确
     */
    int an = 0;
    int tar = 0;

    public int deleteAndEarn(int[] nums) {
        tar = nums.length;
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> flags = new HashSet<Integer>();
            Map<Integer, Integer> disables = new HashMap<Integer, Integer>();
            delete(nums, i, flags, disables, 0);
        }
        return an;
    }

    public void delete(int[] nums, int flag, Set<Integer> flags, Map<Integer, Integer> disables, int re) {
        if (tar == flags.size()) {
            an = an > re ? an : re;
            tar = nums.length;
            return;
        }
        if (flags.contains(flag)) {
            return;
        }
        if ((disables.containsKey(nums[flag] + 1) && disables.get(nums[flag] + 1) > 0
                || (disables.containsKey(nums[flag] - 1) && disables.get(nums[flag] - 1) > 0))) {
            tar--;
            return;
        }
        flags.add(flag);
        if (!disables.containsKey(nums[flag])) {
            disables.put(nums[flag], 1);
        } else {
            disables.put(nums[flag], disables.get(nums[flag]) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            delete(nums, i, flags, disables, re + nums[flag]);
        }
        disables.put(nums[flag], disables.get(nums[flag]) - 1);
        flags.remove(flag);
    }

    public static void main(String[] args) {
        int source[] = {8,10,4,9,1,3,5};
        LeetCode740 lee = new LeetCode740();
        int re = lee.deleteAndEarn2(source);
        System.out.println(re);
    }


}
