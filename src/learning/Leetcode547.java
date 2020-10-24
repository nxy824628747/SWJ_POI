package learning;

import java.util.HashSet;
import java.util.Set;

public class Leetcode547 {
    public final int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int re = 0;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] != 1) continue;
                M[i][j] = 0;
                search(M, j, i + 1);
                re++;
            }
        }
        return re;
    }

    private final void search(int[][] M, int x, int begin) {
        for (int i = 0; i < M.length; i++) {
            if (M[i][x] == 1) {
                M[i][x] = 0;
                search(M, i, begin + 1);
            }
        }
    }


    class UnionFind {

        int[] parents;

        private final int find(int point) {
            if (parents[point] == point) return point;
            return parents[point] = find(parents[point]);
        }

        private final void union(int point0, int point1) {
            int parent0 = find(point0);
            int parent1 = find(point1);
            if (parent0 != parent1) parents[parent0] = parent1;
        }


        public final int findCircleNum(int[][] M) {
            if (M.length == 0) return 0;
            int re = 0, len = M.length;
            parents = new int[len];
            for (int i = 0; i < len; i++) parents[i] = i;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) if (M[i][j] == 1) union(i, j);
            }
            for (int i = 0; i < len; i++) {
                if (parents[i] == i) re++;
            }
            return re;
        }
    }

}
