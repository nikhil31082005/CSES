import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Planets_And_Kingdoms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        List<Integer>[] graph = new List[n+1];
        List<Integer>[] reverseGraph = new List[n+1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(s.nextToken());
            int b = Integer.parseInt(s.nextToken());

            graph[a].add(b);
            reverseGraph[b].add(a);
        }

        boolean[] visited = new boolean[n+1];

        Stack<Integer> st = new Stack<>();

        for(int i=1;i<=n;i++){
            if(!visited[i]) {
                dfs(i, graph, visited, st);
            }
        }

        Arrays.fill(visited, false);

        int scc = 0;

        int[] comp = new int[n+1];

        while(!st.isEmpty()) {
            int node = st.pop();

            if(!visited[node]) {
                scc++;
                dfs2(node, reverseGraph, visited, comp, scc);
            }
        }

        System.out.println(scc);
        for(int i=1;i<=n;i++){
            System.out.print(comp[i] + " ");
        }

    }

    private static void dfs2(int src, List<Integer>[] reverseGraph, boolean[] visited, int[] comp, int scc) {
        visited[src] = true;
        comp[src] = scc;
        for(int nbr: reverseGraph[src]) {
            if(!visited[nbr]) {
                dfs2(nbr, reverseGraph, visited, comp, scc);
            }
        }
    }

    public static void dfs(int src, List<Integer>[] graph, boolean[] visited, Stack<Integer> st) {
        visited[src] = true;

        for(int nbr: graph[src]) {
            if(!visited[nbr]) {
                dfs(nbr, graph, visited, st);
            }
        }

        st.push(src);
    }
}
