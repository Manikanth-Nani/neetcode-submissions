
class Solution {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length; 

        // Try starting from each station
        for(int i = 0; i < n; i++) {
            int currGas = 0; // Current gas in tank
            int st = i;      // Starting station index

            // Simulate the journey for n stations
            for(int j = 0; j < n; j++) {
                int idx = (i + j) % n; // Circular index for stations
                currGas += gas[idx] - cost[idx]; // Update current gas after visiting station

                // If at any point gas drops below zero, cannot start from 'i'
                if(currGas < 0) {
                    st = -1; // Mark as invalid start
                    break;   
                }
            }

            // If a valid start is found, return its index
            if(st != -1) return st;
        }

        // If no valid start found, return -1
        return -1;
    }
}