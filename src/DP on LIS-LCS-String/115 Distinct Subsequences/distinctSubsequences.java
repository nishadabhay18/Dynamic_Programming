class distinctSubsequences {
    // RECURSION TC-> O(2^m) SC-> O(m)
     public int numDistinct(String str1, String str2) {
         int m = str1.length(), n = str2.length();
         return helper(str1, str2, m-1, n-1);
     }
     public int helper(String str1, String str2, int idx1, int idx2){
         int m = str1.length(), n = str2.length();
         if(idx2 < 0) return 1;
         if(idx1 < 0) return 0;
         if(str1.charAt(idx1) == str2.charAt(idx2)) return helper(str1, str2, idx1-1, idx2-1) + helper(str1, str2, idx1-1, idx2);
         return helper(str1, str2, idx1-1, idx2);
     }

    // RECURSION+MEMOIZATION TC-> O(m*n) SC-> O(m*n)+O(m)
     public int numDistinct(String str1, String str2) {
         int m = str1.length(), n = str2.length();
         Integer[][] dp = new Integer[m][n];
         return helper(str1, str2, m-1, n-1, dp);
     }
     public int helper(String str1, String str2, int idx1, int idx2, Integer[][] dp){
         int m = str1.length(), n = str2.length();
         if(idx2 < 0) return 1;
         if(idx1 < 0) return 0;
         if(dp[idx1][idx2] != null) return dp[idx1][idx2];
         if(str1.charAt(idx1) == str2.charAt(idx2)) return dp[idx1][idx2] = helper(str1, str2, idx1-1, idx2-1, dp) + helper(str1, str2, idx1-1, idx2, dp);
         return dp[idx1][idx2] = helper(str1, str2, idx1-1, idx2, dp);
     }

    // TABULATION TC-> O(m*n) SC-> O(m*n)
     public int numDistinct(String str1, String str2) {
         int m = str1.length(), n = str2.length();
         int[][] dp = new int[m+1][n+1];
         for(int i = 0; i<=m; i++) dp[i][0] = 1;
         for(int i = 1; i<=m; i++){
             for(int j = 1; j<=n; j++){
                 if(str1.charAt(i-1) == str2.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                 else dp[i][j] = dp[i-1][j];
             }
         }
         return dp[m][n];
     }

    // SPACE OPTIMIZATION TC-> O(m*n) SC-> O(m)
    public int numDistinct(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[] prev = new int[n+1];
        int[] curr = new int[n+1];
        // for(int i=0;i<=m;i++) prev[0]=1;
        prev[0] = curr[0] = 1;
        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)) curr[j] = prev[j-1] + prev[j];
                else curr[j] = prev[j];
            }
            prev = curr.clone();
        }
        return prev[n];
    }
}