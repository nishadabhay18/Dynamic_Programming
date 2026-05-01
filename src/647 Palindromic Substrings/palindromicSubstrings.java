class palindromicSubstrings {
    // Tabulation via thinking TC-> O(n*n) SC-> O(n*n)
    public int countSubstrings(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        int count = 0;
        for(int k = 0; k < n; k++){
            int i = 0, j = k;
            while(j < n){
                if(i == j){ // single length
                    dp[i][j] = 1;
                    count++;
                }
                else if(j == i+1){ // double length
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = 1;
                        count++;
                    }
                }
                else{ // more than double length
                    if(str.charAt(i) == str.charAt(j)){
                        if(dp[i+1][j-1] == 1){
                            dp[i][j] = 1;
                            count++;
                        }
                    }
                }
                i++;
                j++;
            }
        }
        return count;
    }
}