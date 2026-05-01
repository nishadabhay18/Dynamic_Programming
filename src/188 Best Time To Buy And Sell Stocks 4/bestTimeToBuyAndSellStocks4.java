class bestTimeToBuyAndSellStocks4 {
    // RECURSION TC->O(2^n) SC->O(n)
    public int maxProfit(int k, int[] prices) {
        return helper(prices, 0, true, k);
    }
    public int helper(int[] prices, int idx, boolean buy, int cap){
        int n = prices.length;
        if(idx == n || cap == 0) return 0;
        int profit = 0;
        if(buy) profit = Math.max(-prices[idx] + helper(prices, idx+1, false, cap), 0 + helper(prices, idx+1, true, cap));
        else profit = Math.max(prices[idx] + helper(prices, idx+1, true, cap-1), 0 + helper(prices, idx+1, false, cap));
        return profit;
    }

    // RECURSION+MEMOIZATION TC->O(n*2*k) SC->O(n*2*k)+O(n)
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        Integer[][][] dp = new Integer[n][2][k+1]; // cap-> k to 0
        return helper(prices, 0, 1, k, dp);
    }
    // buy->1 sell->0
    public int helper(int[] prices, int idx, int buy, int cap, Integer[][][] dp){
        int n = prices.length;
        if(idx == n || cap == 0) return 0;
        int profit = 0;
        if(dp[idx][buy][cap] != null) return dp[idx][buy][cap];
        if(buy == 1) profit = Math.max(-prices[idx] + helper(prices, idx+1, 0, cap, dp), 0 + helper(prices, idx+1, 1, cap, dp));
        else profit = Math.max(prices[idx] + helper(prices, idx+1, 1, cap-1, dp), 0 + helper(prices, idx+1, 0, cap, dp));
        return dp[idx][buy][cap] = profit;
    }

    // TABULATION TC->O(n*2*k) SC->O(n*2*k)
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n+1][2][k+1]; // cap-> k to 0
        for(int idx = n-1; idx >= 0; idx--){
            for(int buy = 0; buy <= 1; buy++){
                for(int cap = 1; cap <= k; cap++){
                    int profit = 0;
                    if(buy == 1) profit = Math.max(-prices[idx] + dp[idx+1][0][cap], 0 + dp[idx+1][1][cap]);
                    else profit = Math.max(prices[idx] + dp[idx+1][1][cap-1], 0 + dp[idx+1][0][cap]);
                    dp[idx][buy][cap] = profit;
                }
            }
        }
        return dp[0][1][k];
    }

    // SPACE OPTIMIZATION TC->O(n*2*3) SC->O(1)
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] ahead = new int[2][k+1];
        int[][] curr = new int[2][k+1];
        for(int idx = n-1; idx >= 0; idx--){
            for(int buy = 0; buy <= 1; buy++){
                for(int cap = 1; cap <= k; cap++){
                    int profit = 0;
                    if(buy == 1) profit = Math.max(-prices[idx] + ahead[0][cap], 0 + ahead[1][cap]);
                    else profit = Math.max(prices[idx] + ahead[1][cap-1], 0 + ahead[0][cap]);
                    curr[buy][cap] = profit;
                }
            }
            ahead = curr.clone();
        }
        return ahead[1][k];
    }
}
