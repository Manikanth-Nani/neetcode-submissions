class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (intervals[i][1] < newInterval[0]) {
                // Add non-overlapping intervals before the new interval
                ans.add(new int[]{intervals[i][0], intervals[i][1]});
            } else if (intervals[i][0] > newInterval[1]) {
                // Add the new interval before adding the remaining intervals
                ans.add(newInterval);
                // Add the remaining intervals
                for (int j = i; j < n; j++) {
                    ans.add(new int[]{intervals[j][0], intervals[j][1]});
                }
                // Convert the list to a 2D array and return
                return ans.toArray(new int[ans.size()][]);
            } else {
                // Merge overlapping intervals
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }

        // Add the new interval if it has not been added yet
        ans.add(newInterval);
        return ans.toArray(new int[ans.size()][]);
    }
}