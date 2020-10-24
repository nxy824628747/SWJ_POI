package learning;

import org.apache.commons.collections4.queue.TransformedQueue;

import java.util.LinkedList;
import java.util.List;

public class Leetcode228 {
    public final List<String> summaryRanges(int[] nums) {
        List<String> reList = new LinkedList<String>();
        if (nums.length == 0) {
            return reList;
        }
        int start = nums[0];
        int end = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == end + 1) {
                end = nums[i];
                continue;
            }
            this.add(start, end, reList);
            start = nums[i];
            end = nums[i];
        }
        this.add(start, end, reList);
        return reList;
    }

    private final void add(int start, int end, List<String> reList) {
        if (start == end) {
            reList.add(String.valueOf(start));
            return;
        }
        reList.add(String.valueOf(start) + "->" + String.valueOf(end));
    }
}
