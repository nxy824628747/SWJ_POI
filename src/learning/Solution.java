package learning;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(reverseList(head).val);
//        reverseList(head);
//        System.out.print(node5.val);
//        System.out.print(node5.next.val);
//        System.out.print(node4.next.val);
//        System.out.print(node3.next.val);
//        System.out.print(node2.next.val);
//        System.out.print(node1.next.val);
//        System.out.print(head.next.val);
//        print(node5);
    }

    public static void print(ListNode node) {
        if (node.next == null) {
            return;
        }
        while (node.next != null) {
            System.out.print(node.next.val);
            print(node.next);
            List<Integer> l = new LinkedList<>();

        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        return p;
    }
}
