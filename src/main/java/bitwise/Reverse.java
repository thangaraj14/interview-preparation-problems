package bitwise;

// https://leetcode.com/problems/reverse-bits/
public class Reverse {

    public int reverseBits(int n) {
        int ans =0;
        for(int i=0;i<=31;i++){
            int bit = n>>i & 1;
            ans|=(bit<<31-i);
        }
        return ans;
    }
}
