class Solution {
    public boolean checkEqual(String str1, String str2){
        // Size 26 perfectly fits characters 'a' through 'z'
        int[] freq = new int[26];
        
        // Count frequencies of s1
        for(int i = 0; i < str2.length(); i++){
            // Subtracting 'a' maps 'a'->0, 'b'->1, ..., 'z'->25
            freq[str2.charAt(i) - 'a']++;
        }

        // Subtract frequencies of the current window from s2
        for(int i = 0; i < str1.length(); i++){
            freq[str1.charAt(i) - 'a']--;
        }

        // Validate if all frequencies canceled out to zero
        for(int i = 0; i < 26; i++){
            if(freq[i] > 0) return false;
        }

        return true;
    }

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        
        if (n > m) return false;
        
        int st = 0, end = n - 1;

        while (end < m) {
            String str = s2.substring(st, end + 1);
            
            if(checkEqual(str, s1)){
                return true;
            }
            
            st++;
            end++;
        }

        return false;
    }
}
