import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Shortest_Route_I {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<Integer, HashMap<Integer, Integer>> graph = new HashMap<>();

        for(int i=1;i<=n;i++){
            graph.put(i, new HashMap<>());
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt((st.nextToken()));
            graph.get(u).put(v, Math.min(graph.get(u).getOrDefault(v, cost), cost));
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.add(new long[]{1, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int node = (int) curr[0];
            long cost = curr[1];

            if (cost > dist[node]) continue;

            for (int nbr : graph.get(node).keySet()) {
                long newDist = cost + graph.get(node).get(nbr);
                if (newDist < dist[nbr]) {
                    dist[nbr] = newDist;
                    pq.add(new long[]{nbr, newDist});
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (dist[i] == Long.MAX_VALUE) System.out.print("-1 ");
            else System.out.print(dist[i] + " ");
        }
    }
}
