
class Solution {

    // Represents one point in the priority queue:
    // vertex = index of the point
    // cost   = minimum edge cost to connect this point to the growing MST
    class PointEdge {
        int pointIndex;
        int connectionCost;

        PointEdge(int pointIndex, int connectionCost) {
            this.pointIndex = pointIndex;
            this.connectionCost = connectionCost;
        }
    }

    public int minCostConnectPoints(int[][] points) {
        int numberOfPoints = points.length;

        // Tracks whether a point is already included in the MST
        boolean[] isConnected = new boolean[numberOfPoints];

        // Min-heap ordered by the cheapest connection cost
        PriorityQueue<PointEdge> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.connectionCost, b.connectionCost)
        );

        // Start MST from point 0 with cost 0
        minHeap.add(new PointEdge(0, 0));

        int totalMSTCost = 0;

        while (!minHeap.isEmpty()) {
            PointEdge current = minHeap.remove();
            int currentPoint = current.pointIndex;
            int currentCost = current.connectionCost;

            // Skip if this point is already part of the MST
            if (isConnected[currentPoint]) {
                continue;
            }

            // Include this point in the MST
            isConnected[currentPoint] = true;
            totalMSTCost += currentCost;

            // Add edges from the newly included point to all unvisited points
            for (int nextPoint = 0; nextPoint < numberOfPoints; nextPoint++) {
                if (!isConnected[nextPoint]) {
                    int manhattanDistance =
                        Math.abs(points[currentPoint][0] - points[nextPoint][0]) +
                        Math.abs(points[currentPoint][1] - points[nextPoint][1]);

                    minHeap.add(new PointEdge(nextPoint, manhattanDistance));
                }
            }
        }

        return totalMSTCost;
    }
}
