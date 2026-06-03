class minimumFallingPathSum2 {
    // RECURSION
    // Time: O(n^m)   (Exponential, very slow)
    // Space: O(m)    (recursion stack)
    public int minFallingPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<m; i++){
            ans = Math.min(ans,helper(grid, 0, 0, m-1, n-1));
        }
        return ans;
    }
    public int helper(int[][] grid, int sr, int sc, int er, int ec){
        int m = grid.length, n = grid[0].length;
        if(sc<0 || sc>=n) return Integer.MAX_VALUE;
        if(sr == er) return grid[sr][sc];
        int ldown = helper(grid, sr+1, sc-1, er, ec);
        int rdown = helper(grid, sr+1, sc+1, er, ec);
        return grid[sr][sc] + Math.min(ldown, rdown);
    }

    // RECURSION+MEMOIZATION
    // Time: O(m * n * n) = O(m n²)
    // Space: O(m * n) + O(m recursion stack)
    public int minFallingPathSum(int[][] grid){
        int m=grid.length, n=grid[0].length;
        Integer[][] dp=new Integer[m][n];
        int ans=Integer.MAX_VALUE;
        for(int j=0;j<n;j++){
            ans=Math.min(ans,helper(grid,0,j,m-1,n-1,dp));
        }
        return ans;
    }
    public int helper(int[][] grid, int sr, int sc, int er, int ec, Integer[][] dp){
        int m = grid.length, n = grid[0].length;
        if(sc<0 || sc>=n) return Integer.MAX_VALUE;
        if(sr == m-1) return grid[sr][sc];
        if(dp[sr][sc] != null) return dp[sr][sc];
        int sum = grid[sr][sc];
        int x = Integer.MAX_VALUE;
        for(int j = 0; j<n; j++){
            if(j != sc){
                int temp = helper(grid, sr+1, j, er, ec, dp);
                x = Math.min(x, temp);
            }
        }
        sum += x;
        return dp[sr][sc] = sum;
    }

    // TABUALTION
    // Time: O(m * n * n) = O(m n²)
    // Space: O(m * n)
    public int minFallingPathSum(int[][] grid){
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int j = 0; j<n; j++) dp[0][j] = grid[0][j];
        for(int i=1 ; i<m; i++){
            for(int j = 0; j<n; j++){
                int min = Integer.MAX_VALUE;
                // check all columns except j
                for (int k = 0; k < n; k++) {
                    if (k != j) min = Math.min(min, dp[i - 1][k]);
                }
                dp[i][j] = grid[i][j] + min;
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int j = 0; j<n; j++){
            ans = Math.min(ans, dp[m-1][j]);
        }
        return ans;
    }
}