import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class High_Score {
    static class Pair {
        int u, v;
        long w;
        Pair(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Pair> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            StringTokenizer s = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(s.nextToken());
            int v = Integer.parseInt(s.nextToken());
            long w = Long.parseLong(s.nextToken());
            edges.add(new Pair(u, v, -w)); // negate for max path
        }

        long INF = (long) 1e15;
        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        // Relax edges n-1 times
        for (int i = 1; i <= n - 1; i++) {
            for (Pair e : edges) {
                if (dist[e.u] != INF && dist[e.u] + e.w < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.w;
                }
            }
        }

        // Check for negative cycle
        boolean[] affected = new boolean[n+1];
        for (Pair e : edges) {
            if (dist[e.u] != INF && dist[e.u] + e.w < dist[e.v]) {
                affected[e.v] = true;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=n;i++){
            if(affected[i]) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int node = q.poll();
            for(Pair e: edges) {
                if(e.u == node && !affected[e.v]) {
                    affected[e.v] = true;
                    q.add(e.v);
                }
            }
        }

        if(affected[n]) {
            System.out.println(-1);
        }
        else {
            System.out.println(-dist[n]);
        }
    }
}
