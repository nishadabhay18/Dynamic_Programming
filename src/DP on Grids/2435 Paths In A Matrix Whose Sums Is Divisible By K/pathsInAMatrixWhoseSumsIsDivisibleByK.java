class pathsInAMatrixWhoseSumsIsDivisibleByK {
    // RECURSION TC->O(2^(m+n)) SC-> O(m+n)
     public int numberOfPaths(int[][] grid, int k) {
         int m = grid.length, n = grid[0].length;
         return findPaths(grid, k, 0, 0, 0);
     }
     public int findPaths(int[][] grid, int k, int row,int col,int sum){
         int m = grid.length, n = grid[0].length;
         if(row >= m || col >= n) return 0;
         sum += grid[row][col];
         if(row == m-1 && col == n-1){
             // if(sum%k==0) count++;
             return (sum % k == 0) ? 1 : 0;
         }
         int down = findPaths(grid, k, row+1, col, sum);
         int right = findPaths(grid, k, row, col+1, sum);
         return down + right;
     }

    // RECURSION+MEMOIZATION TC->O(m*n*k) SC-> O(m*n*k)+O(m+n)
    public static int mod = 1000000007;
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m][n][k];
        for(int i = 0; i<m; i++)
            for(int j = 0; j<n; j++) Arrays.fill(dp[i][j], -1);
        return findPaths(grid, k, 0, 0, 0, dp);
    }
    public int findPaths(int[][] grid, int k, int row,int col,int sum, int[][][] dp){
        int m = grid.length, n = grid[0].length;
        if(row >= m || col >= n) return 0;
        sum += grid[row][col];
        sum % = k;
        if(row == m-1 && col == n-1) return (sum % k == 0) ? 1 : 0;
        if(dp[row][col][sum] != -1) return dp[row][col][sum];
        int down = findPaths(grid, k, row+1, col, sum, dp);
        int right = findPaths(grid, k, row, col+1, sum, dp);
        return dp[row][col][sum] = (down + right) % mod;
    }
}
