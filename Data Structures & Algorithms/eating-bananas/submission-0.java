

class Solution {

    // Helper function to check if Koko can finish all banana piles in 'h' hours at speed 'k'
    public boolean isKokoCanEat(int[] piles, int h, int k){
        int n = piles.length;
        int hours = 0; // To track total hours Koko needs

        for(int i = 0; i < n; i++){
            // Koko can eat up to 'k' bananas from pile[i] in one hour
            // Each pile requires ceil(piles[i] / k) hours to finish
            hours += piles[i] / k;      // full hours
            if(piles[i] % k != 0){      // needs an extra hour if not divisible i.e to eat rest ones
                hours++;
            }

            // If at any point hours exceeds h, return false early
            if(hours > h){
                return false;
            }
        }

        // If total hours are within limit, return true
        return true;
    }

    
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;

        // Koko can eat from 1 to max(piles) bananas per hour
        // Initialize search bounds
        int low = 1;      // Minimum possible speed (must be at least 1, not zero to avoid division by zero)
        int high = piles[0];
        for(int i = 1; i < n; i++){
            high = Math.max(high, piles[i]); // Find the maximum pile size
        }

        int ans = high; // Store current minimum feasible speed

        // Binary search for the smallest possible eating speed
        while(low <= high){
            int mid = low + (high - low) / 2; // Try a middle speed

            if(isKokoCanEat(piles, h, mid)){
                // If Koko can finish at this speed, try slower speeds
                ans = mid;
                high = mid - 1;
            } else {
                // If not, she needs to eat faster
                low = mid + 1;
            }
        }

        
        return ans;
    }
}