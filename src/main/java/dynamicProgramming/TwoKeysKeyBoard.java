package dynamicProgramming;

public class TwoKeysKeyBoard {

    /**
     * the idea is if i is even(i%2), go to the i/2 position and add 2 (1- for copy and 1- for paste)
     * if i is odd, we need to check what other odd is factor of i, initialise j and iterate till sqrt(i)
     * if we find i%j==0 then we go to i%j position and add 1-for copy and j-1 for paste
     * for e.x if i=9 we find j=3 (i%3==0), we go to i/j (3rd position which has 'AAA') and copy and paste it 2 times
     * i=3(AAA), copy and paste 2 times (AAA-AAA-AAA) we get 9 items
     *
     */
    public static int minSteps(int n) {
        int[] dp = new int[n+1];
        dp[1]=0;
        for(int i=2;i<=n;i++){
            dp[i] = i; // this is for prime numbers
            if(i%2==0){
                dp[i] = dp[i/2]+1+1; // 1 for copy + 1 for paste
            }else{
                for(int j=2;j<=Math.sqrt(i);j++){
                    if(i%j==0){
                        dp[i] = Math.min(dp[i], dp[i/j] + 1 + j-1); //1 for copy and (j-1) for paste
                    }
                }
            }

        }
        return dp[n];
    }

    int[] dp = new int[1001];
    public int minStepRecurs(int n) {
        if(n==1) return 0;
        if(dp[n]!=0) return dp[n];
        int min=n;
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0){
                min = Math.min(min, minStepRecurs(n/i) + 1 + i-1); //1 for copy and (i-1) for paste
            }
        }
        dp[n] = min;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(minSteps(9));
    }
}
