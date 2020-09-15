package linkedLists;

import java.util.Collections;
import java.util.PriorityQueue;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if(k==0 || head==null) return head;

        ListNode tempHead= head;
        int length=1;
        while(tempHead.next!=null){
            length++;
            tempHead=tempHead.next;
        }
        tempHead.next=head;
        // if(k%length==0) return head;
        k%=length;
        for(int i=0;i<length-k;i++){
            tempHead=tempHead.next;
            head=head.next;
        }
        tempHead.next=null;
        return head;
    }
}

 class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        MedianQueue medianHeap= new MedianQueue();

        double[] result= new double[nums.length-k+1];
        int i=0;
        int resultIndex=0;
        for(;i<k;i++){
            medianHeap.offer(nums[i]);
        }
        result[resultIndex++]=medianHeap.median();

        for(;i<nums.length-k+1;i++){
            medianHeap.offer(nums[i]);
            if(medianHeap.size()==k){
                result[resultIndex++]=medianHeap.median();
                medianHeap.remove(nums[nums.length-k]);
            }

        }

        return result;
    }

    static class MedianQueue{
        PriorityQueue<Integer> minQueue= new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue= new PriorityQueue<>(Collections.reverseOrder());

        public void offer(int x){
            maxQueue.offer(x);
            minQueue.offer(maxQueue.poll());
            if(maxQueue.size()<minQueue.size()){
                maxQueue.offer(minQueue.poll());
            }
        }

        public double median(){
            if(minQueue.size()==maxQueue.size()){
                return ((double)(maxQueue.peek()+minQueue.peek()/2));
            }
            return maxQueue.peek();
        }
       public int size(){
            return minQueue.size() + maxQueue.size();
        }

        public boolean remove(int x){
            return minQueue.remove(x) || maxQueue.remove(x);
        }
    }
}
