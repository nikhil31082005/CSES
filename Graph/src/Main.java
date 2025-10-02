import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000007;

    static class Edge {
        int node;
        long wt;
        Edge(int node, long wt) {
            this.node = node;
            this.wt = wt;
        }
    }

    static class Pair {
        int node;
        long wt;
        Pair(int node, long wt) {
            this.node = node;
            this.wt = wt;
        }
    }

    // ---- Short & Fast Scanner ----
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;
        FastScanner(InputStream in) { this.in = in; }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, val = 0, sign = 1;
            do { c = readByte(); } while (c <= ' ');
            if (c == '-') { sign = -1; c = readByte(); }
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c, sign = 1;
            long val = 0;
            do { c = readByte(); } while (c <= ' ');
            if (c == '-') { sign = -1; c = readByte(); }
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
    // ------------------------------

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int m = fs.nextInt();

        List<Edge>[] graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            long w = fs.nextLong();
            graph[u].add(new Edge(v, w));
        }

        long[] dis = new long[n+1];
        long[] ways = new long[n+1];
        int[] minFlight = new int[n+1];
        int[] maxFlight = new int[n+1];

        Arrays.fill(dis, Long.MAX_VALUE);
        Arrays.fill(minFlight, Integer.MAX_VALUE);
        Arrays.fill(maxFlight, Integer.MIN_VALUE);

        dis[1] = 0;
        ways[1] = 1;
        minFlight[1] = 0;
        maxFlight[1] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.wt));
        pq.add(new Pair(1, 0));

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            if (p.wt > dis[p.node]) continue;

            for (Edge e : graph[p.node]) {
                long nd = dis[p.node] + e.wt;
                if (nd < dis[e.node]) {
                    dis[e.node] = nd;
                    ways[e.node] = ways[p.node];
                    minFlight[e.node] = minFlight[p.node] + 1;
                    maxFlight[e.node] = maxFlight[p.node] + 1;
                    pq.add(new Pair(e.node, nd));
                } else if (nd == dis[e.node]) {
                    ways[e.node] = (ways[e.node] + ways[p.node]) % MOD;
                    minFlight[e.node] = Math.min(minFlight[e.node], minFlight[p.node] + 1);
                    maxFlight[e.node] = Math.max(maxFlight[e.node], maxFlight[p.node] + 1);
                }
            }
        }
        System.out.println(dis[n] + " " + ways[n] + " " + minFlight[n] + " " + maxFlight[n]);
    }
}
