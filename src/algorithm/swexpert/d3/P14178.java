package algorithm.swexpert.d3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// sw expert D3 14178 1차원 정원
public class P14178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            if (N <= D) {
                bw.write("#" + tc + " 1\n");
                continue;
            }

            int answer = N / (2 * D + 1);
            if (N % (2 * D + 1) == 0) {
                bw.write("#" + tc + " " + answer + "\n");
            } else {
                bw.write("#" + tc + " " + (answer + 1) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
