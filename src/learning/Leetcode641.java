package learning;

public class Leetcode641 {

    class MyCircularDeque {

        class Node {
            int val;
            Node pre;
            Node next;

            Node(int val) {
                this.val = val;
            }
        }

        private final int capacity;
        private final Node head;
        private final Node end;
        private int length;

        /**
         * Initialize your data structure here. Set the size of the deque to be k.
         */
        public MyCircularDeque(int k) {
            this.capacity = k;
            this.head = new Node(0);
            this.end = new Node(0);
            head.next = end;
            end.pre = head;
            this.length = 0;
        }

        private final boolean addNodeToHead(int val) {
            if (this.length == capacity) {
                return false;
            }
            Node node = new Node(val);
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
            this.length++;
            return true;
        }

        private final boolean addNodeToEnd(int val) {
            if (this.length == capacity) {
                return false;
            }
            Node node = new Node(val);
            node.next = end;
            node.pre = end.pre;
            end.pre.next = node;
            end.pre = node;
            length++;
            return true;
        }

        private final boolean deleteFromHead() {
            Node node = head.next;
            if (node == end) {
                return false;
            }
            head.next = node.next;
            node.next.pre = head;
            node.pre = node.next = null;
            node = null;
            this.length--;
            return true;
        }

        private final boolean deleteFromEnd() {
            Node node = end.pre;
            if (node == head) {
                return false;
            }
            node.pre.next = end;
            end.pre = node.pre;
            node.pre = node.next = null;
            node = null;
            this.length--;
            return true;
        }

        /**
         * Adds an item at the front of Deque. Return true if the operation is successful.
         */
        public boolean insertFront(int value) {
            return addNodeToHead(value);
        }

        /**
         * Adds an item at the rear of Deque. Return true if the operation is successful.
         */
        public boolean insertLast(int value) {
            return addNodeToEnd(value);
        }

        /**
         * Deletes an item from the front of Deque. Return true if the operation is successful.
         */
        public boolean deleteFront() {
            return deleteFromHead();
        }

        /**
         * Deletes an item from the rear of Deque. Return true if the operation is successful.
         */
        public boolean deleteLast() {
            return deleteFromEnd();
        }

        /**
         * Get the front item from the deque.
         */
        public int getFront() {
            Node node = head.next;
            return node == end ? -1 : node.val;
        }

        /**
         * Get the last item from the deque.
         */
        public int getRear() {
            Node node = end.pre;
            return node == head ? -1 : node.val;
        }

        /**
         * Checks whether the circular deque is empty or not.
         */
        public boolean isEmpty() {
            return this.length == 0;
        }

        /**
         * Checks whether the circular deque is full or not.
         */
        public boolean isFull() {
            return this.length == this.capacity;
        }
    }
}
