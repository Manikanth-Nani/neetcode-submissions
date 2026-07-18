/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        int n = intervals.size();
        
        // Base case: If there are no meetings scheduled, there are no conflicts
        if (n == 0) return true;
        
        // Sort intervals chronologically by their start times 
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));
        
        // Track the end time of the very first meeting to begin comparisons
        int prevEnd = intervals.get(0).end;

        // Iterate through the remaining meetings starting from the second one (index 1)
        for (int i = 1; i < n; i++) {
            
            int currStart = intervals.get(i).start;
            
            // Conflict Check: If current meeting starts before the previous meeting finishes, 
            // the person cannot attend both.
            if (currStart < prevEnd) {
                return false;
            }

            // No conflict: Update prevEnd to the current meeting's end time for the next iteration
            prevEnd = intervals.get(i).end;
        }

        // Checked all meetings sequentially with zero overlaps found
        return true;
    }
}
