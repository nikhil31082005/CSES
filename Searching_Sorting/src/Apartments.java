import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Apartments {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] applicant = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            applicant[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int[] size = new int[m];

        for(int i=0;i<m;i++){
            size[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(applicant);
        Arrays.sort(size);

        int i = 0;
        int j = 0;
        int c = 0;

        while(i < n && j < m) {
            if(size[j] > applicant[i] + k) {
                i++;
            }
            else if(size[j] < applicant[i] - k) {
                j++;
            }
            else {
                i++;
                j++;
                c++;
            }
        }
        System.out.println(c);
    }
}
