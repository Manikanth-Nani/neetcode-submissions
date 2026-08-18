class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // Deque to store the indices of useful elements in the current window
        Deque<Integer> dq = new LinkedList<>();

        // Array to store the result
        int[] ans = new int[n - k + 1];

        // Index for the result array
        int j = 0;

        // Process the first k elements to initialize the deque
        for (int i = 0; i < k; i++) {
            // Remove elements from the deque that are smaller than the current element
            while (dq.size() > 0 && nums[i] > nums[dq.getLast()]) {
                dq.removeLast();
            }
            // Add the current element index to the deque
            dq.addLast(i);
        }

        // Add the maximum of the first window to the result
        ans[j++] = nums[dq.getFirst()];

        // Initialize start and end pointers for the sliding window
        int st = 1, end = k;

        // Process the remaining elements
        while (end < n) {
            // Remove the element that is out of the current window
            if (dq.getFirst() == st - 1) {
                dq.removeFirst();
            }

            // Remove elements from the deque that are smaller than the current element
            while (dq.size() > 0 && nums[end] > nums[dq.getLast()]) {
                dq.removeLast();
            }

            // Add the current element index to the deque
            dq.addLast(end);

            // Add the maximum of the current window to the result
            ans[j++] = nums[dq.getFirst()];

            // Move the window forward
            st++;
            end++;
        }

        return ans;
    }
}