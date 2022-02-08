package Math;

import java.io.*;

// baekJoon 1929 silver2 소수 구하기 - class 2+
public class S1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = br.readLine().split(" ");
        int M = Integer.parseInt(line[0]);
        int N = Integer.parseInt(line[1]);

        for (int i = M; i <= N; i++) {
            boolean ok = true;
            if (i == 2) {
                bw.write("2\n");
                continue;
            } else if (i % 2 == 0 || i == 1) continue;

            for (int k = 2; k * k <= i; k++) {
                if (i % k == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) bw.write(i + "\n");
        }

        bw.flush();
        bw.close();
    }
}
