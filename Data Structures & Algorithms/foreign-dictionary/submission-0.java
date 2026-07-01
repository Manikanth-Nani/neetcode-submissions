
class Solution {

    public String foreignDictionary(String[] words) {
        int wordCount = words.length;

        // Graph of character ordering constraints:
        // edge u -> v means character u must come before character v
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[26];
        boolean[] present = new boolean[26];

        // Mark every character that actually appears in the input
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                present[ch - 'a'] = true;
            }
        }

        // Build graph from adjacent word pairs
        for (int i = 0; i < wordCount - 1; i++) {
            String firstWord = words[i];
            String secondWord = words[i + 1];

            int minLength = Math.min(firstWord.length(), secondWord.length());
            boolean foundMismatch = false;

            for (int j = 0; j < minLength; j++) {
                int from = firstWord.charAt(j) - 'a';
                int to = secondWord.charAt(j) - 'a';

                // First differing character determines the ordering
                if (from != to) {
                    graph.get(from).add(to);
                    indegree[to]++;
                    foundMismatch = true;
                    break;
                }
            }

            // If no mismatch was found, the shorter word must come first.
            // Example: "abc" before "ab" is invalid.
            if (!foundMismatch && firstWord.length() > secondWord.length()) {
                return "";
            }
        }

        // Kahn's algorithm: start with all present characters that have indegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (present[i] && indegree[i] == 0) {
                queue.offer(i);
            }
        }

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.append((char) (current + 'a'));

            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If some present character still has indegree > 0, there's a cycle
        // and no valid order exists.
        for (int i = 0; i < 26; i++) {
            if (present[i] && indegree[i] > 0) {
                return "";
            }
        }

        return result.toString();
    }
}
