package learning.dcl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Niuxy
 * @Date 2020/6/8 8:54 下午
 * @Description 基于双向链表的简单 FIFO 队列
 * 锁的粒度很粗，仅针对最上层操作进行互斥同步关系规划
 * 最上层方法有两个：put 与 remove
 * 以 length 与 capacity 是否相等为竞态条件进行同步
 * put 与 remove 操作涉及了相同的共享变量，动作互斥
 * 只要在某个时刻，两个动作的竞态条件必然有一个会得到满足，便至少有一个线程处于运行状态
 * 锁的设计越少越安全，但粒度太粗的互斥关系也会降低运行效率。如果锁较多，需要注意锁依赖（获取顺序）的一致性，防止死锁
 */
public class SynchronizedQueue<T> {

    /**
     * @Author Niuxy
     * @Date 2020/6/8 9:01 下午
     * @Description 双向链表节点
     */
    class Node {
        private T value;
        private Node next;
        private Node pre;

        Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }
    }

    // 队列容量
    private int capacity;
    // 队列当前长度
    private int length;

    //虚拟头结点
    private Node firstNode;
    //虚拟尾结点
    private Node lastNode;

    SynchronizedQueue(int capacity) {
        this.capacity = capacity;
        this.length = 0;
        firstNode = new Node(null);
        lastNode = new Node(null);
        firstNode.setNext(lastNode);
        lastNode.setPre(firstNode);
    }

    // 移除并返回队尾节点
    public synchronized T remove() throws InterruptedException {
        while (length == 0) {
            wait();
        }
        Node node = lastNode.pre;
        while (length <= 0) {
            throw new RuntimeException("outOfIndex:" + String.valueOf(length - capacity));
        }
        node.pre.next = lastNode;
        lastNode.pre = node.pre;
        length--;
        notifyAll();
        return node.value;
    }

    // 新增节点，放入队首
    public synchronized void put(T value) throws InterruptedException {
        while (length == capacity) {
            wait();
        }
        Node node = new Node(value);
        node.next = firstNode.next;
        node.pre = firstNode;
        firstNode.next = node;
        node.next.pre = node;
        length++;
        print();
        notifyAll();
    }

    private synchronized void print() {
        Node node = firstNode.next;
        while (node != lastNode) {
            System.out.print(node.value + ",");
            node = node.next;
        }
        System.out.println("---------");
    }
}
