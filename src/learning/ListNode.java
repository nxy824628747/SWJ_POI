package learning;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
    public static ListNode fromSums(int[] nums){
        if(nums==null){return null;}
        int length=nums.length;
        ListNode temp=new ListNode(nums[0]);
        ListNode head=temp;
        for(int i=1;i<length;i++){
            temp.next=new ListNode(nums[i]);
            temp=temp.next;
        }
        return head;
    }
}
