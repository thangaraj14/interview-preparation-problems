package microsoftassesment;

import java.util.*;

class Pair{
    String value;
    int digitSum;

    public Pair(String value, int digitSum) {
        this.value = value;
        this.digitSum = digitSum;
    }
}

public class NumsWithEqualDigitSum {

    // given an array of integers, return max sum of 2 values whose digits adds up to same value
    // [51,71,17,42] => 93 (51+42)=>(5+1==4+2)
    // [42,33,60] => 102 (42+60)=> (4+2== 6+0)

    public int findMaxSumWithEqualDigits(int[] nums){
        if(nums==null || nums.length==0) return 0;
        List<Pair> inputs= new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int sum=0;
            int val=nums[i];
            while(val>0){
                sum+=val%10;
                val/=10;
            }
            inputs.add(new Pair(String.valueOf(nums[i]),sum));
        }
        PriorityQueue<Pair> queue= new PriorityQueue<>((a,b)->Integer.compare(a.digitSum,b.digitSum));

        int result=0;
       queue.addAll(inputs);
       while (!queue.isEmpty()){
           Pair first=queue.poll();
           if(queue.isEmpty()) break;
           Pair second= queue.peek();
         if(first.digitSum==second.digitSum){
             second=queue.poll();
             result= Math.max(result, Integer.parseInt(first.value)+Integer.parseInt(second.value));
             queue.offer(new Pair(String.valueOf(Math.max(Integer.parseInt(first.value),Integer.parseInt(second.value))),first.digitSum));
         }
       }
        return result;
    }

    public static void main(String[] args) {
       System.out.println(new NumsWithEqualDigitSum().findMaxSumWithEqualDigits(new int[]{51,71,17,42}));
       System.out.println(new NumsWithEqualDigitSum().findMaxSumWithEqualDigits(new int[]{42,33,60}));
        System.out.println(new NumsWithEqualDigitSum().findMaxSumWithEqualDigits( new int[] {2053, 280, 780, 505, 690, 730, 4730, 951, 8331, 5079, 7252, 3675, 8969, 6904, 1194}));
        System.out.println(new NumsWithEqualDigitSum().findMaxSumWithEqualDigits( new int[] {2053, 280, 780, 505, 690, 730, 4730, 951, 8331, 5079, 7252, 3675, 8969, 6904, 1194}));
        //[2053, 280, 780, 505, 690, 730, 4730, 951, 8331, 5079, 7252, 3675, 8969, 6904, 1194]

    }


    private int computeDigitSum(int a){
        // supposed to be valid for negative numbers and the output must be non-negative integer.
        a = Math.abs(a);
        int res = 0;
        while(a > 0){
            res += a % 10;
            a /= 10;
        }
        return res;
    }
    public int maxSum(int[] A){
        int N = A.length;
        if(N <= 1) return -1;
        Map<Integer, Integer> map = new HashMap<>();
        int res = -1;
        for(int i = 0; i < N; ++i){
            int digitsum = computeDigitSum(A[i]);
            if(!map.containsKey(digitsum)){
                map.put(digitsum, A[i]);
            }
            else{
                res = Math.max(res, map.get(digitsum) + A[i]);
                map.put(digitsum, Math.max(A[i], map.get(digitsum)));
            }
        }
        return res;
    }
}
