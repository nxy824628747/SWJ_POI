package learning;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Leetcode365 {
    public static void main(String[] args) {
        Leetcode365 l = new Leetcode365();
        l.canMeasureWater(104579, 104593, 12444);
    }

    private final boolean canMeasureWaterBFS(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        Queue<Long> queue = new LinkedBlockingQueue<>();
        Set<Long> set = new HashSet<Long>();
        queue.add((long) 0);
        while (!queue.isEmpty()) {
            long currLon = queue.poll();
            if (set.contains(currLon)) continue;
            set.add(currLon);
            int currX = (int) (currLon >> 32);
            int currY = (int) currLon;
            if (currX == z || currY == z || currX + currY == z) {
                return true;
            }
            if (currX != 0) queue.add((long) currY);
            if (currY != 0) queue.add(((long) currX) << 32);
            if (currX != x) queue.add(combin(x, currY));
            if (currY != y) queue.add(combin(currX, y));
            if (currY < y && currX > 0) {
                if (currX > y - currY) queue.add(combin(currX - y + currY, y));
                else queue.add(combin(0, currY + currX));
            }
            if (currX < x && currY > 0) {
                if (currY > x - currX) queue.add(combin(x, currY - x + currX));
                else queue.add(combin(currX + currY, 0));
            }
        }
        return false;
    }

    public final boolean canMeasureWater(int x, int y, int z) {
        return measureWater(x, y, z, 0, 0, new HashSet<Long>());
    }

    private final boolean measureWater(int x, int y, int z, int xc, int yc, Set<Long> set) {
        if (xc == z || yc == z || xc + yc == z) return true;
        long key = combin(x, y);
        if (set.contains(key)) return false;
        System.out.println("x:" + xc + " , y:" + yc);
        set.add(key);
        //倒满
        if ((x < xc && measureWater(x, y, z, x, yc, set)) || (y < yc && measureWater(x, y, z, xc, y, set)))
            return true;
        //清空
        if ((xc > 0 && measureWater(x, y, z, 0, yc, set)) || (yc > 0 && measureWater(x, y, z, xc, 0, set)))
            return true;
        //y 倒入 x
        if (xc < x && yc > 0) {
            if (yc > x - xc) {
                if (measureWater(x, y, z, x, yc - x + xc, set)) return true;
            } else {
                if (measureWater(x, y, z, xc + yc, 0, set)) return true;
            }
        }
        //x 倒入 y
        if (yc < y && xc > 0) {
            if (xc > y - yc) {
                if (measureWater(x, y, z, xc - y + yc, y, set)) return true;
            } else {
                if (measureWater(x, y, z, 0, xc + yc, set)) return true;
            }
        }
        return false;
    }

    private long combin(int x, int y) {
        long xLong = (long) x;
        xLong = xLong << 32;
        return xLong | y;
    }
}
