import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = fs.nextInt();
        int x = fs.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextInt();
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
    }

    // ⚙️ Ultra-fast input reader for CSES-style large inputs
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16]; // 64KB buffer
        private int ptr = 0, len = 0;
        private final InputStream in = System.in;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
            } while (c <= ' '); // skip whitespace
            if (c == '-') {
                sign = -1;
                c = readByte();
            }
            while (c > ' ') {
                val = val * 10 + c - '0';
                c = readByte();
            }
            return val * sign;
        }
    }
}
