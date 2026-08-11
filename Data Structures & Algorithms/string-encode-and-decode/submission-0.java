
class Solution {
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            // Append length + delimiter + the actual string
            sb.append(str.length()).append("#").append(str);
        }
        return sb.toString();
    }

    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        int i = 0;
        
        while (i < str.length()) {
            // Find where the length ends and the string begins
            int j = i;
            while (str.charAt(j) != '#') {
                j++;
            }
            
            // Extract the length of the upcoming string
            int length = Integer.parseInt(str.substring(i, j));
            
            // Move pointer past the '#' delimiter
            i = j + 1;
            
            // Extract the actual string using the known length
            String originalStr = str.substring(i, i + length);
            result.add(originalStr);
            
            // Move pointer to the start of the next encoded block
            i += length;
        }
        
        return result;
    }
}
