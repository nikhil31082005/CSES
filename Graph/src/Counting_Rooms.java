import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// https://cses.fi/problemset/task/1192
public class Counting_Rooms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine(); // consume the rest of the line
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            map[i] = line.toCharArray();
        }

        // change char map into int[] array where # mean 1 and . mean 0
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '#') {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = 0;
                }
            }
        }



        System.out.println(countRooms(arr, n, m));
    }

    private static int countRooms(int[][] arr, int n, int m) {
        int count = 0;
        boolean[][] visited = new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && arr[i][j] == 0) {
                    bfs(arr, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(int[][] arr, boolean[][] visited, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});

        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        visited[i][j] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];

            for(int k=0;k<4;k++){
                int nr = dx[k] + row;
                int nc = dy[k] + col;

                if(nr >= 0 && nr < arr.length && nc >= 0 && nc < arr[0].length && !visited[nr][nc] && arr[nr][nc] == 0){
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }
}
