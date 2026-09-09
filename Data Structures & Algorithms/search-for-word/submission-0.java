

class Solution {

    // Performs depth-first search from the current cell
    private boolean search(char[][] board,String word,int row,int col,int index,boolean[][] visited) {

        // All characters in the word have been matched
        if (index == word.length()) {
            return true;
        }

        // Stop if the position is invalid, already used, or mismatched
        if (row < 0 || row >= board.length ||
            col < 0 || col >= board[0].length ||
            visited[row][col] ||
            board[row][col] != word.charAt(index)) {
            return false;
        }

        // Mark this cell as used for the current path
        visited[row][col] = true;

        // Search in all four directions
        boolean found =
                search(board, word, row - 1, col, index + 1, visited) || // Up
                search(board, word, row + 1, col, index + 1, visited) || // Down
                search(board, word, row, col - 1, index + 1, visited) || // Left
                search(board, word, row, col + 1, index + 1, visited);   // Right

        // Backtrack so another path can reuse this cell
        visited[row][col] = false;

        return found;
    }

    public boolean exist(char[][] board, String word) {

        // Handles invalid input
        if (board == null || board.length == 0 ||
            board[0].length == 0 || word == null || word.length() == 0) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];

        // Try starting the search from every cell
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {

                if (search(board, word, row, col, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }
}
