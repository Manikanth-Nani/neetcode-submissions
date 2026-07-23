
class Solution {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int totalGas = 0; // Total net gas for the entire circuit
        int currGas = 0;  // Net gas for the current segment
        int st = 0;       // Candidate starting station

        for (int i = 0; i < n; i++) {
            int diff = gas[i] - cost[i];
            totalGas += diff;   // Update total net gas
            currGas += diff;    // Update current segment net gas

            // If currGas drops below zero, cannot reach station i+1 from current 'st'
            // So, set next station as new candidate and reset currGas
            if (currGas < 0) {
                st = i + 1;
                currGas = 0;
            }
        }

        // If totalGas is negative, it's impossible to complete the circuit
        if (totalGas < 0) return -1;

        // Otherwise, return the valid starting station
        return st;
    }
}