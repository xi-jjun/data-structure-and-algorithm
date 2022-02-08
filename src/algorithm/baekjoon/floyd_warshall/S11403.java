package floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S11403 {
    static final int INF = 999999999;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] answer = new int[N][N];
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int data = Integer.parseInt(st.nextToken());
                graph[i][j] = data == 0 ? INF : 1;
            }
        }

        floyd(graph, answer);

        for (int[] ans1 : answer) {
            for (int ans : ans1) {
                System.out.print(ans + " ");
            }
            System.out.println("");
        }
    }

    private static void floyd(int[][] graph, int[][] answer) {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer[i][j] = graph[i][j] >= INF ? 0 : 1;
            }
        }
    }
}
