
class Solution {

    private final int[] dr = {-1, 0, 1, 0};
    private final int[] dc = {0, 1, 0, -1};

    // Mark all 'O' cells connected to the border as safe
    public void dfs(char[][] board, int i, int j) {
        board[i][j] = '#'; // temporary mark for safe cells

        for (int d = 0; d < 4; d++) {
            int ni = i + dr[d];
            int nj = j + dc[d];

            // Stay inside the board and continue only on 'O'
            if (ni >= 0 && nj >= 0 &&
                ni < board.length && nj < board[0].length &&
                board[ni][nj] == 'O') {
                dfs(board, ni, nj);
            }
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int n = board.length;
        int m = board[0].length;

        // 1) Mark all border-connected 'O' cells as safe

        // First and last column
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][m - 1] == 'O') dfs(board, i, m - 1);
        }

        // First and last row
        for (int j = 0; j < m; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[n - 1][j] == 'O') dfs(board, n - 1, j);
        }

        // 2) Flip surrounded regions and restore safe cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';   // surrounded region
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';   // restore safe region
                }
            }
        }
    }
}
