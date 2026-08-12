class Solution {
    public boolean isPalindrome(String s) {
        int n = s.length();
        int st = 0, end = n - 1;

        while (st < end) { // "st < end" is sufficient; a single middle char is always equal to itself
            char ch1 = s.charAt(st);
            char ch2 = s.charAt(end);

            // Skip non-alphanumeric characters from the left
            if (!Character.isLetterOrDigit(ch1)) {
                st++;
            }
            // Skip non-alphanumeric characters from the right
            else if (!Character.isLetterOrDigit(ch2)) {
                end--;
            }
            // If both are alphanumeric, convert to lowercase and compare
            else {
                if (Character.toLowerCase(ch1) != Character.toLowerCase(ch2)) {
                    return false;
                }
                st++;
                end--;
            }
        }

        return true;
    }
}
