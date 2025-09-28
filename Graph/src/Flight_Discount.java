import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Flight_Discount {
    static class Pair {
        int node;
        long cost;
        int used;

        public Pair(int node, long cost, int used) {
            this.node = node;
            this.cost = cost;
            this.used = used;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]>[] graph = new List[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(s.nextToken());
            int v = Integer.parseInt(s.nextToken());
            int w = Integer.parseInt(s.nextToken());

            graph[u].add(new int[]{v, w});
        }

        long[][] dis = new long[n+1][2];
        for(long[] arr: dis) {
            Arrays.fill(arr, Long.MAX_VALUE);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return Math.toIntExact(a.cost - b.cost);
        });

        pq.add(new Pair(1, 0, 0));
        while(!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node;
            long currCost = curr.cost;
            int used = curr.used;

            if(currCost > dis[node][used]) {
                continue;
            }

            for(int[] arr: graph[node]) {
                int to = arr[0];
                int cost = arr[1];

                if(currCost + cost < dis[to][used]) {
                    dis[to][used] = currCost + cost;
                    pq.add(new Pair(to, dis[to][used], used));
                }

                if(used == 0) {
                    long discounted = currCost + cost / 2;
                    if(discounted < dis[to][1]) {
                        dis[to][1] = discounted;
                        pq.add(new Pair(to, dis[to][1], 1));
                    }
                }
            }
        }

        System.out.println(dis[n][1]);
    }
}
