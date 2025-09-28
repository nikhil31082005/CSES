import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
// https://cses.fi/problemset/task/1193/
public class Labyrinth {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[] dir = {'D', 'U', 'R', 'L'};
    public static class Pair{
        int row;
        int col;
        int step;
        StringBuilder sb;

        public Pair(int row, int col, int step, StringBuilder sb) {
            this.row = row;
            this.col = col;
            this.step = step;
            this.sb = sb;
        }
    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        scanner.nextLine(); // consume the rest of the line
//        char[][] map = new char[n][m];
//        for (int i = 0; i < n; i++) {
//            String line = scanner.nextLine();
//            map[i] = line.toCharArray();
//        }
//
//        // change char map into int[] array where # mean 1 and . mean 0
//        int[][] arr = new int[n][m];
//        int startRow = 0, startCol = 0;
//        int endRow = 0, endCol = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if(map[i][j] == 'A') {
//                    startRow = i;
//                    startCol = j;
//                }
//                if(map[i][j] == 'B') {
//                    endRow = i;
//                    endCol = j;
//                }
//                if (map[i][j] == '#') {
//                    arr[i][j] = 1;
//                } else {
//                    arr[i][j] = 0;
//                }
//            }
//        }
//
//        shortestPath(arr, startRow, startCol, endRow, endCol);
//
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        char[][] grid = new char[n][m];
        boolean[][] visited = new boolean[n][m];
        int[][] parentDir = new int[n][m];  // direction from which we came
        int[][] prevX = new int[n][m];      // previous X
        int[][] prevY = new int[n][m];      // previous Y

        int sx = 0, sy = 0, ex = 0, ey = 0;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'A') {
                    sx = i;
                    sy = j;
                }
                if (grid[i][j] == 'B') {
                    ex = i;
                    ey = j;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        visited[sx][sy] = true;

        boolean found = false;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            if (x == ex && y == ey) {
                found = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && grid[nx][ny] != '#') {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    parentDir[nx][ny] = i;
                    prevX[nx][ny] = x;
                    prevY[nx][ny] = y;
                }
            }
        }

        if (!found) {
            System.out.println("NO");
        } else {
            System.out.println("YES");

            // Reconstruct path from B to A
            StringBuilder path = new StringBuilder();
            int x = ex, y = ey;

            while (x != sx || y != sy) {
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
    private static void shortestPath(int[][] arr, int startRow, int startCol, int endRow, int endCol) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return a.step - b.step;
        });
        pq.add(new Pair(startRow, startCol, 0, new StringBuilder()));

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        char[] ch = {'L', 'R', 'U', 'D'};

        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            int row = p.row;
            int col = p.col;
            int step = p.step;
            StringBuilder sb = p.sb;

            if(row == endRow && col == endCol) {
                System.out.println("YES");
                System.out.println(step);
                System.out.println(sb.toString());
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = dx[i] + row;
                int nc = dy[i] + col;
                if(nr >= 0 && nr < arr.length && nc >= 0 && nc < arr[0].length && arr[nr][nc] == 0) {
                    StringBuilder sb1 = new StringBuilder(sb);
                    sb1.append(ch[i]);
                    pq.add(new Pair(nr, nc, step + 1, sb1));
                }
            }
        }
    }
}
