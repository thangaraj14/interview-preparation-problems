package binarysearch;

public class PivotInteger {

    public int pivotInteger(int n) {

        int total = (n * (n + 1)) / 2;

        int left = 0;
        int right = n;

        while (left < right) {
            int mid = (left + right) / 2;

            if ((mid * mid - total) < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        return left * left - total == 0 ? left : -1;

    }
}
