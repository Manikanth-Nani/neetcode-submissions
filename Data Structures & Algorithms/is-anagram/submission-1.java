class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        HashMap<Character, Integer> freq = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0)+1);
        }

        for(int i=0; i<t.length(); i++){
            char ch = t.charAt(i);
            if(freq.containsKey(ch)){
                freq.put(ch, freq.getOrDefault(ch, 0)-1);
                if(freq.get(ch) == 0) freq.remove(ch);
            }
            else{
                return false;
            }
        }

        return true;
    }
}
