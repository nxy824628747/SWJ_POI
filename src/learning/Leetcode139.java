package learning;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        return false;
    }

    private boolean search(String s, Set<String> set, int point) {
        if (point == -1) {
            return true;
        }
        boolean pre = search(s, set, point - 1);
        for (int i = point; i < s.length(); i++) {
            if (pre && set.contains(s.substring(point, point + i))) {
                return true;
            }
        }
        return false;
    }

    private final Set<String> listToSet(List<String> worldDict) {
        Set<String> set = new HashSet<String>();
        for (String s : worldDict) {
            set.add(s);
        }
        return set;
    }
}
