import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Monsters {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[] dir = {'D', 'U', 'R', 'L'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] mat = new char[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            mat[i] = s.toCharArray();
        }

        int[][] monsterDist = new int[n][m];
        for (int[] row : monsterDist) Arrays.fill(row, Integer.MAX_VALUE);

        Queue<int[]> monsterQ = new LinkedList<>();
        int sx = -1, sy = -1;

        // collect monsters and player
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 'M') {
                    monsterQ.add(new int[]{i, j});
                    monsterDist[i][j] = 0;
                } else if (mat[i][j] == 'A') {
                    sx = i;
                    sy = j;
                }
            }
        }

        while(!monsterQ.isEmpty()) {
            int[] curr = monsterQ.poll();
            int row = curr[0];
            int col = curr[1];

            for(int i=0;i<4;i++){
                int nx = row + dx[i];
                int ny = col + dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < m && mat[nx][ny] == '.' && monsterDist[nx][ny] == Integer.MAX_VALUE) {
                    monsterDist[nx][ny] = monsterDist[row][col] + 1;
                    monsterQ.add(new int[]{nx, ny});
                }
            }
        }

        boolean[][] visited = new boolean[n][m];
        int[][] parentDir = new int[n][m];  // direction from which we came
        int[][] prevX = new int[n][m];      // previous X
        int[][] prevY = new int[n][m];      // previous Y

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        visited[sx][sy] = true;
        int[][] distA = new int[n][m];
        distA[sx][sy] = 0;
        boolean found = false;
        int ex = -1, ey = -1;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];

            if(row == 0 || col == 0 || row == n-1 || col == m-1) {
                ex = row;
                ey = col;
                found = true;
                break;
            }

            for(int i=0;i<4;i++){
                int nr = row + dx[i];
                int nc = col + dy[i];

                if(nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && mat[nr][nc] == '.') {
                    if(distA[row][col] + 1 < monsterDist[nr][nc]) {
                        distA[nr][nc] = distA[row][col] + 1;
                        visited[nr][nc] = true;
                        parentDir[nr][nc] = i;
                        prevX[nr][nc] = row;
                        prevY[nr][nc] = col;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
        if(!found) {
            System.out.println("NO");
        }
        else {
            System.out.println("YES");

            StringBuilder path = new StringBuilder();
            int x = ex, y = ey;

            while(x != sx || y != sy) {
                int i = parentDir[x][y];
                path.append(dir[i]);
                int px = prevX[x][y];
                int py = prevY[x][y];
                x = px;
                y = py;
            }

            path.reverse();
            System.out.println(path.length());
            System.out.println(path.toString());
        }
    }
}
