class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int profit = 0;
        int buy = prices[0];

        for(int i=0; i<n; i++){
            buy = Math.min(buy, prices[i]);
            int currProfit = prices[i] - buy;
            profit = Math.max(profit, currProfit);
        }

        return profit;
    }
}
