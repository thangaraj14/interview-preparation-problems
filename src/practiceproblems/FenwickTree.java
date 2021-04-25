package practiceproblems;

/**
 * https://codeforces.com/blog/entry/61364
 * 
 * Fenwick trees are binary indexed trees, when asked for a range queries for an array
 * ex [1,3,4,1,2] print the sum for range (0,4), (1,2) etc.. sum, difference, product, division can be asked
 * one way is to pre-compute the prefix sums like [1,4,8,9,11]
 * when asked for sum between 0 to 4 return 9
 * when asked sum between 2 to 4 we can return sum of (0,1) - (0,4)
 * the retrival is O(1), but if the original array is updated we need to compute
 * the prefix sum which will take O(N).
 * Fenwick tree solves this by making both get and update as O(log n)
 */
public class FenwickTree{

    // the array size is always incremented by 1, because the first entry is
    // a dummy entry, the trees are arranges in set bits of each array's index
    // we consider 4 pos of the bit representation
    /**
     * for array [1,-7,15,9,4,2,0,10]
     * 0=>               [0/0000]                       (index/ bit val of index)
     *         /        |        \            \
     * 1=>  [1/0001]   [2/0010]   [4/0100]    [8/1000]
     *              /             /     \ 
     * 2=>      [3/0011]      [5/0101]  [6/0110]  
     *                                    /
     * 3=>                              [7/0111]
     * 
     * so from the above tree we can infer that to get to a parent node, we need to
     * remove the right most set bit
     * [7/0111] => [6/0110]=> [4/0100]=> [0/0000]
     *  and the formula for that is parent = i - (i & -i);
     * 
     * before populating fenwick tree with values, calculate the prefix sum for the array
     * [1,-7,15,9,4,2,0,10]=> [1,-6,9,18,22,24,24,34] and update in it's posistion in fenwick tree
     * 
     *               [0/0000]                     
     *         /        |        \            \
     *   [1/0001]   [-6/0010]   [18/0100]    [34/1000]
     *              /             /     \ 
     *       [9/0011]      [22/0101]  [24/0110]  
     *                                    /
     *                               [24/0111]
     * the we need to subract each cell's value to it's immediate parent's value, then the tree would look like
     * we can use the above formula parent = i - (i & -i);
     * 
     *               [0/0000]                     
     *         /        |        \            \
     *   [1/0001]   [-6/0010]   [18/0100]    [34/1000]
     *              /             /     \ 
     *       [15/0011]      [4/0101]  [6/0110]  
     *                                    /
     *                               [0/0111]
     * in array's representation it'd look like
     * [0,1,-6,15,18,4,6,0,34]
     */

    int[] fen; int[] arr;
    int n;
    public void NumArray(int[] nums) {
        arr = nums;
        n = nums.length;
        fen = new int[n+1];
        init();
    }

    public void init(){
        if(n == 0) return;
        
        fen[1] = arr[0];
        for(int i = 1; i < n; i++){
            fen[i+1] = fen[i] + arr[i];
        }  
        for(int i = n; i >0 ; i--){
            int parent = i - (i & -i);
            if(parent >= 0) fen[i] -= fen[parent];
        }
        //System.out.println(Arrays.toString(fen));
    }



    // for update we need to come from parent to children
    // to get children do opposite of i-(i&-i)
    public void update(int i, int val) {
        int extra = val - arr[i];
        arr[i] = val;
        increment(i, extra);
    }
    
     public int sum(int i){
        int res = 0;
        while(i > 0){
            res += fen[i];
            i = i - (i & -i);
        }
        return res;
    }
    public void increment(int i, int val){
        i++;
        while(i <= n){
            fen[i] += val;
            i = i + (i & -i);
        }
    }
    //sum(1,4)=> sum(0,4)-(0,1) the sum function will calculate from 4->0(parent)
    public int sumRange(int i, int j) {
        return sum(j+1) - sum(i);
    }

}