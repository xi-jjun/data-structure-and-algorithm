package algorithm.baekjoon.floyd_warshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class G1719 {
    static final int INF = 100000000;
    static int n, m;
    static int[][] answer;
    static int[][] graph;
//    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        answer = new int[n][n];
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    graph[i][j] = INF;
                    answer[i][j] = j;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[start - 1][end - 1] = w;
            graph[end - 1][start - 1] = w;
        }

        floyd();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) bw.write("- ");
                else bw.write((answer[i][j] + 1) + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    private static void floyd() {
        for (int via = 0; via < n; via++) {
            for (int start = 0; start < n; start++) {
                for (int end = 0; end < n; end++) {
                    if (start == end) continue;
                    if (graph[start][via] + graph[via][end] < graph[start][end]) {
                        answer[start][end] = getFirstMeetNodeNumber(via, start);
                        graph[start][end] = graph[start][via] + graph[via][end];
                    }
                }
            }
        }
    }

    private static int getFirstMeetNodeNumber(int via, int start) {
        int ret = via;
        while (answer[start][ret] != ret) {
            ret = answer[start][ret]; // ret : start-ret 사이 start 의 neighbor
        }

        return ret;
    }
}

