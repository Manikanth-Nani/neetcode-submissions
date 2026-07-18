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
    public int minMeetingRooms(List<Interval> intervals) {
        // Handle edge case where there are no scheduled meetings
        if (intervals == null || intervals.size() == 0) {
            return 0;
        }

        // Sort all meetings chronologically by their start times
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));
        
        // Min-heap tracks the end times of rooms currently in use
        // The room freeing up earliest will always stay at the top
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Reserve the first room by tracking the first meeting's end time
        pq.add(intervals.get(0).end);

        // Process all remaining meetings one by one
        for (int i = 1; i < intervals.size(); i++) {
            // Check if the current meeting starts after or when the earliest meeting finishes
            if (intervals.get(i).start >= pq.peek()) {
                // No conflict: Evict the finished meeting to reuse that same room
                pq.remove();
            }

            // Book the room (either a newly added room or a reused one)
            // by adding the current meeting's end time to the heap
            pq.add(intervals.get(i).end);
        }

        // The total number of elements remaining in the heap 
        // represents the peak number of concurrent rooms required
        return pq.size();
    }
}
