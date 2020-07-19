package learning;

import java.util.HashMap;
import java.util.Map;

public class Leetcode1625 {

    class LRUCache {

        class ListNode {
            ListNode pre;
            ListNode next;
            int val;
            int key;

            ListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private final int capacity;
        private final Map<Integer, ListNode> pool;
        private final ListNode head;
        private final ListNode last;
        private int length;

        LRUCache(int capacity) {
            this.capacity = capacity;
            pool = new HashMap<Integer, ListNode>();
            head = new ListNode(0, 0);
            last = new ListNode(0, 0);
            head.next = last;
            last.pre = head;
            length = 0;
        }


        public int get(int key) {
            if (!pool.containsKey(key)) {
                return -1;
            }
            ListNode node = pool.get(key);
            removeToHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            if (pool.containsKey(key)) {
                ListNode node = pool.get(key);
                node.val = value;
                removeToHead(node);
                return;
            }
            ListNode node = new ListNode(key, value);
            addToHead(node);
            pool.put(key, node);
            length++;
            if (length > capacity) {
                pool.remove(removeLast().key);
            }
        }

        private void removeNode(ListNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = node.next = null;
        }

        private void addToHead(ListNode node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }

        private void removeToHead(ListNode node) {
            removeNode(node);
            addToHead(node);
        }

        private ListNode removeLast() {
            ListNode node = last.pre;
            if (node == head) {
                return null;
            }
            removeNode(node);
            return node;
        }
    }

}
