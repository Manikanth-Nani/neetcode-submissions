class Solution {

    // Helper class to store a grid cell and the minimum time needed to reach it.
    class Triplet {
        int i; // row index
        int j; // column index
        int t; // time required so far to reach this cell

        Triplet(int i, int j, int t) {
            this.i = i;
            this.j = j;
            this.t = t;
        }
    }

    public int swimInWater(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Directions: down, right, up, left
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        // Min-heap based on the minimum time required to reach a cell
        PriorityQueue<Triplet> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.t, b.t)
        );

        // Start from the top-left cell.
        // The initial time is the elevation of the starting cell.
        pq.add(new Triplet(0, 0, grid[0][0]));

        // Mark the starting cell as visited.
        // Using -1 works because grid values are non-negative.
        grid[0][0] = -1;

        while (!pq.isEmpty()) {
            Triplet current = pq.remove();
            int row = current.i;
            int col = current.j;
            int timeSoFar = current.t;

            // Reached bottom-right cell, so this is the minimum possible time.
            if (row == rows - 1 && col == cols - 1) {
                return timeSoFar;
            }

            // Try all 4 neighboring cells.
            for (int d = 0; d < 4; d++) {
                int nextRow = row + dr[d];
                int nextCol = col + dc[d];

                // Check bounds and ensure the cell has not been visited.
                if (nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols
                        && grid[nextRow][nextCol] != -1) {

                    // If the next cell is higher, we must wait until that elevation.
                    // Otherwise, we can move immediately.
                    int nextTime = Math.max(timeSoFar, grid[nextRow][nextCol]);

                    pq.add(new Triplet(nextRow, nextCol, nextTime));

                    // Mark visited as soon as the cell is added to the queue.
                    // This prevents pushing the same cell multiple times.
                    grid[nextRow][nextCol] = -1;
                }
            }
        }

        return -1;
    }
}
