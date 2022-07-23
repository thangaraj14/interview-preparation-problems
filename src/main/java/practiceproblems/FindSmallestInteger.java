package practiceproblems;

/**
 * https://www.geeksforgeeks.org/find-smallest-value-represented-sum-subset-given-array/
 * Given a sorted array (sorted in non-decreasing order) of positive numbers,
 * find the smallest positive integer value
 * that cannot be represented as sum of elements of any subset of given set.
 * Expected time complexity is O(n).
 *
 * */
class FindSmallestInteger {

    int findSmallest(int a[], int n) {
         int maxPossible = 0;

        if(a.length == 0 || a[0] != 1)
        {
            return maxPossible + 1;
        }

        // we have verified that 1 exists in the array.
        maxPossible = 1;

        for(int i=1; i<a.length; i++)
        {
            // if the current element is greater than (maxPossible + 1)
            // that leaves a gap at: maxPossible + 1
            if(a[i] > maxPossible + 1)
            {
                break;
            }

            maxPossible += a[i];
        }

        return maxPossible + 1;
    }

    public static void main(String[] args) {
        FindSmallestInteger small = new FindSmallestInteger();
        int arr1[] = { 1, 3, 4, 5 };
        int n1 = arr1.length;
        System.out.println("FINAL RESULT: "+small.findSmallest(arr1, n1));

        int arr2[] = { 1, 2, 6, 10, 11, 15 };
        int n2 = arr2.length;
        System.out.println("FINAL RESULT: "+small.findSmallest(arr2, n2));

        int arr3[] = { 1, 1, 1, 1 };
        int n3 = arr3.length;
        System.out.println("FINAL RESULT: "+small.findSmallest(arr3, n3));

        int arr4[] = { 1, 1, 3, 4 };
        int n4 = arr4.length;
        System.out.println("FINAL RESULT: "+small.findSmallest(arr4, n4));

    }
}