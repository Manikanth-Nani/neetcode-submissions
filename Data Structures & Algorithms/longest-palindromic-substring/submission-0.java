class Solution {
    // generate all substring = o(n^3)
    boolean ispoli(String s, int i, int j){
        while(i<=j){
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }

        return true;
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        if(n == 1) return s;

        int st = 0;
        int len = 1;

        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                if(ispoli(s, i, j) && (j-i+1 > len) ){
                    len = j - i + 1;
                    st = i;
                }
            }
        }

        return s.substring(st, st+ len);
    }
}
