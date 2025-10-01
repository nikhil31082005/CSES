import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Longest_Flight_Route {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n+1];
        for(int i=0;i<m;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(s.nextToken());
            int b = Integer.parseInt(s.nextToken());
            graph.get(a).add(b);
            indegree[b]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<n+1;i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> ls = new ArrayList<>();

        while(!q.isEmpty()) {
            int node = q.poll();

            ls.add(node);

            for(int nbr: graph.get(node)) {
                indegree[nbr]--;
                if(indegree[nbr] == 0) {
                    q.add(nbr);
                }
            }
        }

        int[] dis = new int[n+1];
        Arrays.fill(dis, Integer.MIN_VALUE);
        int[] parent = new int[n+1];
        Arrays.fill(parent, -1);

        dis[1] = 1;
        parent[1] = 0;

        for(int ele: ls) {
            if(dis[ele] == Integer.MIN_VALUE) {
                continue;
            }
            for(int nbr: graph.get(ele)) {
                if(dis[ele] + 1 > dis[nbr]) {
                    dis[nbr] = dis[ele] + 1;
                    parent[nbr] = ele;
                }
            }
        }

        if(dis[n] == Integer.MIN_VALUE) {
            System.out.println("IMPOSSIBLE");
        }
        else {
            List<Integer> path = new ArrayList<>();
            int curr = n;
            while(curr != 0) {
                path.add(curr);
                curr = parent[curr];
            }
            Collections.reverse(path);
            System.out.println(path.size());
            for(int ele: path) {
                System.out.print( ele + " ");
            }
        }
    }
}
