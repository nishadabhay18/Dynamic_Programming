class Solution {
    int totalPaths = 0;
    public int uniquePathsIII(int[][] grid) {
        // totalPaths=0;
        int m = grid.length, n = grid[0].length;
        int startRow = -1, startCol = -1, totalFreeCells = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] != -1) totalFreeCells++;
                if(grid[i][j] == 1){
                    startRow = i;
                    startCol = j;
                }
            }
        }
        dfs(grid, startRow, startCol, totalFreeCells);
        return totalPaths;
    }
    public void dfs(int[][] grid, int r, int c, int cells){
        int m = grid.length, n = grid[0].length;
        if(r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == -1) return;
        if(grid[r][c] == 2){
            if(cells == 1) totalPaths++;
            return;
        }
        int temp = grid[r][c];
        grid[r][c] = -1;
        dfs(grid, r, c+1, cells-1); // right
        dfs(grid, r, c-1, cells-1); // left
        dfs(grid, r+1, c, cells-1); // bottom
        dfs(grid, r-1, c, cells-1); // top
        grid[r][c] = temp;
    }
}