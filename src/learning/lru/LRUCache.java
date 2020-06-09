package learning.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    /**
     * @Author Niuxy
     * @Date 2020/6/7 12:20 上午
     * @Description 双向链表，记录最近访问顺序
     */
    private class LRUNode {
        LRUNode(Integer key,Integer value) {
            this.value = value;
            this.key = key;
        }

        LRUNode next;
        LRUNode pre;
        Integer value;
        Integer key;
    }

    //虚拟头结点
    private LRUNode firstNode;
    //虚拟尾结点
    private LRUNode lastNode;
    //当前数据长度
    private int size;
    //缓存容量
    private int capacity;

    private Map<Integer, LRUNode> cacheMap;

    LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.firstNode = new LRUNode(0, Integer.MIN_VALUE);
        this.lastNode = new LRUNode(0, Integer.MAX_VALUE);
        this.cacheMap = new HashMap<Integer, LRUNode>();
        firstNode.next = lastNode;
        lastNode.pre = firstNode;
    }

    //查找元素
    public Integer get(Integer key) {
        LRUNode node = cacheMap.get(key);
        if (node == null) {
            return -1;
        }
        removeToHead(node);
        return node.value;
    }

    //新增元素
    public void put(Integer key, Integer value) {
        LRUNode beforeNode = cacheMap.get(key);
        //key 已存在，覆盖
        if (beforeNode != null) {
            beforeNode.value = value;
            removeToHead(beforeNode);
            return;
        }
        LRUNode node = new LRUNode(key, value);
        cacheMap.put(key, node);
        putToHead(node);
        size++;
        if (size > capacity) {
            removeLast();
            size--;
        }
        printList();
    }

    private final void putToHead(LRUNode node) {
        node.next = firstNode.next;
        firstNode.next = node;
        node.pre = firstNode;
        node.next.pre = node;
    }

    private final void removeToHead(LRUNode node) {
        removeNode(node.key);
        putToHead(node);
    }

    private final void removeLast() {
        Object key=lastNode.pre.key;
        removeNode(lastNode.pre.key);
        cacheMap.remove(key);
    }

    private final void removeNode(Integer key) {
        LRUNode node = cacheMap.get(key);
        if (node == null) {
            return;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void printList() {
        LRUNode node = firstNode.next;
        System.out.print("***");
        while (node != lastNode) {
            System.out.print(node.key + ":" + node.value + " , ");
            node = node.next;
        }
        System.out.println(" --- ");
    }
}
