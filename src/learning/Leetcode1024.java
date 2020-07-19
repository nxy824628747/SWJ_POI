package learning;

import java.util.*;

public class Leetcode1024 {
    //头结点集合
    final List<int[]> heads = new LinkedList<int[]>();
    //最大尾部值
    int maxEnd;

    public int videoStitching(int[][] clips, int T) {
        sort(clips);
        findSomething(clips);
        if (heads.size() == 0 || maxEnd < T) {
            return -1;
        }
        int re = Integer.MAX_VALUE;
        Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
        for (int i = 0; i < heads.size(); i++) {
            int an = videoStitching(clips, T, heads.get(i)[1], i, cache);
            if (an == -1) {
                continue;
            }
            re = Math.min(an, re);
        }
        return re == Integer.MAX_VALUE ? -1 : re;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/18 8:41 下午
     * @Description 以第 point 个元素结尾，当前尾部值为 end ，目标尾部值为 T 的所需的最少元素数
     */
    private final int videoStitching(int[][] clips, int T, int end, int point, Map<Integer, Integer> cache) {
        if (end >= T) {
            System.out.println(clips[point][0] + "," + clips[point][1]);
            return 1;
        }
        if (point == clips.length) {
            return 0;
        }
        int key = end + point * 1113;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        //达成目标所需最小元素数
        int re = Integer.MAX_VALUE;
        int next = point + 1;
        //尝试所有拼接可能
        for (int i = point + 1; i < clips.length; i++) {
            //不满足拼接条件
            if (clips[i][1] <= clips[point][1] || clips[i][0] > clips[point][1]) {
                continue;
            }
            int an = videoStitching(clips, T, clips[i][1], i, cache);
            if (an != -1) {
                re = Math.min(re, an + 1);
            }
        }
        re = re == Integer.MAX_VALUE ? -1 : re;
        //缓存结果。复用
        cache.put(key, re);
        return re;
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/18 4:42 下午
     * @Description 按视频开始时间排序
     */
    private final void sort(int[][] clips) {
        for (int i = 0; i < clips.length; i++) {
            for (int j = i + 1; j < clips.length; j++) {
                if (clips[j][0] < clips[i][0]) {
                    int[] temp = clips[j];
                    clips[j] = clips[i];
                    clips[i] = temp;
                }
            }
        }
    }

    /**
     * @Author Niuxy
     * @Date 2020/7/18 8:40 下午
     * @Description 筛选头元素，并找出尾元素最大值
     */
    private final void findSomething(int[][] clips) {
        for (int[] num : clips) {
            maxEnd = maxEnd > num[1] ? maxEnd : num[1];
            if (num[0] == 0) {
                heads.add(num);
            }
        }
    }


    public static void main(String[] args) {
        Leetcode1024 l = new Leetcode1024();
        int[][] source = {{3, 6}, {3, 6}, {0, 4}, {6, 6}, {8, 10}, {6, 10}, {0, 1}, {6, 9}};
        System.out.println("res: " + l.videoStitching(source, 2));
    }

    private void print(int[][] clips) {
        for (int i = 0; i < clips.length; i++) {
            System.out.println(clips[i][0] + "," + clips[i][1]);
        }
        System.out.print("---");
    }
}
