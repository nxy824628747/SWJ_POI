package learning;

import com.timevale.tgtext.text.List;

public class Leetcode243 {

    /**
     * @Author Niuxy
     * @Date 2020/6/23 8:22 下午
     * @Description 快慢指针翻转链表
     */
    public final boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        //快慢指针找中点
        while (fast != null && fast.next != null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode seHead=revoleList(slow.next);
        slow.next=null;
        ListNode fiHead=head;
        while(fiHead!=null&&seHead!=null){
            if(fiHead.val!=seHead.val){
                return false;
            }
            fiHead=fiHead.next;
            seHead=seHead.next;
        }
        return true;
    }

    //翻转链表
    private static ListNode revoleList(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode next = head.next.next;
        ListNode node = head.next;
        ListNode pre = head;
        head.next = null;
        while (node != null) {
            node.next = pre;
            if (next == null) {
                return node;
            }
            pre = node;
            node = next;
            next = next.next;
        }
        return node;
    }

    public static void main(String[] args) {
        int[] arr = {1};
        ListNode head = ListNode.fromSums(arr);
        head = revoleList(head);
        printNodes(head);
    }

    private static void printNodes(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " , ");
            head = head.next;
        }
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/23 8:22 下午
     * @Description 双指针
     */
    public final boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode temp = head;
        //链表长度
        int length = 1;
        while (temp.next != null) {
            temp = temp.next;
            length++;
        }
        //双指针
        int before = 0;
        int after = length - 1;
        while (after > before) {
            if (getNode(head, before).val != getNode(head, after).val) {
                return false;
            }
            before++;
            after--;
        }
        return true;
    }

    private final ListNode getNode(ListNode head, int index) {
        while (index != 0) {
            head = head.next;
            index--;
        }
        return head;
    }

}
