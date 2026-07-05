class Solution {
    boolean ispoli(String s, int i, int j){
        while(i<=j){
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }

        return true;
    }

    public int countSubstrings(String s) {
        int n = s.length();
        if(n == 1) return 1;

        int count = 0;

        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                if(ispoli(s, i, j) ){
                   count++;
                }
            }
        }

        return count;
    }
}
