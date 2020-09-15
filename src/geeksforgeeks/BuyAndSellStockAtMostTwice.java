package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-twice/
 */
class BuyAndSellStockAtMostTwice {

	public static void main(String args[]) {
		int price[] = { 2, 30, 15, 10, 8, 25, 80 };
		int n = price.length;
		System.out.println("Maximum Profit = " + maxProfit(price));
	}

	/**
	 * the idea is when we find a profit which is from 
	 * i to n, we can break it in to i to k, k+1 to n
	 * in this manner at each point we can calculate profit from
	 * (left min element to current element) and (current element to right max element)
	 * the second part of the above eq can be acieved by coming from right to left
	 * for input          [3,3,5,0,0,3,1,4]
	 * 	profit from	l->r  [0,0,2,2,2,3,3,4]
	 *  profit from r->l  [4,4,4,4,4,3,3,0]
	 * this simply states that at index 2 if we come from left the profit is 2
	 * and we can initiate another transaction to obtain another profit
	 */
	public static int maxProfit(int[] prices) {
		int ans = 0;
        if (prices.length == 0 || prices.length == 1)
            return ans;
        int[] p = new int[prices.length];
        int minBuy = prices[0];
        int maxProfit = 0;
        for (int i=1;i<prices.length;i++) {
            minBuy = Math.min(minBuy, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minBuy);
            p[i] = maxProfit;
        }
        
        int maxSell = prices[prices.length-1];
        maxProfit = 0;
        for (int i=prices.length-2;i>=0;i--) {
            maxSell = Math.max(maxSell, prices[i]);
            maxProfit = Math.max(maxProfit, maxSell - prices[i]);
            p[i] += maxProfit;
            ans = Math.max(p[i], ans);
        }
        return ans;
	}

}
