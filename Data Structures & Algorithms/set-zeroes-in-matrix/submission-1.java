
class Solution {

    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // The first row and first column are reused as marker storage.
        // If matrix[i][0] == 0, then row i must be zeroed.
        // If matrix[0][j] == 0, then column j must be zeroed.
        // We need one extra flag because matrix[0][0] cannot distinguish
        // between "first row should be zeroed" and "first column should be zeroed".
        int col = 1;

        // Pass 1: scan the whole matrix and record which rows/columns
        // should be zeroed by writing markers into the first row/column.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    // Mark the entire row.
                    matrix[i][0] = 0;

                    // If the zero is in the first column, remember that
                    // the first column itself must be zeroed later.
                    if (j == 0) {
                        col = 0;
                    } else {
                        // Mark the entire column.
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        // Pass 2: use the markers to zero out the inner part of the matrix.
        // We skip row 0 and column 0 for now because they are being used as markers.
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Pass 3: zero out the first row if it was marked.
        if (matrix[0][0] == 0) {
            for (int j = 0; j < m; j++) {
                matrix[0][j] = 0;
            }
        }

        // Pass 4: zero out the first column if needed.
        if (col == 0) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
