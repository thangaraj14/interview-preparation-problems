package binarysearch;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/number-of-flowers-in-full-bloom/">...</a>
 * <p>
 * one of the trick involved in computing rage queries over prefix sum is use the given query index to calculate the prefix-sum for ex
 * <p>
 * if queries are from index (1 , 5) value is 4 and from index (3,7) value is 6, then the prefix array would look like
 * [0,5,0,6,0,0,-5,0,0,-6,0,0] => when prefix sum is calculated then the array would get transformed to [0,5,5,11,11,11,6,6,6,0,0,0]
 * the advantage is update query run time is O(1) as we are touching the specific index at that point, when get query comes we have to traverse the array and it's complexity is O(N)
 * <p>
 * we can improve the above array storage space by removing 0 entries with the help of sparse array(a simple hashmap to store index and value at that place)
 */
public class FlowerBloomSparseArray {
    // Blooming flowers = started flowers - ended flowers
    // Collect start bloom time point array, then sort it.
    //Collect end bloom time point array, then sort it.
    // For each time point t in persons:
    //
    //Binary search the upper bound of t in start, then we find the started flowers.
    //Binary search the lower bound of t in end, then we find the started flowers.
    //Blooming flowers = started flowers - ended flowers
    public static int[] fullBloomFlowersBinarySearch(int[][] flowers, int[] people) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();


        for (int[] flower: flowers) {
            starts.add(flower[0]);
            ends.add(flower[1] + 1); // Note that a flower = [start, end] stops blooming at end + 1, not end.
        }

        Collections.sort(starts);
        Collections.sort(ends);
        int[] ans = new int[people.length];

        for (int index = 0; index < people.length; index++) {
            int person = people[index];
            int i = binarySearch(starts, person);
            int j = binarySearch(ends, person);
            ans[index] = i - j;
        }

        return ans;
    }

    public static int binarySearch(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (target < arr.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }


    public static void main(String[] args) {
        fullBloomFlowersBinarySearch(new int[][]{{1, 6}, {3, 7}, {9, 12}, {4, 13}}, new int[]{2, 3, 7, 11});
    }

    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        int BLOOM = 0, WILT = 1, PERSON = 2;
        int[] arr = new int[persons.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int[] flower : flowers) {
            pq.offer(new int[]{flower[0], BLOOM});
            pq.offer(new int[]{flower[1], WILT});
        }
        for(int i = 0; i < persons.length; i++){
            pq.offer(new int[]{persons[i],PERSON,i});
        }
        int count = 0;
        while(!pq.isEmpty()){
            int[] temp = pq.poll();

            if(temp[1] == BLOOM)    count++;
            else if(temp[1] == WILT)   count--;
            else{
                arr[temp[2]] = count;
            }
        }
        return arr;
    }
}
