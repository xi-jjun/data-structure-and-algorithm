package BackTracking;

import java.io.*;

// baekJoon 15652 silver3 N과 M - 4
public class S15652 {
    static int[] arr = new int[10];
    static int N, M;
    static boolean[] visited = new boolean[10];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void backTracking(int depth) throws IOException {
        if (depth == M) {
            for (int i = 0; i < M - 1; i++) {
                if (arr[i] > arr[i + 1]) return;
            }
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
