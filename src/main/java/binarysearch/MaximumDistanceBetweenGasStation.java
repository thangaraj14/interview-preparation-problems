package binarysearch;

// https://leetcode.com/problems/minimize-max-distance-to-gas-station
// https://takeuforward.org/arrays/minimise-maximum-distance-between-gas-stations/
// extreme tricky hard problem

/**
 * You are given a sorted array ‘arr’ of length ‘n’, which contains positive integer positions of ‘n’ gas stations on the X-axis.
 * You are also given an integer ‘k’. You have to place 'k' new gas stations on the X-axis.
 * You can place them anywhere on the non-negative side of the X-axis, even on non-integer positions.
 * Let 'dist' be the maximum value of the distance between adjacent gas stations after adding k new gas stations.
 * Find the minimum value of ‘dist’.
 * <p>
 * Input Format:
 * N = 5, arr[] = {1,2,3,4,5}, k = 4
 * Result:
 * 0.5
 * Explanation:
 * One of the possible ways to place 4 gas stations is {1,1.5,2,2.5,3,3.5,4,4.5,5}.
 * Thus the maximum difference between adjacent gas stations is 0.5.
 * Hence, the value of ‘dist’ is 0.5. It can be shown that there is no possible way to add 4 gas stations in such a way that the value of ‘dist’ is lower than this.
 */
public class MaximumDistanceBetweenGasStation {

    public double minimiseMaxDistanceBruteForce(int[] stations, int k) {
        int n = stations.length;
        int[] howManyLeft = new int[n - 1];

        for (int gasStations = 1; gasStations <= k; gasStations++) {
            double maxSelection = -1;
            int maxIndex = -1;

            for (int i = 0; i < n - 1; i++) {
                double diff = (stations[i + 1] - stations[i]);
                double selectionLength = diff / (howManyLeft[i] + 1);
                if (selectionLength > maxSelection) {
                    maxSelection = selectionLength;
                    maxIndex = i;
                }
            }
            howManyLeft[maxIndex]++;
        }

        double maxDistance = -1;
        for (int i = 0; i < n - 1; i++) {
            double diff = (stations[i + 1] - stations[i]);
            double selectionLength = diff / (howManyLeft[i] + 1);
            maxDistance = Math.max(maxDistance, selectionLength);
        }

        return maxDistance;
    }

    public double minimiseMaxDistanceHeap(int[] stations, int k) {
        int n = stations.length;
        int[] howManyLeft = new int[n - 1];

        var priorityQueue = new java.util.PriorityQueue<double[]>((a, b) -> Double.compare(b[0], a[0]));

        for (int i = 0; i < n - 1; i++) {
            double diff = (stations[i + 1] - stations[i]);
            priorityQueue.add(new double[]{diff, i});
        }

        for (int gasStations = 1; gasStations <= k && !priorityQueue.isEmpty(); gasStations++) {
            double[] curr = priorityQueue.poll();
            int index = (int) curr[1];
            howManyLeft[index]++;
            double diff = stations[index + 1] - stations[index];
            double selectionLength = diff / (howManyLeft[index] + 1);
            priorityQueue.add(new double[]{selectionLength, index});
        }
        return priorityQueue.peek()[0];
    }

    //Minimum possible answer: We will get the minimum answer when we place all the gas stations in a single location.
    // Now, in this case, the maximum distance will be 0.
    //Maximum possible answer: We will not place stations before the first or after the last station rather we will place stations in between the existing stations.
    // So, the maximum possible answer is the maximum distance between two consecutive existing stations.
    public double minimiseMaxDistance(int[] stations, int k) {
        int n = stations.length; // size of the array
        double left = 0;
        double right = 0;

        for (int i = 0; i < n - 1; i++) {
            right = Math.max(right, stations[i + 1] - stations[i]);
        }

        double diff = 1e-6;

        //The condition 'while(low <= high)' inside the 'while' loop won't work for decimal answers, and using it might lead to a TLE error.
        // To avoid this, we can modify the condition to 'while(high - low > 10^(-6))'.
        // This means we will only check numbers up to the 6th decimal place.
        while (right - left < diff) {
            double mid = (left + right) / (2.0);
            int count = numberOfGasStationsRequired(stations, mid);
            //low = mid+1: We have used this operation to eliminate the left half.
            // But if we apply the same here, we might ignore several decimal numbers and possibly our actual answer.
            // So, we will use this: low = mid.
            if (count > k) {
                //If result > k: On satisfying this condition, we can conclude that the number ‘mid’ is smaller than our answer.
                // So, we will eliminate the left half and consider the right half
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private int numberOfGasStationsRequired(int[] arr, double mid) {
        int n = arr.length;
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            //For each section between i and i-1, we will do the following:
            //No. of stations = (arr[i]-arr[i-1]) / dist
            int numberInBetween = (int) ((arr[i] - arr[i - 1]) / mid);
            //Let's keep in mind a crucial edge case: if the section_length (arr[i] - arr[i-1]) is completely divisible by 'dist',
            // the actual number of stations required will be one less than what we calculate.
            //if (arr[i]-arr[i-1] == (No. of stations*dist): No. of stations -= 1.
            if ((arr[i] - arr[i - 1]) == (mid * numberInBetween)) {
                numberInBetween--;
            }
            cnt += numberInBetween;
        }
        return cnt;
    }
}
