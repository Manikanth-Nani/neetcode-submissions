class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        
        // Edge case: If s1 is longer than s2, s2 cannot contain s1's permutation
        if (n > m) return false;

        // Step 1: Sort the target string s1 to establish a baseline for comparison
        char[] chrs = s1.toCharArray();
        Arrays.sort(chrs);
        String t1 = new String(chrs);
        
        // Initialize pointers for a fixed sliding window of size 'n'
        int st = 0, end = n - 1;

        // Step 2: Slide the window across s2
        while (end < m) {
            // Extract the current substring of length 'n' from s2
            String str = s2.substring(st, end + 1);
            
            // Step 3: Sort the current substring characters
            char[] chr1 = str.toCharArray();
            Arrays.sort(chr1);
            String strTemp = new String(chr1);
            
            // Step 4: Compare sorted versions. If they match, a permutation exists.
            if (t1.equals(strTemp)) return true;
            
            // Step 5: Shift the window right by 1 position
            st++;
            end++;
        }

        // No permutation found after checking all possible windows
        return false;
    }
}
