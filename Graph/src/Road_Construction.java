import java.io.*;
import java.util.*;

public class Road_Construction {
    static class DSU {
        int[] parent, size;

        DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (x != parent[x]) parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;

            if (size[pa] < size[pb]) { // attach smaller to larger
                parent[pa] = pb;
                size[pb] += size[pa];
            } else {
                parent[pb] = pa;
                size[pa] += size[pb];
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        DSU dsu = new DSU(n);
        int components = n;
        int maxSize = 1;

        for (int i = 0; i < m; i++) {
            StringTokenizer s = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(s.nextToken());
            int b = Integer.parseInt(s.nextToken());

            if (dsu.union(a, b)) {
                components--;
                int root = dsu.find(a);
                maxSize = Math.max(maxSize, dsu.size[root]);
            }

            System.out.println(components + " " + maxSize);
        }
    }
}
