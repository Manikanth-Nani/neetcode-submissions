class Solution {

    // Expands outward from the given center and counts
    // how many palindromic substrings are formed.
    int expandFromCenter(String s, int left, int right) {
        int count = 0;

        // Keep expanding while:
        // 1) indices stay within bounds
        // 2) characters on both sides are equal
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;   // every successful expansion gives one palindrome
            left--;
            right++;
        }

        return count;
    }

    public int countSubstrings(String s) {
        int n = s.length();

        // Stores total number of palindromic substrings
        int count = 0;

        for (int i = 0; i < n; i++) {
            // Count odd-length palindromes centered at i
            count += expandFromCenter(s, i, i);

            // Count even-length palindromes centered between i and i + 1
            count += expandFromCenter(s, i, i + 1);
        }

        return count;
    }
}
