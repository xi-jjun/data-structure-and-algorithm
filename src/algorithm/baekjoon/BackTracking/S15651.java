package BackTracking;

import java.io.*;

// baekJoon 15651 silver3 N과 M - 3
public class S15651 {
    static int N, M;
    static int[] arr = new int[10];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void backTracking(int depth) throws IOException {
        if (depth == M) {
            for (int i = 0; i < M; i++) bw.write(arr[i] + " ");
            bw.write("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            arr[depth] = i;
            backTracking(depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        backTracking(0);

        bw.flush();
        bw.close();
    }
}
