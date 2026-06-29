class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Put all valid dictionary words into a set for O(1) lookup
        HashSet<String> wordSet = new HashSet<>();
        for (String word : wordList) {
            wordSet.add(word);
        }

        // If the target word is not in the dictionary, no transformation is possible
        if (!wordSet.contains(endWord)) return 0;

        // Frontiers for bidirectional BFS:
        // beginSet = current level from beginWord side
        // endSet   = current level from endWord side
        HashSet<String> beginSet = new HashSet<>();
        HashSet<String> endSet = new HashSet<>();

        // Track words we have already visited/queued to avoid repeating work
        HashSet<String> vis = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        // Number of transformation steps so far
        int steps = 1;

        // Continue while both search fronts still have words to expand
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {

            // Always expand the smaller frontier to reduce branching
            if (beginSet.size() > endSet.size()) {
                HashSet<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            // Collect all valid words for the next BFS layer
            HashSet<String> nextSet = new HashSet<>();

            // Expand every word in the current frontier
            for (String word : beginSet) {

                // Try changing each character one by one
                for (int i = 0; i < word.length(); i++) {

                    // Replace the current character with every possible lowercase letter
                    for (char c = 'a'; c <= 'z'; c++) {

                        // Skip if the character is unchanged
                        if (c == word.charAt(i)) continue;

                        // Create the transformed word
                        String newWord = word.substring(0, i) + c + word.substring(i + 1);

                        // If the transformed word exists in the opposite frontier,
                        // the two BFS searches have met, so we found the shortest path
                        if (endSet.contains(newWord)) return steps + 1;

                        // Otherwise, if it is a valid dictionary word and not visited,
                        // add it to the next layer
                        if (wordSet.contains(newWord) && !vis.contains(newWord)) {
                            vis.add(newWord);
                            nextSet.add(newWord);
                        }
                    }
                }
            }

            // Move to the next BFS layer
            beginSet = nextSet;
            steps++;
        }

        // No transformation sequence exists
        return 0;
    }
}
