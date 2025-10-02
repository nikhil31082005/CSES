import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Investigation {
    static int MOD = 1000000007;
    static class Edge {
        int node;
        long wt;

        public Edge(int node, long wt) {
            this.wt = wt;
            this.node = node;
        }
    }

    static class Pair {
        int node;
        long wt;

        public Pair(int node, long wt) {
            this.node = node;
            this.wt = wt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new List[n+1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for(int i=0;i<m;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(s.nextToken());
            int v = Integer.parseInt(s.nextToken());
            int wt = Integer.parseInt(s.nextToken());

            graph[u].add(new Edge(v, wt));
        }

        long[] dis = new long[n+1];
        int[] ways = new int[n+1];
        int[] minFlight = new int[n+1];
        int[] maxFlight = new int[n+1];

        Arrays.fill(dis, Long.MAX_VALUE);
        Arrays.fill(minFlight, Integer.MAX_VALUE);
        Arrays.fill(maxFlight, Integer.MIN_VALUE);

        dis[1] = 0;
        ways[1] = 1;
        minFlight[1] = 0;
        maxFlight[1] = 1;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.wt, b.wt));
        pq.add(new Pair(1, 0));

        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            int node = p.node;
            long wt = p.wt;

            if(wt > dis[node]) {
                continue;
            }

            for(Edge nbr: graph[node]) {
                int newNode = nbr.node;
                long newWt = nbr.wt;

                if(dis[node] + newWt < dis[newNode]) {
                    dis[newNode] = dis[node] + newWt;
                    ways[newNode] = ways[node] % MOD;
                    minFlight[newNode] = minFlight[node] + 1;
                    maxFlight[newNode] = maxFlight[node] + 1;
                    pq.add(new Pair(newNode, dis[newNode]));
                }
                else if(dis[node] + newWt == dis[newNode]) {
                    ways[newNode] = (ways[newNode] + ways[node]) % MOD;
                    minFlight[newNode] = Math.min(minFlight[newNode], minFlight[node] + 1);
                    maxFlight[newNode] = Math.max(maxFlight[newNode], maxFlight[node] + 1);
                }
            }
        }
        System.out.println(dis[n] + " " + ways[n] + " " + minFlight[n] + " " + maxFlight[n]);
    }
}
