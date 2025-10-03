import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Road_Reparation {
    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n+1];
            rank = new int[n+1];
            for(int i=1;i<=n;i++){
                parent[i] = i;
            }
        }

        public int find(int node) {
            if(node == parent[node]) {
                return node;
            }
            return parent[node] = find(parent[node]);
        }

        public boolean union(int u, int v) {
            int pu = find(u);
            int pv = find(v);

            if(pu == pv) {
                return false;
            }

            if(rank[pu] > rank[pv]) {
                parent[pv] = pu;
            }
            else if(rank[pu] < rank[pv]) {
                parent[pu] = pv;
            }
            else {
                parent[pu] = pv;
                rank[pv]++;
            }
            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]> ls = new ArrayList<>();

        for(int i=0;i<m;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(s.nextToken());
            int v = Integer.parseInt(s.nextToken());
            int wt = Integer.parseInt(s.nextToken());

            ls.add(new int[]{u, v, wt});
        }

        Collections.sort(ls, (a, b) -> {
            return a[2] - b[2];
        });

        DSU d = new DSU(n);
        long cost = 0;
        int edgeUsed = 0;
        for(int[] arr: ls) {
            if(d.union(arr[0], arr[1])) {
                cost += arr[2];
                edgeUsed++;
            }
        }

        if(edgeUsed != n-1) {
            System.out.println("IMPOSSIBLE");
        }
        else {
            System.out.println(cost);
        }
    }
}
