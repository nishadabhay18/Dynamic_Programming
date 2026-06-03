class wildcardMatching {
    // RECURSION TC-> O(2^(m+n)) SC-> O(m+n)
     public boolean isMatch(String s, String p) {
         int m = s.length(), n = p.length();
         return helper(s, p, m-1, n-1);
     }
     public boolean helper(String s, String p, int idx1, int idx2){
         int m = s.length(), n = p.length();
         if(idx1 < 0 && idx2 < 0) return true;
         if(idx1 >= 0 && idx2 < 0) return false;
         if(idx1 < 0 && idx2 >= 0){
             for(int i = 0; i <= idx2; i++){
                 if(p.charAt(i) != '*') return false;
             }
             return true;
         }
         if(p.charAt(idx2) == '?' || s.charAt(idx1) == p.charAt(idx2)) return helper(s, p, idx1-1, idx2-1);
         if(p.charAt(idx2) == '*') return helper(s, p, idx1-1, idx2) || helper(s, p, idx1, idx2-1);
         return false;
     }

    // RECURSION + MEMOIZATION TC-> O(m*n) SC-> O(m*n)+O(m+n)
     public boolean isMatch(String s, String p) {
         int m = s.length(), n = p.length();
         Boolean[][] dp = new Boolean[m][n];
         return helper(s, p, m-1, n-1, dp);
     }
     public boolean helper(String s, String p, int idx1, int idx2, Boolean[][] dp){
         int m = s.length(), n = p.length();
         if(idx1 < 0 && idx2 < 0) return true;
         if(idx1 >= 0 && idx2 < 0) return false;
         if(idx1 < 0 && idx2 >= 0){
             for(int i = 0; i <= idx2; i++){
                 if(p.charAt(i) != '*') return false;
             }
             return true;
         }
         if(dp[idx1][idx2] != null) return dp[idx1][idx2];
         if(p.charAt(idx2) == '?' || s.charAt(idx1) == p.charAt(idx2)) return dp[idx1][idx2] = helper(s, p, idx1-1, idx2-1, dp);
         if(p.charAt(idx2) == '*') return dp[idx1][idx2] = helper(s, p, idx1-1, idx2, dp) || helper(s, p, idx1, idx2-1, dp);
         return dp[idx1][idx2] = false;
     }

    // TABULATION TC-> O(m*n) SC-> O(m*n)
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        Boolean[][] dp = new Boolean[m+1][n+1];
        dp[0][0] = true;
        // first column
        for(int i = 1; i<=m; i++) dp[i][0] = false;
        // first row
        for(int j = 1; j<=n; j++){
            boolean flag = true;
            for(int i = 1; i<=j; i++){
                if(p.charAt(i-1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[0][j] = flag;
        }
        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(p.charAt(j-1) == '?' || s.charAt(i-1) == p.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else if(p.charAt(j-1) == '*') dp[i][j] = dp[i-1][j] || dp[i][j-1];
                else dp[i][j] = false;
            }
        }
        return dp[m][n];
    }
}