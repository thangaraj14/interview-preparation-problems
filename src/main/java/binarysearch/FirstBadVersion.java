package binarysearch;

/**
 * https://leetcode.com/problems/first-bad-version/
 */
public class FirstBadVersion {

    public int firstBadVersion(int n) {
        if(n==0) return 0;
        int left=1;
        int right=n;

        while(left<right){
            int mid=  left+ (right-left)/2;
            if(isBadVersion(mid)) right=mid;
            else left=mid+1;
        }

        return left;
    }

    private boolean isBadVersion(int mid) {
        return true;
    }
}
