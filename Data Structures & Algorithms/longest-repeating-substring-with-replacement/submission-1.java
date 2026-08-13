
class Solution { 
    public int characterReplacement(String s, int k) {
        int n = s.length();
        
        // Maps characters to their current frequency count within the active window
        HashMap<Character, Integer> freq = new HashMap<>();
        
        // Tracks the highest frequency of any single character seen in the current window
        int maxFreq = 0;
        
        // Stores the length of the longest valid substring found
        int res = Integer.MIN_VALUE;

        // Initialize left (l) and right (r) pointers for the sliding window
        int l = 0, r = 0;
        
        // Expand the right side of the window across the string
        while (r < n) {

            // Get the current character at the right pointer and update its frequency
            char ch = s.charAt(r);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            
            // Maintain the maximum frequency of any character in the current window
            maxFreq = Math.max(maxFreq, freq.get(ch));
            
            // Calculate current window size
            int substrLen = r - l + 1;
            
            // If (Total Characters - Majority Character) > k, we need too many replacements.
            // This means the current window is invalid, so we must shrink it from the left.
            if (substrLen - maxFreq > k) {
                // Decrement the count of the character leaving the window from the left
                freq.put(s.charAt(l), freq.getOrDefault(s.charAt(l), 0) - 1);
                
                // Shrink the window by moving the left pointer forward
                l++;
            }

            // Update the result with the maximum valid window size found so far
            res = Math.max(res, r - l + 1);
            
            // Move right pointer forward to continue expanding the window
            r++;
        }
        
        
        return res;
    }
}
