package algorithm.baekjoon.floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 14938 Gold4 서강그라운드
public class G14938 {
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] items = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; st.hasMoreTokens(); i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        int[][] distance = new int[N + 1][N + 1];
        distanceReset(N, distance);
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            distance[a][b] = l;
            distance[b][a] = l;
        }

        for (int via = 1; via <= N; via++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    distance[a][b] = Math.min(distance[a][b], distance[a][via] + distance[via][b]);
                }
            }
        }

        int answer = 0;
        for (int area = 1; area <= N; area++) {
            int itemCount = 0;
            int[] minDistance = distance[area];
            for (int i = 1; i <= N; i++) {
                if (minDistance[i] <= M) {
                    itemCount += items[i];
                }
            }
            answer = Math.max(answer, itemCount);
        }

        System.out.println(answer);
    }

    private static void distanceReset(int N, int[][] distance) {
        for (int a = 1; a <= N; a++) {
            for (int b = 1; b <= N; b++) {
                distance[a][b] = a == b ? 0 : INF;
            }
        }
    }
}
