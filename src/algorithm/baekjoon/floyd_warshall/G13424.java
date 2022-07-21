package algorithm.baekjoon.floyd_warshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class G13424 {
    static final int INF = 100000000;
    static int N, M;
    static int minRoomNumber;
    static int minDistance;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dist = new int[N + 1][N + 1];

            initDistanceToINF();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                dist[a][b] = c;
                dist[b][a] = c;
            }

            floydWarshall(dist);

            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] friends = new int[K];
            for (int i = 0; st.hasMoreTokens(); i++) {
                friends[i] = Integer.parseInt(st.nextToken());
            }

            minDistance = Integer.MAX_VALUE;
            for (int std = 1; std < N + 1; std++) {
                int totalDistance = 0;
                for (int friend : friends) {
                    totalDistance += dist[friend][std];
                }

                if (minDistance > totalDistance) {
                    minDistance = totalDistance;
                    minRoomNumber = std;
                }
            }

            bw.write(minRoomNumber + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void floydWarshall(int[][] dist) {
        for (int via = 1; via < N + 1; via++) {
            for (int a = 1; a < N + 1; a++) {
                for (int b = 1; b < N + 1; b++) {
                    dist[a][b] = Math.min(dist[a][b], dist[a][via] + dist[via][b]);
                }
            }
        }
    }

    private static void initDistanceToINF() {
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                dist[i][j] = i == j ? 0 : INF;
            }
        }
    }
}
