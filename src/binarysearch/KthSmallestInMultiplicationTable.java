package binarysearch;

/**
 * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/
 *
 * For Kth-Smallest problems like this, what comes to our mind first is Heap.
 * Usually we can maintain a Min-Heap and just pop the top of the Heap for k times.
 * However, that doesn't work out in this problem.
 * We don't have every single number in the entire Multiplication Table, instead, we only have the height and the length of the table.
 * If we are to apply Heap method, we need to explicitly calculate these m * n values and save them to a heap.
 * The time complexity and space complexity of this process are both O(mn), which is quite inefficient.
 * This is when binary search comes in. Remember we say that designing condition function is the most difficult part?
 * In order to find the k-th smallest value in the table, we can design an enough function,
 * given an input num, determine whether there're at least k values less than or equal to num.
 * The minimal num satisfying enough function is the answer we're looking for.
 * Recall that the key to binary search is discovering monotonicity.
 * In this problem, if num satisfies enough, then of course any value larger than num can satisfy.
 * This monotonicity is the fundament of our binary search algorithm.
 */
public class KthSmallestInMultiplicationTable {

    public int findKthNumber(int m, int n, int k) {
        int left=1;
        int right= m*n;

        while(left<right){
            int mid= left+(right-left)/2;
            System.out.println("isFeasible Begin with left:: "+left+ " right:: "+right+ " mid "+mid );
            if(isFeasible(m,n,k,mid)){
                right=mid;
            }else{
                left=mid+1;
            }
        }

        return left;
    }

    /**
     * The count function is to find how many numbers in the table are less than or equal to value v.
     * Since it is a multiplication table, and each number in the table is r*c,
     * we can find the amount of numbers row by row (or column by column).
     *
     * The Multiplication Table:
     *      1	2	3  4
     *      2	4	6  8
     *      3	6	9  12
     *      4   8   12  16
     * For the first row, r=1, the maximum possible c is v/1=v, or n.
     * Because c starts from 1, we can only have at most Math.min(v/1,n) values, which are less than or equal to v.
     * For the second row, r=2, the maximum c is v/2, or n. Similarly, we can only have at most Math.min(v/2,n) values.
     * For the i-th row, r=i, the maximum c is v/i, or n.
     *
     */
    public boolean isFeasible(int m, int n, int k, int mid){
        int count=0;
        for(int i=1; i<=m;i++){
            // this will give the number of values less that or equal to mid for each row
            // we add to the count to check for count>=k if yes adjust the right side
            int temp= Math.min(mid/i,n);
            System.out.println("I :: "+i+" N:: "+n+" MID/N ::"+temp);
            count+=temp;
        }
        System.out.println("isFeasible end:: "+count);
        System.out.println();
        return count>=k;
    }

    public static void main(String[] args) {
        System.out.println(new KthSmallestInMultiplicationTable().findKthNumber(4,4,6));
    }
}
