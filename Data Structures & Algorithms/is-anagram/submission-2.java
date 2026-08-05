class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] freq = new int[26];
        for(int i=0; i<s.length(); i++){
            int idx = s.charAt(i) - 'a';
            freq[idx]++;
        }

        for(int i=0; i<t.length(); i++){
            int idx = t.charAt(i) - 'a';
            if(freq[idx] > 0){
                freq[idx]--;
            }
            else{
                return false;
            }
        }

        return true;
    }
}
