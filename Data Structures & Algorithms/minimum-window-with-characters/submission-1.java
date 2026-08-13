class Solution {
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();

        // Edge Case: If t is longer than s, it's impossible to find a valid window
        if (m > n) return "";

        // Frequency arrays to track characters for ASCII limit (256)
        int[] freqS = new int[256];
        int[] freqT = new int[256];
        
        // Populate the frequency map for the target string 't'
        for (int i = 0; i < m; i++) {
            freqT[t.charAt(i)]++;
        }
        
        int stIdx = -1; // Keeps track of the starting index of the best window found
        int minlen = Integer.MAX_VALUE; // Stores the minimum window length found so far
        int l = 0, r = 0; // Left and Right pointers for the sliding window
        int cnt = 0; // Tracks how many characters of 't' are currently matched inside the window
        
        // Expand the window by moving the right pointer
        while (r < n) {
            char rightChar = s.charAt(r);
            freqS[rightChar]++; // Add the current character to the window's frequency map
            
            // If the character is needed in 't' AND we haven't collected excess copies of it yet,
            // increment our matching counter (cnt).
            if (freqT[rightChar] > 0 && (freqS[rightChar] <= freqT[rightChar])) {
                cnt++;
            }

            // Once the window contains all the required characters of 't'
            if (cnt == m) {
                // Shrink the window from the left to remove useless or duplicate characters.
                // Loop continues if:
                // 1. We have more copies of s.charAt(l) than 't' requires, OR
                // 2. The character s.charAt(l) is not needed by 't' at all (freqT == 0).
                while (freqS[s.charAt(l)] > freqT[s.charAt(l)] || freqT[s.charAt(l)] == 0) {
                    char leftChar = s.charAt(l);
                    if (freqS[leftChar] > freqT[leftChar]) {
                        freqS[leftChar]--; // Reduce count from window map since we are discarding it
                    }
                    l++; // Move the left pointer forward to shrink the window
                }

                // If the newly minimized window is smaller than our previous minimum window,
                // update our tracking variables.
                if (r - l + 1 < minlen) {
                    minlen = r - l + 1;
                    stIdx = l; // Record the starting position of this valid window
                }
            }

            r++; // Keep expanding the window to look for other options
        }

        // If stIdx remained -1, it means no valid window was found. 
        // Otherwise, return the specific substring slicing from stIdx to stIdx + minlen.
        return stIdx == -1 ? "" : s.substring(stIdx, stIdx + minlen);
    }
}
