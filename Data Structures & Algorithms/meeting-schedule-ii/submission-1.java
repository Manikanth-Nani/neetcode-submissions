
class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        int n = intervals.size();
        if (n == 0) return 0;

        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        // Step 1: Extract start and end times
        for (int i = 0; i < n; i++) {
            startTimes[i] = intervals.get(i).start;
            endTimes[i] = intervals.get(i).end;
        }

        // Step 2: Sort both arrays independently
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int startPtr = 0;
        int endPtr = 0;
        int roomsUsed = 0;

        // Step 3: Iterate through all starting meetings
        while (startPtr < n) {
            // If there is a conflict, allocate a room
            if (startTimes[startPtr] < endTimes[endPtr]) {
                roomsUsed++;
            } else {
                // No conflict, a room opened up
                endPtr++;
            }
            startPtr++;
        }

        return roomsUsed;
    }
}
