import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Flight_Route_Check {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

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

        dfs(1, graph, visited);

        for(int i=1;i<=n;i++){
            if(!visited[i]) {
                System.out.println("NO");
                System.out.println(1 + " " + i);
                return;
            }
        }

        Arrays.fill(visited, false);

        dfs(1, reverseGraph, visited);

        for(int i=1;i<=n;i++){
            if(!visited[i]) {
                System.out.println("NO");
                System.out.println(i + " " + 1);
                return;
            }
        }

        System.out.println("YES");


    }

    public static void dfs(int src, List<Integer>[] graph, boolean[] visited) {
        visited[src] = true;
        for(int nbr: graph[src]) {
            if(!visited[nbr]) {
                dfs(nbr, graph, visited);
            }
        }
    }
}
