class shortestCommonSupersequence {
    // RECURSION + MEMOIZATION TC-> O(m*n) SC-> O(m*n)+O(m+n)
    // public String LCS(String text1, String text2) {
    //     int m=text1.length(), n=text2.length();
    // int[][] dp=new int[m+1][n+1];
    // for(int i=1;i<=m;i++){
    //     for(int j=1;j<=n;j++){
    //         if(text1.charAt(i-1)==text2.charAt(j-1)) dp[i][j]=1+dp[i-1][j-1];
    //         else dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
    //     }
    // }
    //     StringBuilder sb=new StringBuilder();
    //     int i=m, j=n;
    //     while(i>0 && j>0){
    //         if(text1.charAt(i-1)==text2.charAt(j-1)){
    //             sb.append(text1.charAt(i-1));
    //             i--; j--;
    //         }else if(dp[i-1][j]>dp[i][j-1]) i--;
    //         else j--;
    //     }
    //     return sb.reverse().toString();
    // }
    // public String shortestCommonSupersequence(String a, String b) {
    //     String lcs = LCS(a,b);
    //     int i=0, j=0, k=0;
    //     StringBuilder scs = new StringBuilder();
    //     while(i<a.length() && j<b.length() && k<lcs.length()){
    //         while(i<a.length() && a.charAt(i)!=lcs.charAt(k)){
    //             scs.append(a.charAt(i));
    //             i++;
    //         }
    //         while(j<b.length() && b.charAt(j)!=lcs.charAt(k)){
    //             scs.append(b.charAt(j));
    //             j++;
    //         }
    //         scs.append(lcs.charAt(k));
    //         i++; j++; k++;
    //     }
    //     // append leftovers
    //     while(i<a.length()){
    //         scs.append(a.charAt(i));
    //         i++;
    //     }
    //     while(j<b.length()){
    //         scs.append(b.charAt(j));
    //         j++;
    //     }
    //     return scs.toString();
    // }

    // TABUALTION TC-> O(m*n) SC-> O(m*n)
    public String shortestCommonSupersequence(String text1, String text2){
        int m=text1.length(), n=text2.length();
        int[][] dp=new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)) dp[i][j]=1+dp[i-1][j-1];
                else dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        StringBuilder sb=new StringBuilder();
        int i=m, j=n;
        while(i>0 && j>0){
            if(text1.charAt(i-1)==text2.charAt(j-1)){
                sb.append(text1.charAt(i-1));
                i--; j--;
            }else if(dp[i-1][j]>dp[i][j-1]){
                sb.append(text1.charAt(i-1));
                i--;
            }else{
                sb.append(text2.charAt(j-1));
                j--;
            }
        }
        while(i>0){
            sb.append(text1.charAt(i-1));
            i--;
        }
        while(j>0){
            sb.append(text2.charAt(j-1));
            j--;
        }
        return sb.reverse().toString();
    }
}