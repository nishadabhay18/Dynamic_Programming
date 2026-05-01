class bestTimeToBuyAndSellStocks2 {
    // RECURSION TC->O(2^n) SC->O(n)
    public int maxProfit(int[] prices) {
        return helper(prices, 0, true);
    }
    public int helper(int[] prices, int idx, boolean buy){
        int n = prices.length;
        if(idx == n) return 0;
        int profit = 0;
        if(buy) profit = Math.max(-prices[idx] + helper(prices, idx+1, false), 0+helper(prices, idx+1, true));
        else profit = Math.max(prices[idx] + helper(prices, idx+1, true), 0 + helper(prices, idx+1, false));
        return profit;
    }

    // RECURSION+MEMOIZATION TC->O(n*2) SC->O(n*2)+O(n)
    public int maxProfit(int[] prices) {
        int n = prices.length;
        Integer[][] dp = new Integer[n][2];
        return helper(prices, 0, 1, dp);
    }
    // buy->1 sell->0
    public int helper(int[] prices, int idx, int buy, Integer[][] dp){
        int n = prices.length;
        if(idx == n) return 0;
        int profit = 0;
        if(dp[idx][buy] != null) return dp[idx][buy];
        if(buy == 1) profit = Math.max(-prices[idx] + helper(prices, idx+1, 0, dp), 0 + helper(prices, idx+1, 1, dp));
        else profit = Math.max(prices[idx] + helper(prices, idx+1, 1, dp), 0 + helper(prices, idx+1, 0, dp));
        return dp[idx][buy] = profit;
    }

    // TABULATION TC->O(n*2) SC->O(n*2)
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        dp[n][0] = dp[n][1] = 0;
        // buy->1 sell->0
        for(int idx = n-1; idx >= 0; idx--){
            for(int buy = 0; buy <= 1; buy++){
                int profit = 0;
                if(buy == 1) profit = Math.max(-prices[idx] + dp[idx+1][0], 0+dp[idx+1][1]);
                else profit = Math.max(prices[idx] + dp[idx+1][1], 0 + dp[idx+1][0]);
                dp[idx][buy] = profit;
            }
        }
        return dp[0][1];
    }

    // SPACE OPTIMIZATION TC->O(n*2) SC->O(1)
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] ahead = new int[2];
        int[] curr = new int[2];
        ahead[0] = ahead[1] = 0;
        // buy->1 sell->0
        for(int idx = n-1; idx >= 0; idx--){
            for(int buy = 0; buy <= 1; buy++){
                int profit = 0;
                if(buy == 1) profit = Math.max(-prices[idx] + ahead[0], 0 + ahead[1]);
                else profit = Math.max(prices[idx] + ahead[1], 0 + ahead[0]);
                curr[buy] = profit;
            }
            ahead = curr.clone();
        }
        return ahead[1];
    }
}