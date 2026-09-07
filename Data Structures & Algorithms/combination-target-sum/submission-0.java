
class Solution {

    public void tarSum(int[] nums, int target, int i, int n, List<Integer> res, List<List<Integer>> ans) {
        // Base Case 1: Found a valid combination
        if (target == 0) {
            ans.add(new ArrayList<>(res));
            return;
        }

        // Base Case 2: Out of bounds or target went negative (Pruning)
        if (i == n || target < 0) {
            return;
        }

        // Choice 1: Include the current element nums[i]
        res.add(nums[i]);
        // Note: We pass 'i' (not 'i + 1') because we can reuse the same element multiple times
        tarSum(nums, target - nums[i], i, n, res, ans);
        
        // Backtrack: Remove the element to explore the "exclude" path
        res.remove(res.size() - 1);

        // Choice 2: Exclude the current element nums[i] and move to the next index
        tarSum(nums, target, i + 1, n, res, ans);
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        tarSum(nums, target, 0, nums.length, new ArrayList<>(), ans);
        return ans;
    }
}
