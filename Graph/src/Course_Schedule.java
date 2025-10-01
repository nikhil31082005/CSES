import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Course_Schedule {
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

        if(ls.size() != n) {
            System.out.println("IMPOSSIBLE");
        }
        else {
            for(int ele: ls) {
                System.out.print(ele + " ");
            }
        }
    }
}
