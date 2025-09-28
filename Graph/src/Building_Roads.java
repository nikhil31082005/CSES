import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Building_Roads {
    static class DSU{
        int[] rank;
        int[] parent;

        public DSU(int n) {
            rank = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int node) {
            if(node == parent[node]) {
                return node;
            }
            return parent[node] = find(parent[node]);
        }
        public void union(int u, int v) {
            int pu = find(u);
            int pv = find(v);

            if(pu == pv) {
                return;
            }

            if(rank[pu] < rank[pv]) {
                parent[pu] = pv;
            }
            else if(rank[pu] > rank[pv]) {
                parent[pv] = pu;
            }
            else {
                parent[pu] = pv;
                rank[pv]++;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        DSU d = new DSU(n + 1);

        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();

            d.union(u, v);
        }

        List<Integer> ls = new ArrayList<>();

        for (int i = 1; i < d.parent.length; i++) {
            if(i == d.find(i)) {
                ls.add(i);
            }
        }

        System.out.println(ls.size()-1);
        for(int i=0;i<ls.size()-1;i++){
            System.out.println(ls.get(i) + " " + ls.get(i+1));
        }
    }
}
