import java.util.*;
// https://cses.fi/problemset/task/1668/
public class Building_Teams {
    static List<Integer>[] adj;
    static int[] color; // 0 = unvisited, 1 = team1, 2 = team2

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // number of pupils
        int m = sc.nextInt();  // number of friendships

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        color = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) { // not visited
                if (!bfs(i)) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(color[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static boolean bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 1;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (color[v] == 0) {
                    color[v] = 3 - color[u]; // alternate between 1 and 2
                    q.add(v);
                } else if (color[v] == color[u]) {
                    return false; // same team conflict
                }
            }
        }
        return true;
    }
}
