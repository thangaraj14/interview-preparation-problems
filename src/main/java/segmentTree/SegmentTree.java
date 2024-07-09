package segmentTree;

// https://leetcode.com/problems/range-sum-query-mutable/
public class SegmentTree {
    private int[] tree;
    private int n;

    public SegmentTree(int[] nums) {
        n = nums.length;
        tree = new int[n * 2];
        buildTree(nums);
    }

    /**
     * Suppose we have the array nums = [1, 3, 5, 7, 9, 11].
     *
     * First, we determine n = 6 (the length of nums).
     * We create tree with size 2 * n = 12.
     *
     * After the first loop:
     * Copytree = [0, 0, 0, 0, 0, 0, 1, 3, 5, 7, 9, 11]
     *             0  1  2  3  4  5  6  7  8  9  10 11  (indices)
     *
     * Now, the second loop builds the rest of the tree:
     *
     * tree[5] = tree[10] + tree[11] = 9 + 11 = 20
     * tree[4] = tree[8] + tree[9] = 5 + 7 = 12
     * tree[3] = tree[6] + tree[7] = 1 + 3 = 4
     * tree[2] = tree[4] + tree[5] = 12 + 20 = 32
     * tree[1] = tree[2] + tree[3] = 32 + 4 = 36
     *
     * After the second loop:
     * Copytree = [0, 36, 32, 4, 12, 20, 1, 3, 5, 7, 9, 11]
     *             0   1   2  3   4   5  6  7  8  9  10 11  (indices)
     *
     *  since we are leaving zeroth index empty,
     *  the left and right nodes are calculated as 2*i and 2*i+1 else it would be 2*i+1 and 2*i+2
     * @param nums
     */
    private void buildTree(int[] nums) {
        //This copies the elements from nums into the second half of tree. Here, n is the size of the original array.
        for (int i = n, j = 0; i < 2 * n; i++, j++) {
            tree[i] = nums[j];
        }
        //This builds the rest of the tree from the bottom up. Each non-leaf node is the sum of its two children.
        for (int i = n - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    /**
     * array nums = [1, 3, 5, 7, 9, 11].
     * Initial tree:
     * [0, 36, 32, 4, 12, 20, 1, 3, 5, 7, 9, 11]
     *  0   1   2  3   4   5  6  7  8  9 10 11  (indices)
     *
     *  Suppose we want to update index 2 (third element in the original array) to value 8.
     *
     * treeIndex = 2 + 6 = 8 (as n = 6)
     * Update leaf: tree[8] = 8
     * Move up:
     * treeIndex = 8 / 2 = 4
     * tree[4] = tree[8] + tree[9] = 8 + 7 = 15
     *
     * Move up again:
     * treeIndex = 4 / 2 = 2
     * tree[2] = tree[4] + tree[5] = 15 + 20 = 35
     *
     * Final move:
     * treeIndex = 2 / 2 = 1
     * tree[1] = tree[2] + tree[3] = 35 + 4 = 39
     *
     * @param index
     * @param newValue
     */
    public void update(int index, int newValue) {
        // Convert to tree index
        int treeIndex = index + n; //We add n to the input index to convert it to the corresponding index in the tree array.

        // Update the leaf node
        tree[treeIndex] = newValue;

        // Update parent nodes
        while (treeIndex > 1) {
            treeIndex = treeIndex / 2;  // Move to parent
            int leftChild = 2 * treeIndex;
            int rightChild = 2 * treeIndex + 1;
            tree[treeIndex] = tree[leftChild] + tree[rightChild];
        }
    }

    /**
     * array nums = [1, 3, 5, 7, 9, 11].
     * Initial tree:
     * [0, 36, 32, 4, 12, 20, 1, 3, 5, 7, 9, 11]
     *  0   1   2  3   4   5  6  7  8  9 10 11  (indices)
     *
     *  Suppose we want to query the sum of the range [1, 4] (2nd to 5th elements in the original array).
     * Initialize: i = 1 + 6 = 7, j = 4 + 6 = 10, sum = 0
     *
     * First iteration:
     * i (7) is odd: sum += tree[7] = 3, i = 8
     * j (10) is even: sum += tree[10] = 9, j = 9
     * i = 8 / 2 = 4, j = 9 / 2 = 4
     *
     * Second iteration:
     * i == j == 4, so we add tree[4] = 15 to sum
     * i = 4 / 2 = 2, j = 4 / 2 = 2
     * Loop ends as i == j == 2
     * The final sum is 3 + 9 + 15 = 27, which is indeed the sum of [3, 5, 8, 7] in the original array.
     *
     * @param i
     * @param j
     * @return
     */
    public int sumRange(int i, int j) {
        // We add n to both i and j to convert them to tree indices.
        i += n;
        j += n;

        int sum = 0;

        while (i <= j) {
            if (i % 2 == 1) { //If i is odd, it's a right child. We include it in the sum and move to its right sibling.
                sum += tree[i];
                i++;  // Move to the j sibling
            }
            if (j % 2 == 0) { //If j is even, it's a left child. We include it in the sum and move to its left sibling.
                sum += tree[j];
                j--;  // Move to the i sibling
            }
            // Move up to the parent level
            i /= 2;
            j /= 2;
        }

        return sum;
    }


}
