class MedianFinder {

    private PriorityQueue<Integer> minheap; // Stores the larger half of numbers
    private PriorityQueue<Integer> maxheap; // Stores the smaller half of numbers

    public MedianFinder() {
        // Default PriorityQueue in Java acts as a Min-Heap
        minheap = new PriorityQueue<>();
        // Passing Collections.reverseOrder() turns it into a Max-Heap
        maxheap = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    
    public void addNum(int num) {
        // Base Case: If both heaps are empty, route the very first element to the maxheap
        if (maxheap.isEmpty() && minheap.isEmpty()) {
            maxheap.add(num);
        } else {
            // Deciding where the new number belongs:
            // If the number is greater than the largest number in the smaller half, 
            // it belongs in the larger half (minheap).
            if (maxheap.peek() < num) {
                minheap.add(num);
            } else {
                maxheap.add(num);
            }

            // Rebalance check: If the size difference between the two heaps exceeds 1, 
            // we must balance them to keep the median accessible at the root(s).
            if (Math.abs(minheap.size() - maxheap.size()) > 1) {
                balanceHeaps();
            }
        }
    }
    
    
    public double findMedian() {
        double median;
        int n = minheap.size(), m = maxheap.size();
        
        // If minheap has more elements, the median is its top element
        if (n > m) {
            median = minheap.peek();
        } 
        // If maxheap has more elements, the median is its top element
        else if (m > n) {
            median = maxheap.peek();
        } 
        // If both heaps have an equal number of elements, the total count is even.
        // The median is the average of both top elements.
        else {
            // Dividing by 2.0 avoids integer division bugs and safely promotes to double
            median = (minheap.peek() + maxheap.peek()) / 2.0;
        }
        return median;
    }

    
    public void balanceHeaps() {
        // Shift the smallest element of the larger half to the smaller half
        if (minheap.size() > maxheap.size()) {
            maxheap.add(minheap.remove());
        } 
        // Shift the largest element of the smaller half to the larger half
        else {
            minheap.add(maxheap.remove());
        }
    }
}
