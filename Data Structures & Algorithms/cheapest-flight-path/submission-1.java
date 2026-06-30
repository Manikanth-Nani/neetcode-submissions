
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INF = (int) 1e9;
        int[] dis = new int[n];
        Arrays.fill(dis, INF);
        dis[src] = 0;

        // At most k stops => at most k + 1 edges
        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(dis, n);

            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int cost = flight[2];

                // Read from dis, write into temp
                if (dis[u] != INF && dis[u] + cost < temp[v]) {
                    temp[v] = dis[u] + cost;
                }
            }

            dis = temp;
        }

        return dis[dst] == INF ? -1 : dis[dst];
    }
}
