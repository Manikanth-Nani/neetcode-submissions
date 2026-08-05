class Solution {
    public String hashKey(String str){ 
        StringBuilder s = new StringBuilder();
        // Fixed size 26 for each lowercase English letter ('a' to 'z')
        int[] freq = new int[26];

        // Count occurrences of each character in the string
        for(char ch : str.toCharArray()){
            // Map character to index 0-25 by subtracting ASCII value of 'a'
            freq[ch - 'a']++;
        }

        // Build a unique string representation of the frequency array
        for(int i=0; i<26; i++){
            s.append(freq[i]);
            s.append('#'); // Separator prevents ambiguity (e.g., separating 1 and 11)
        }

        return s.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        int n = strs.length;
        List<List<String>> res = new ArrayList<>();
        // Maps a unique frequency hash key to its index inside the 'res' list
        HashMap<String, Integer> map = new HashMap<>();

        // Group words by their frequency-based hash keys
        for(String str: strs){
            String key = hashKey(str);

            // If this anagram group hasn't been seen yet, create a new sublist
            if (!map.containsKey(key)){
                // Map the key to the next available index in 'res' (current size)
                map.put(key, res.size());
                res.add(new ArrayList<>());
            }

            // Retrieve the correct sublist index and add the original string
            res.get(map.get(key)).add(str);
        }

        return res;
    }
}
