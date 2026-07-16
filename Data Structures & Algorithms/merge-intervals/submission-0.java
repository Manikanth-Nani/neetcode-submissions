class Solution {

    public int[][] merge(int[][] intervals) {

        // Sort intervals by their start value so overlapping ones become adjacent.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Stores the merged result intervals.
        List<int[]> ans = new ArrayList<>();

        // Track the current interval being merged.
        int currSt = intervals[0][0];
        int currEnd = intervals[0][1];

        // Process each interval from left to right.
        for (int i = 1; i < intervals.length; i++) {

            // If the current interval overlaps with the previous merged one,
            // extend the end boundary.
            if (intervals[i][0] <= currEnd) {
                currSt = Math.min(currSt, intervals[i][0]);
                currEnd = Math.max(currEnd, intervals[i][1]);
            } else {
                // No overlap: save the previous merged interval and start a new one.
                ans.add(new int[]{currSt, currEnd});
                currSt = intervals[i][0];
                currEnd = intervals[i][1];
            }
        }

        // Add the last merged interval.
        ans.add(new int[]{currSt, currEnd});

        return ans.toArray(new int[ans.size()][]);
    }
}
