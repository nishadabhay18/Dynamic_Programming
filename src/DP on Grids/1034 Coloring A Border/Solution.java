class Solution {
    // TC-> O(m*n) SC-> O(m*n)
     public static class Pair{
         int row, col;
         Pair(int row, int col){
             this.row = row;
             this.col = col;
         }
     }
     public int[][] colorBorder(int[][] grid, int row, int col, int color) {
         int m = grid.length, n = grid[0].length;
         for(int i=0; i<m; i++){
             for(int j=0;j<n;j++){
                 if(i == row && j == col){
                     bfs(grid, color, row, col);
                 }
             }
         }
         return grid;
     }
     public void bfs(int[][] grid, int color, int row,int col){
         int m = grid.length, n = grid[0].length;
         boolean[][] isVisited = new boolean[m][n];
         List<Pair> list = new ArrayList<>();
         int original = grid[row][col];
         Queue<Pair> q = new ArrayDeque<>();
         q.add(new Pair(row, col));
         isVisited[row][col] = true;
         int[] dx = {-1, 0, 1, 0};
         int[] dy = {0, 1, 0, -1};
         while(q.size() != 0){
             Pair val = q.remove();
             int r = val.row, c = val.col;
             boolean isBorder = false;
             for(int k=0; k<4; k++){
                 int nr = r+dx[k], nc = c+dy[k];
                 if(nr<0 || nr>=m || nc<0 || nc>=n || grid[nr][nc]!=original) isBorder=true;
                 // else if(grid[nr][nc]!=original) isBorder=true;
                 else if(isVisited[nr][nc] == false && grid[nr][nc] == original){
                     q.add(new Pair(nr, nc));
                     isVisited[nr][nc] = true;
                 }
             }
             if(isBorder == true) list.add(val);
         }
         for(Pair ele : list) grid[ele.row][ele.col] = color;
     }

    // TC-> O(m*n) SC-> O(m*n) without stack space
    public static class Pair {
        int row, col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        dfs(grid, color, row, col);
        return grid;
    }
    public void dfs(int[][] grid, int color, int row, int col) {
        int m = grid.length, n = grid[0].length;
        boolean[][] isVisited = new boolean[m][n];
        List<Pair> list = new ArrayList<>();
        int original = grid[row][col];
        dfss(grid, row, col, list, isVisited, original);
        for (Pair ele : list) grid[ele.row][ele.col] = color;
    }
    public void dfss(int[][] grid, int row, int col, List<Pair> list, boolean[][] isVisited, int original) {
        int m = grid.length, n = grid[0].length;
        isVisited[row][col] = true;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        boolean isBorder = false;
        for (int k = 0; k < 4; k++) {
            int nr = row + dx[k], nc = col + dy[k];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] != original) isBorder = true;
            else if (!isVisited[nr][nc]) dfss(grid, nr, nc, list, isVisited, original);
        }
        if(isBorder) list.add(new Pair(row, col));
    }
}