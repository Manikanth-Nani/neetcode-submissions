class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for(int i=0; i<nums.length;i++){
            freq.put(nums[i], freq.getOrDefault(nums[i], 0)+1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (int[] a, int[] b) -> a[1] - b[1]
        );

        for(Map.Entry<Integer, Integer> entry: freq.entrySet()){
            pq.add(new int[]{entry.getKey(), entry.getValue()});
            if(pq.size() > k) pq.remove();
        }

        int[] res = new int[k];
        int idx=0;
        while(pq.size()>0){
            res[idx++] = pq.remove()[0];
        }

        return res;
    }
}
