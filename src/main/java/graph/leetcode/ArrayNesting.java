package graph.leetcode;

/**
 * TODO
 *
 * https://leetcode.com/problems/array-nesting/solution/
 *
 * Elements in the same set will form a cycle.
 * We just traverse elements x in nums:
 *      If x is not visited then we dfs(x) to find elements in the same cycle with node x.
 *      Update the ans if the current cycle has length greater than ans.
 * Check the following picture for more clearly.
 *
 * nums = [5,4,0,3,1,6,2]
 *
 * curr = 5, nums[curr] = 6
 * make edge between 5 and 6 in paper, change curr to 6
 *
 * curr = 6, nums[curr] = 2
 * make edge between 6 and 2, change curr to 2
 *
 * curr = 2, nums[curr] = 0
 * make edge between 2 and 0, change curr to 0
 *
 * curr = 0, nums[curr] = 5
 * make edge between 0 and 5, change curr to 5
 *
 * now as 5 is already visited hence component is complete
 * component is => (5, 6, 2, 0)
 *
 * Now when i = 1, curr = 4
 * component is => (1, 4)
 *
 * i = 2, this number is already used in a component
 * i = 3 already used
 * i = 4 already used...... and so on
 *
 * so we have two components
 * (5, 6, 2, 0) and (1, 4)
 *
 * The largest size component is (5, 6, 2, 0)
 * Hence out ans for this test case is 4.
 */
public class ArrayNesting {

    public int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int start = i;
                int count = 0;
                while (!visited[start]) {
                    count++;
                    visited[start] = true;
                    start = nums[start];
                }
                if (result < count) {
                    result = count;
                }
            }
        }
        return result;
    }

    public int arrayNestingDFS(int[] nums) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i]<0)
                continue;
            max = Math.max(max, calcLength(nums, i));
        }
        return max;
    }

    private int calcLength(int[] nums, int start) {

        if(start<0 || start==nums.length || nums[start]<0){
            return 0;
        }

        int nextValue = nums[start];
        nums[start] = Integer.MIN_VALUE;

        return 1 + calcLength(nums, nextValue);

    }
}
