class cherryPickup2 {
    // RECURSION + MEMOIZATION TC-> O(m*n*n) SC-> O(m*n*n)+O(m)
     public int cherryPickup(int[][] grid) {
         int m = grid.length, n = grid[0].length;
         Integer[][][] dp = new Integer[m][n][n];
         return helper(grid, 0, 0, n-1, m-1, n-1, dp);
     }
     public int helper(int[][] grid, int sr, int j1, int j2, int er, int ec, Integer[][][] dp){
         int m = grid.length, n = grid[0].length;
         if(j1<0 || j2<0 || j1>=n || j2>=n) return Integer.MIN_VALUE;
         if(sr == m-1){
             if(j1 == j2) return grid[sr][j1];
             return grid[sr][j1] + grid[sr][j2];
         }
         if(dp[sr][j1][j2] != null) return dp[sr][j1][j2];
         int maxCherryPick = Integer.MIN_VALUE;
         for(int dj1 = -1 ; dj1 <= 1; dj1++){
             for(int dj2 = -1; dj2 <= 1; dj2++){
                 int value = 0;
                 if(j1 == j2) value += grid[sr][j2] + helper(grid, sr+1, j1+dj1, j2+dj2, er, ec, dp);
                 else value += grid[sr][j1] + grid[sr][j2] + helper(grid, sr+1, j1+dj1, j2+dj2, er, ec, dp);
                 // value += helper(grid, sr+1, j1+dj1, j2+dj2, er, ec);
                 maxCherryPick = Math.max(maxCherryPick, value);
             }
         }
         return dp[sr][j1][j2] = maxCherryPick;
     }

    // TABULATION TC-> O(m*n*n) SC-> O(m*n*n)
    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m][n][n];
        // base case (last row)
        for (int j1 = 0; j1 < n; j1++) {
            for (int j2 = 0; j2 < n; j2++) {
                if (j1 == j2) dp[m-1][j1][j2] = grid[m-1][j1];
                else dp[m-1][j1][j2] = grid[m-1][j1] + grid[m-1][j2];
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < n; j1++) {
                for (int j2 = 0; j2 < n; j2++) {
                    int maxCherryPick = Integer.MIN_VALUE;
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int nj1 = j1 + dj1;
                            int nj2 = j2 + dj2;
                            if (nj1 >= 0 && nj1 < n && nj2 >= 0 && nj2 < n) {
                                int value;
                                if (j1 == j2) value = grid[i][j1] + dp[i+1][nj1][nj2];
                                else value = grid[i][j1] + grid[i][j2] + dp[i+1][nj1][nj2];
                                maxCherryPick = Math.max(maxCherryPick, value);
                            }
                        }
                    }
                    dp[i][j1][j2] = maxCherryPick;
                }
            }
        }
        return dp[0][0][n-1];
    }
}