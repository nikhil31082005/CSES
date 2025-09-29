import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Flight_Routes {
    static class Edge {
        int node;
        int weight;
        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }

        // PQ for global processing: (node, cost)
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.add(new long[]{1, 0});

        // Each node stores at most k shortest distances in a max-heap
        PriorityQueue<Long>[] dist = new PriorityQueue[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = new PriorityQueue<>(Comparator.reverseOrder()); // max-heap
        }

        List<Long> ans = new ArrayList<>();

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int node = (int) curr[0];
            long cost = curr[1];

            // skip if we already have k better paths
            if (dist[node].size() == k && dist[node].peek() <= cost) continue;

            // record this path
            dist[node].add(cost);
            if (dist[node].size() > k) dist[node].poll(); // remove worst

            if (node == n) ans.add(cost);
            if (ans.size() == k) break;

            // relax edges
            for (Edge e : graph[node]) {
                long newCost = cost + e.weight;
                if (dist[e.node].size() < k || dist[e.node].peek() > newCost) {
                    pq.add(new long[]{e.node, newCost});
                }
            }
        }

        Collections.sort(ans); // ensure ascending
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + (i + 1 < ans.size() ? " " : ""));
        }
    }
}
