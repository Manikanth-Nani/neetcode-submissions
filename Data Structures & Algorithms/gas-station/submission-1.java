
class Solution {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length; 

        // Phase 1: Find a potential starting station
        // 'st' will hold the candidate starting index
        // 'currGas' tracks the net gas as we traverse stations
        int st = 0;
        int currGas = 0;

        for (int i = 0; i < n; i++) {
            currGas += gas[i] - cost[i]; // Add net gas at station i

            // If net gas drops below zero, cannot start from current 'st'
            // Move 'st' to the next station and reset 'currGas'
            if (currGas < 0) {
                st = i + 1;
                currGas = 0;
            }
        }

        // If 'st' equals n, it means no valid starting station exists
        if (st == n) return -1;

        // Phase 2: Verify that starting from 'st' allows a full circuit
        int i = st;
        int totalgas = 0;

        // Simulate the journey for n stations starting from 'st'
        for (int j = 0; j < n; j++) {
            int idx = (i + j) % n; // Circular index for stations
            totalgas += gas[idx] - cost[idx]; // Update total gas after visiting station

            // If at any point total gas drops below zero, cannot complete the circuit
            if (totalgas < 0) {
                return -1;
            }
        }
        return st;
    }
}