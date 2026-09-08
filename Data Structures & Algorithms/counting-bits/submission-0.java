class Solution {
    public int[] countBits(int n) {
        // Create an array of size n + 1 to store the results
        int[] ans = new int[n + 1];
        
        // Loop from 1 to n to fill the array using previously computed values
        for (int i = 1; i <= n; i++) {
            // i >> 1 drops the last bit (equivalent to i / 2)
            // i & 1 extracts the last bit (1 if odd, 0 if even)
            ans[i] = ans[i >> 1] + (i & 1);
        }
        
        return ans;
    }
}
