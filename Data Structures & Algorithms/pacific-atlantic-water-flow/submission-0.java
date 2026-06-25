class Solution {

    // 4 directions: up, right, down, left
    private final int[] dr = {-1, 0, 1, 0};
    private final int[] dc = {0, 1, 0, -1};

    // DFS marks all cells reachable from a given ocean
    public void dfs(int[][] heights, int i, int j, boolean[][] vis) {
        vis[i][j] = true;

        // Explore all 4 neighboring cells
        for (int d = 0; d < 4; d++) {
            int ni = i + dr[d];
            int nj = j + dc[d];

            // Valid cell + not visited + height condition
            // We move from ocean inward, so next cell must be
            // greater than or equal to current cell
            if (ni >= 0 && nj >= 0 &&
                ni < heights.length && nj < heights[0].length &&
                !vis[ni][nj] &&
                heights[ni][nj] >= heights[i][j]) {

                dfs(heights, ni, nj, vis);
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();

        // Edge case: empty grid
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return res;
        }

        int n = heights.length;
        int m = heights[0].length;

        // canReachPacific[i][j] = true if cell can reach Pacific
        boolean[][] canReachPacific = new boolean[n][m];

        // canReachAtlantic[i][j] = true if cell can reach Atlantic
        boolean[][] canReachAtlantic = new boolean[n][m];

        // Pacific touches top row and left column

        // DFS from top row
        for (int j = 0; j < m; j++) {
            if (!canReachPacific[0][j]) {
                dfs(heights, 0, j, canReachPacific);
            }
        }

        // DFS from left column
        for (int i = 0; i < n; i++) {
            if (!canReachPacific[i][0]) {
                dfs(heights, i, 0, canReachPacific);
            }
        }

        // Atlantic touches bottom row and right column

        // DFS from bottom row
        for (int j = 0; j < m; j++) {
            if (!canReachAtlantic[n - 1][j]) {
                dfs(heights, n - 1, j, canReachAtlantic);
            }
        }

        // DFS from right column
        for (int i = 0; i < n; i++) {
            if (!canReachAtlantic[i][m - 1]) {
                dfs(heights, i, m - 1, canReachAtlantic);
            }
        }

        // Cells reachable from both oceans are part of the answer
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (canReachPacific[i][j] && canReachAtlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }
}
