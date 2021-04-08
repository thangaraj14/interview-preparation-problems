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

    public int next(int n)
    {
        int res=0;
        while (n>0)
        {
            int t = n % 10;
            res += t*t;
            n/=10;
        }
        return res;
    }
    
   public boolean isHappyOpt(int n)
    {
        int i1=n, i2=next(n);
        
        while ( i2 != i1)
        {
            System.out.println("i1: "+ i1+" i2: "+ i2);
            i1 = next(i1);
            i2 = next(next(i2));
        }
        
        return i1==1;
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        System.out.println(hn.isHappyOpt(19));
    }
}