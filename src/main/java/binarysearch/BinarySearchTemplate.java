package binarysearch;

public class BinarySearchTemplate {

    /**
     * while (left < right):
     * This condition is typically used when you want to find the insertion point or a specific boundary in a sorted array.
     * It's often used when you're not necessarily looking for an exact match,
     * but rather a position where an element should be inserted to maintain the sorted order.
     * while (left <= right):
     * This condition is used when you're searching for a specific element in the array
     * and want to return its index if found.
     * It allows the search to continue until the pointers cross each other, ensuring that every element is checked.

     * The key differences are:
     *
     * Termination condition:
     *
     * left < right will terminate when left == right
     * left <= right will terminate when left > right
     *
     * Final state:
     *
     * With left < right, left will be the insertion point or boundary you're looking for
     * With left <= right, you need to check if the element was found after the loop ends
     *
     * Use case:
     *
     * left < right is often used for problems like finding the first/last occurrence of an element, or finding an insertion point
     * left <= right is typically used when you're searching for a specific element and want to return its index
     *
     */

//    public <T> T template(int n) {
//
//        int left= min_val;
//        int right= max_val;
//
//        while(left<right){
//            int mid=  left+ (right-left)/2;
//            if(condition) right=mid;
//            else left=mid+1;
//        }
//
//        return left;
//    }

}
