class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Stores the final merged result.
        List<int[]> ans = new ArrayList<>();

        int i = 0;
        int n = intervals.length;

        // 1) Add all intervals that come completely before newInterval.
        while (i < n && intervals[i][1] < newInterval[0]) {
            ans.add(intervals[i]);
            i++;
        }

        // 2) Merge all intervals that overlap with newInterval.
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // Add the merged interval once.
        ans.add(newInterval);

        // 3) Add the rest of the intervals.
        while (i < n) {
            ans.add(intervals[i]);
            i++;
        }

        return ans.toArray(new int[ans.size()][]);
    }
}
