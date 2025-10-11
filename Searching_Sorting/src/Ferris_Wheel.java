import java.io.*;
import java.util.*;

public class Ferris_Wheel {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int i = 0, j = n - 1, c = 0;
        while (i <= j) {
            if (arr[i] + arr[j] <= x) i++;
            j--;
            c++;
        }

        bw.write(String.valueOf(c));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
