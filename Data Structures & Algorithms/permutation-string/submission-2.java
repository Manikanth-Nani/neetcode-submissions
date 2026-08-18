class Solution {
    /**
     * Helper method to compare character frequencies of two arrays.
     * Time Complexity: O(1) as it always runs exactly 26 times.
     */
    private boolean areFrequenciesEqual(int[] s1Counts, int[] currentWindowCounts) {
        for (int i = 0; i < 26; i++) {
            if (s1Counts[i] != currentWindowCounts[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Primary method to check if s2 contains a permutation of s1.
     * Time Complexity: O(M) where M is the length of s2.
     * Space Complexity: O(1) using fixed-size arrays of size 26.
     */
    public boolean checkInclusion(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        
        // Edge case: A longer string cannot be a permutation of a shorter string
        if (s1Length > s2Length) {
            return false;
        }
        
        // Frequency arrays for lowercase English letters ('a' through 'z')
        int[] s1Counts = new int[26];
        int[] currentWindowCounts = new int[26];

        // Step 1: Initialize frequencies for s1 and the very first window of s2
        for (int i = 0; i < s1Length; i++) {
            s1Counts[s1.charAt(i) - 'a']++; 
            currentWindowCounts[s2.charAt(i) - 'a']++; 
        }

        // Step 2: Check if the initial window matches s1 immediately
        if (areFrequenciesEqual(s1Counts, currentWindowCounts)) {
            return true;
        }
        
        // Setup pointers for the sliding window
        int windowStart = 1;
        int windowEnd = s1Length;

        // Step 3: Slide the fixed-size window across s2
        while (windowEnd < s2Length) {
            // Drop the character that is falling out from the left of the window
            currentWindowCounts[s2.charAt(windowStart - 1) - 'a']--;
            
            // Add the new character entering the right side of the window
            currentWindowCounts[s2.charAt(windowEnd) - 'a']++;

            // Check if the modified window matches the target baseline
            if (areFrequenciesEqual(s1Counts, currentWindowCounts)) {
                // Permutation found!
                return true; 
            }
            
            // Move both boundaries forward to maintain a fixed window size
            windowStart++;
            windowEnd++;
        }

        // No permutation discovered after checking all valid substrings
        return false;
    }
}
