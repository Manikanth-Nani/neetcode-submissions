class Solution {

    // Expands around the given center and returns the length of the
    // longest palindrome found.
    // left and right represent the center:
    // - (i, i) for odd-length palindromes
    // - (i, i + 1) for even-length palindromes
    public int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // When the loop stops, left/right moved one step too far.
        // So the palindrome length is:
        return right - left - 1;
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }

        int st = 0, end = 0;

        // Try every index as a center.
        // For each center, check:
        // 1) odd-length palindrome
        // 2) even-length palindrome
        for (int i = 0; i < n; i++) {
            int len1 = expandFromCenter(s, i, i);     // odd-length center
            int len2 = expandFromCenter(s, i, i + 1); // even-length center
            int len = Math.max(len1, len2);

            // If the newly found palindrome is longer than the best one so far,
            // update the start and end indices of the current answer.
            if (len > end - st + 1) {
                st = i - (len - 1) / 2;
                end = i + len / 2; // i is the center of the palindrome
            }
        }

        return s.substring(st, end + 1);
    }
}
