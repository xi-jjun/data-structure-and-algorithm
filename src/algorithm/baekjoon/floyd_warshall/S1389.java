package floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// baekJoon 1389 silver1 케빈 베이컨의 6단계 법칙
// Floyd-Warshall https://chanhuiseok.github.io/posts/algo-50/ 참고
public class S1389 {
    static final int INF = 100000000;
    static int N, M;

    static void floyd(int[][] graph) { // time complexity O(N^3)
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (i == j) continue;
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // user number
        M = Integer.parseInt(st.nextToken()); // relation
        int[][] graph = new int[N + 1][N + 1];

        reset(graph);

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A][B] = 1;
            graph[B][A] = 1;
        }

        floyd(graph);
        int min = 10000000;
        int minIdx = 0;

        for (int i = 1; i < N + 1; i++) {
            int sum = 0;
            for (int j = 1; j < N + 1; j++) {
                if (i == j) continue;
                sum += graph[i][j];
            }
            if (min > sum) {
                minIdx = i;
                min = sum;
            }
        }

        System.out.println(minIdx);
    }

    private static void reset(int[][] graph) {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) continue;
                graph[i][j] = INF;
            }
        }
    }
}
