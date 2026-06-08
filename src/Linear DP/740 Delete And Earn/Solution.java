class Solution {
    // TC-> O(2^m) SC-> O(m)
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        int max = 0;
        for(int i=0; i<n; i++) max = Math.max(max, nums[i]);
        int[] points = new int[max+1];
        for(int i=0; i<n; i++) points[nums[i]] += nums[i];
        return houseRob(points, 0);
    }
    public int houseRob(int[] points, int idx){
        int m = points.length;
        if(idx >= m) return 0;
        int skip = houseRob(points, idx+1);
        int take = points[idx] + houseRob(points, idx+2);
        return Math.max(take, skip);
    }

    // TC-> O(m) SC-> O(m) + O(m)
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        int max = 0;
        for(int i=0; i<n; i++) max=Math.max(max, nums[i]);
        int[] points = new int[max+1];
        for(int i=0; i<n; i++) points[nums[i]] += nums[i];
        int[] dp = new int[points.length];
        Arrays.fill(dp, -1);
        return houseRob(points, 0, dp);
    }
    public int houseRob(int[] points, int idx, int[] dp){
        int m = points.length;
        if(idx >= m) return 0;
        if(dp[idx] != -1) return dp[idx];
        int skip = houseRob(points, idx+1, dp);
        int take = points[idx] + houseRob(points, idx+2, dp);
        return dp[idx] = Math.max(take, skip);
    }

    // TC-> O(m) SC-> O(m)
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        int max = 0;
        for(int i=0; i<n; i++) max=Math.max(max, nums[i]);
        int[] points = new int[max+1];
        for(int i=0; i<n; i++) points[nums[i]] += nums[i];
        int[] dp = new int[points.length];
        dp[0] = points[0];
        dp[1] = Math.max(points[0], points[1]);
        for(int i=2; i<points.length; i++){
            dp[i] = Math.max(dp[i-1], points[i] + dp[i-2]);
        }
        return dp[points.length-1];
    }
}