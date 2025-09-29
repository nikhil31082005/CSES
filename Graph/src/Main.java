import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) {
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

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            StringTokenizer s = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(s.nextToken());
            int v = Integer.parseInt(s.nextToken());
            int w = Integer.parseInt(s.nextToken());
            edges.add(new Edge(u, v, w));
        }

        long[] dist = new long[n+1];
        int[] parent = new int[n+1];
        Arrays.fill(dist, 0);
        Arrays.fill(parent, -1);
        int x = -1;

        for (int i = 1; i <= n; i++) {
            x = -1;
            for (Edge e : edges) {
                if (dist[e.u] + e.w < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.w;
                    parent[e.v] = e.u;
                    x = e.v;
                }
            }
        }

        if(x == -1) {
            System.out.println("NO");
        }
        else {
            System.out.println("YES");
//            System.out.println(x);

            for(int i=0;i<n;i++){
                x = parent[x];
            }

            List<Integer> ls = new ArrayList<>();

            int a = x;
            while(parent[a] != x) {
                ls.add(a);
                a = parent[a];
            }
            ls.add(0, a);
            ls.add(a);

            for(int i=ls.size()-1;i>=0;i--){
                System.out.print(ls.get(i) + " ");
            }

//            List<Integer> ls = new ArrayList<>();
//            int a = x;
//
//            while(parent[a] != x) {
//                ls.add(a);
//                a = parent[a];
//            }
//            ls.add(a);
//            System.out.println(Arrays.toString(parent));
        }
    }
}
