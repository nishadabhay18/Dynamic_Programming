class Solution {
    // RECURSION TC-> O(2^n) SC-> O(n) + O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        return solve(0, 0, triangle);
    }
    public int solve(int x, int y, List<List<Integer>> triangle){
        if(x == triangle.size()-1) return triangle.get(x).get(y);
        int rightBottom = solve(x+1, y+1,triangle);
        int bottom = solve(x+1, y,triangle);
        int val = Math.min(bottom, rightBottom);
        return val + triangle.get(x).get(y);
    }

    // RECURSION + MEMOIZATION TC-> O(n*n) SC-> O(n*n) + O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        Integer[][] dp = new Integer[m][m];
        return solve(0, 0, triangle, dp);
    }
    public int solve(int x, int y, List<List<Integer>> triangle, Integer[][] dp){
        if(x == triangle.size()-1) return triangle.get(x).get(y);
        if(dp[x][y] != null) return dp[x][y];
        int rightBottom = solve(x+1, y+1,triangle,dp);
        int bottom = solve(x+1, y,triangle,dp);
        int val = Math.min(bottom, rightBottom);
        return dp[x][y] = val + triangle.get(x).get(y);
    }

    // TABULATION TC-> O(n*n) SC-> O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int[] dp = new int[m];
        for(int i = 0; i<m; i++){
            dp[i] = triangle.get(m-1).get(i);
        }
        for(int i = m-2; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
