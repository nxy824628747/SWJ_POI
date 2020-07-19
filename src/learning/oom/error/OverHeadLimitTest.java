package learning.oom.error;

import java.util.HashMap;
import java.util.Map;

public class OverHeadLimitTest {
    public static void main(String[] args) {
        add();
    }

    class Unit {
        Integer id;
        int[] nums;

        Unit(Integer id) {
            this.id = id;
            nums=new int[500];
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    private static void add() {
        Map<Unit, Unit> map = new HashMap<Unit, Unit>();
        OverHeadLimitTest o = new OverHeadLimitTest();
        while (true) {
            for (int i = 0; i < 1000; i++) {
                Unit u = o.new Unit(i);
                if (!map.containsKey(u)) {
                    map.put(u, u);
                    System.out.println(map.size());
                }
            }
        }
    }

}
