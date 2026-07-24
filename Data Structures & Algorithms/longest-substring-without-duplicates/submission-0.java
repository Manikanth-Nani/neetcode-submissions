

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        // Maps each character to its most recent index in the string
        HashMap<Character, Integer> map = new HashMap<>();
        
        int res = 0; // Stores the maximum length found so far
        int left = 0, right = 0; // Two pointers defining the sliding window boundaries
        
        // Expand the window by moving the right pointer
        while(right < n){
            char ch = s.charAt(right);
            
            // If the character is a duplicate and exists within our current window
            if(map.containsKey(ch) && map.get(ch) >= left){
                // Shrink the window by jumping the left pointer past the previous duplicate
                left = map.get(ch) + 1;
            }
            
            // Calculate current window size and update the maximum length
            res = Math.max(res, right - left + 1);
            
            // Update or insert the current character's latest index
            map.put(ch, right);
            
            // Move right pointer forward to check the next character
            right++;
        }
        
        return res;
    }
}
