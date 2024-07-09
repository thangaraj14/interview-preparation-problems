package cess.sort_search;

import java.util.Arrays;

public class Apartments {

    public static int search(int[] memberReq, int[] apartments, int diff) {
        Arrays.sort(apartments);
        Arrays.sort(memberReq);
        int i = 0;
        int j = 0;
        int ans = 0;
        for (; i < memberReq.length; i++) {
            // if the curr space is less, then try finding a suitable apartment for applicant i
            while (j < apartments.length && apartments[j] < memberReq[i] - diff) {
                j++;
            }
            if (Math.abs(apartments[j] - memberReq[i]) <= diff) {
                ans++;
                i++;
                j++;
            } else {
                // if it's large then move to next applicant
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        search(new int[]{60,45,80,60},new int[]{30,60,75}, 5);
    }
}
