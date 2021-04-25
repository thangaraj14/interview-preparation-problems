package practiceproblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a sorted array arr, two integers k and x, find the k closest elements to x in the array.
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
 */
public class KClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // since this is sorted array we are making use of binary search to get index of X and 
        // fix the range between index-K to index+K and doing normal PQ solution
        int index= binarySearch(arr,x);
        //if(index==-1) return Collections.emptyList();
        
        int low= Math.max(0,index-k);
        int high= Math.min(index+k,arr.length-1);
        
        PriorityQueue<Entry> queue= new PriorityQueue<>((a, b)->Integer.compare(a.key,b.key));
        
        for(int i=low;i<=high;i++){
            // taking abs value because question is askin closest in both signs
            queue.offer(new Entry(Math.abs(arr[i]-x),i));
        }
        List<Integer> result= new ArrayList<>();
        int i=0;
        while(i<k){
            result.add(arr[queue.poll().value]);
            i++;
        }
        Collections.sort(result);
        return result;
    }

    public int binarySearch(int[] arr, int target){
        int start=0;
        int end= arr.length-1;

        while(start<=end){
            int mid= start + (end-start)/2;
            if(arr[mid]==target) return mid;
            else if(arr[mid]<target){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        if(start>0){
            start--;
        }
        return start;
    }
}

class Entry{
    int key;
    int value;
    public Entry(int key, int value){
        this.key=key;
        this.value=value;
    }
}