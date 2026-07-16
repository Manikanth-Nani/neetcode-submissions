class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {

        // Sort intervals by their end time.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int removals = 0;

        // Keep the interval that finishes earliest.
        int previousEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            // A common endpoint is allowed:
            // [1, 2] and [2, 4] do not overlap.
            if (currentStart < previousEnd) {

                // The current interval overlaps with the kept interval.
                // Since intervals are sorted by end time, remove the current one.
                removals++;

            } else {

                // No overlap, so keep the current interval.
                previousEnd = currentEnd;
            }
        }

        return removals;
    }
}
