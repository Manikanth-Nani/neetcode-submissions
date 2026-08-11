class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> freq = new HashMap<>();

        // Build frequency map
        for (int i = 0; i < n; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        // Initialize bucket array where index represents frequency count.
        // Size is n + 1 because the maximum possible frequency of an element is n.
        List<Integer>[] buckets = new List[nums.length + 1];
        
        // Group numbers by their frequencies into respective buckets.
        for(int key: freq.keySet()){
            int count = freq.get(key);
            
            // Lazily instantiate the list inside the bucket if it's empty.
            if(buckets[count] == null){
                buckets[count] = new ArrayList<>();
            }

            buckets[count].add(key);
        }
        
        // Build result array (order may not be most frequent first)
        int[] res = new int[k];
        int idx = 0;
        
        // Iterate backwards from the highest possible frequency bucket to the lowest.
        for (int i = buckets.length - 1; i >= 0 && idx < k; i--) {
            // Check if any numbers share this specific frequency.
            if (buckets[i] != null) {
                // Collect elements from the bucket until we gather exactly k elements.
                for (int num : buckets[i]) {
                    res[idx++] = num;
                    if (idx == k) {
                        return res; // Short-circuit early as soon as top k elements are found.
                    }
                }
            }
        }
        return res;
    }
}
