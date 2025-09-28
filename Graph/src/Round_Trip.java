import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Round_Trip {
    static int cycleStart = -1, cycleEnd = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // adjacency list
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!visited[i] && dfs(adj, i, parent, visited)) {
                break;
            }
        }

        if (cycleStart == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            List<Integer> cycle = new ArrayList<>();
            cycle.add(cycleStart);
            for (int v = cycleEnd; v != cycleStart; v = parent[v]) {
                cycle.add(v);
            }
            cycle.add(cycleStart);

            Collections.reverse(cycle);
            System.out.println(cycle.size());
            for (int x : cycle) {
                System.out.print(x + " ");
            }
        }
    }

    private static boolean dfs(List<Integer>[] adj, int src, int[] parent, boolean[] visited) {
        visited[src] = true;

        for (int nbr : adj[src]) {
            if (!visited[nbr]) {
                parent[nbr] = src;
                if (dfs(adj, nbr, parent, visited)) return true;
            } else if (nbr != parent[src]) {
                cycleStart = nbr;
                cycleEnd = src;
                return true;
            }
        }
        return false;
    }
}
