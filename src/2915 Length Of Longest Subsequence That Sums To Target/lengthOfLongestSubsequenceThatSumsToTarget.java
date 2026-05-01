class lengthOfLongestSubsequenceThatSumsToTarget {
    // RECURSION TC->O(2^n) SC-> O(n*target)+O(n)
     public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
         return longest(nums,target,0);
     }
     public int longest(List<Integer> nums,int target,int idx){
         if (target < 0) return Integer.MIN_VALUE;
         if(idx == nums.size()){
             if(target == 0) return 0;
             else return Integer.MIN_VALUE;
         }
         int skip = longest(nums,target,idx+1);
         if(target - nums.get(idx) < 0) return skip;
         int take = 1 + longest(nums, target - nums.get(idx), idx+1);
         return Math.max(take, skip);
     }

    // RECURSION+MEMOIZATION idx from n-1 to 0 TC->O(n*target) SC-> O(n*target)+O(n)
     public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
         int n = nums.size();
         Integer[][] dp = new Integer[n][target+1];
         int ans = longest(nums, target, n-1, dp);
         return ans < 0 ? -1 : ans;
     }
     public int longest(List<Integer> nums,int target,int idx,Integer[][] dp){
         if (target < 0) return Integer.MIN_VALUE;
         if(idx < 0){
             if(target == nums.get(idx)) return 0;
             else return Integer.MIN_VALUE;
         }
         if(dp[idx][target] != null) return dp[idx][target];
         int skip = longest(nums, target, idx-1, dp);
         if(target - nums.get(idx) < 0) return dp[idx][target] = skip;
         int take = 1 + longest(nums, target - nums.get(idx), idx-1, dp);
         return dp[idx][target] = Math.max(take, skip);
     }

    // RECURSION+MEMOIZATION idx from 0 to n-1 TC->O(n*target) SC-> O(n*target)+O(n)
     public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
         int n = nums.size();
         int[][] dp = new int[n][target+1];
         for(int i = 0; i<n; i++)
             for(int j = 0; j <= target; j++) dp[i][j] = -1;
         int ans = longest(nums, target, 0, dp);
         return ans < 0 ? -1 : ans;
     }
     public int longest(List<Integer> nums, int target, int idx, int[][] dp){
         int n = nums.size();
         if (target < 0) return Integer.MIN_VALUE;
         if(idx == n){
             if(target == 0) return 0;
             else return Integer.MIN_VALUE;
         }
         if(dp[idx][target] != -1) return dp[idx][target];
         int skip = longest(nums, target, idx+1, dp);
         if(target - nums.get(idx) < 0) return dp[idx][target] = skip;
         int take = 1 + longest(nums, target - nums.get(idx), idx+1, dp);
         return dp[idx][target] = Math.max(take, skip);
     }

    // TABULATION TC-> O(n*target) SC-> O(n*target)
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int n = nums.size();
        int[][] dp = new int[n+1][target+1];
        for(int j = 0; j <= target; j++){
            if(j == 0) dp[n][j] = 0; // optional
            else dp[n][j] = Integer.MIN_VALUE;
        }
        for(int i = n-1; i>=0; i--){
            for(int j = 0; j<=target; j++){
                int skip = dp[i+1][j];
                int take = Integer.MIN_VALUE;
                if(j >= nums.get(i) && dp[i + 1][j - nums.get(i)] != Integer.MIN_VALUE) {
                    take = 1 + dp[i + 1][j - nums.get(i)];
                }
                dp[i][j] = Math.max(take, skip);
            }
        }
        int ans = dp[0][target];
        return ans < 0 ? -1 : ans;
    }
}