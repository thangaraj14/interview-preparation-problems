package geeksforgeeks;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/
 */
class HappyNumber {
    public boolean isHappy(int n) {

        Set<Integer> visited = new HashSet<>();
        int sum = String.valueOf(n).chars().map(Character::getNumericValue).map(val -> val * val).sum() == 1 ? 1 : n;
        while (true) {
            if (sum == 1) {
                return true;
            }
            sum = String.valueOf(sum).chars().map(Character::getNumericValue).map(val -> val * val).sum();
            if (visited.contains(sum)) {
                return false;
            }

            visited.add(sum);
        }
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        System.out.println(hn.isHappy(19));
    }
}