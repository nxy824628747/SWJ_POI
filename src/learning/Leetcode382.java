package learning;

import com.timevale.tgtext.text.List;

import java.util.Random;

public class Leetcode382 {
    class Solution0 {
        ListNode head;
        Random random;

        public Solution0(ListNode head) {
            this.head = head;
            random = new Random(0);
        }

        public final int getRandom() {
            Integer an = null;
            int point = 1;
            ListNode node = head;
            while (node != null) {
                if (an == null) {
                    an = node.val;
                } else {
                    int ran = random.nextInt(point);
                    if (ran < 1) {
                        an = node.val;
                    }
                }
                node = node.next;
                point++;
            }
            return an == null ? 0 : an;
        }
    }

    /**
     * @Author Niuxy
     * @Date 2020/9/1 12:19 上午
     * @Description 随机数算法
     */
    class Solution {
        private ListNode head = null;
        private int size = 0;
        Random random = new Random(0);

        public Solution(ListNode head) {
            if (head != null) {
                this.head = head;
                ListNode node = head;
                while (node != null) {
                    node = node.next;
                    this.size++;
                }
            }
        }

        /**
         * Returns a random node's value.
         */
        public final int getRandom() {
            if (size <= 1) {
                return head.val;
            }
            int currentRandom = random.nextInt() % size;
            return getNode(currentRandom).val;
        }

        private final ListNode getNode(int point) {
            if (point >= this.size) {
                return null;
            }
            ListNode an = head;
            while (point > 0) {
                an = an.next;
                point--;
            }
            return an;
        }

    }


    public static void main(String[] args) {
        Random random = new Random(0);
        int zero = 0;
        int one = 0;
        int two = 0;
        for (int i = 0; i < 100000; i++) {
            int cur = random.nextInt(3);
            switch (cur) {
                case 0:
                    zero++;
                    break;
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
            }
        }
        System.out.print("0: " + zero + " , 1: " + one + " , 2: " + two);
    }
}
