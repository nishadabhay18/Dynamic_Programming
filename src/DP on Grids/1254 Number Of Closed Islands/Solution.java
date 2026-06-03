class Solution {
    public static class Pair {
        int row, col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    // TC-> O(m*n) SC-> O(m*n)
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) count += bfs(grid, i, j);
            }
        }
        return count;
    }
    public int bfs(int[][] grid, int row, int col) {
        int m = grid.length, n = grid[0].length;
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(row, col));
        grid[row][col] = 1;
        boolean isClosed = true;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        while (!q.isEmpty()) {
            Pair val = q.remove();
            int r = val.row, c = val.col;
            if (r == 0 || c == 0 || r == m-1 || c == n-1) isClosed = false;
            for (int k = 0; k < 4; k++) {
                int nr = r + dx[k], nc = c + dy[k];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                    q.add(new Pair(nr, nc));
                    grid[nr][nc] = 1;
                }
            }
        }
        return isClosed ? 1 : 0;
    }
}