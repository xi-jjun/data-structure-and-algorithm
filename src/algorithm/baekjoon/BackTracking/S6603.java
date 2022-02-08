package BackTracking;

import java.io.*;
import java.util.StringTokenizer;

// baekJoon 6603 Silver2 로또
public class S6603 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] S, ans = new int[6];
    static boolean[] visited;
    static int k;

    static void backTracking(int depth, int at) throws IOException {
        if (depth == 6) {
            for (int a : ans) bw.write(a + " ");
            bw.write("\n");
            return;
        }

        for (int i = at; i < S.length; i++) {
            if (!visited[i]) {
                ans[depth] = S[i];
                visited[i] = true;
                backTracking(depth + 1, i);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) break;

            S = new int[k];
            visited = new boolean[k];
            for (int i = 0; i < k; i++) S[i] = Integer.parseInt(st.nextToken());

            backTracking(0, 0);
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}
