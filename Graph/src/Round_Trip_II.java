import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Round_Trip_II {
    static int cycleStart = -1, cycleEnd = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(s.nextToken());
            int b = Integer.parseInt(s.nextToken());

            graph.get(a).add(b);
        }

        int[] pathVisited = new int[n+1];
        boolean[] visited = new boolean[n+1];

        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        boolean found = false;

        for(int i=1;i<=n;i++){
            if(!visited[i] && dfs(graph, i, pathVisited, visited, parent)) {
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

    private static boolean dfs(List<List<Integer>> graph, int src, int[] pathVisited, boolean[] visited, int[] parent) {
        pathVisited[src] = 1;
        visited[src] = true;

        for(int nbr: graph.get(src)) {
            if(!visited[nbr]) {
                parent[nbr] = src;
                if(dfs(graph, nbr, pathVisited, visited, parent)) {
                    return true;
                }
            }
            else if(pathVisited[nbr] == 1) {
                cycleStart = nbr;
                cycleEnd = src;
                return true;
            }
        }
        pathVisited[src] = 0;
        return false;
    }
}
