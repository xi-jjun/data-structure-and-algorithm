package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class S1446 {
    static int N, D;
    static int[] dp;
    static List<Node> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        dp = new int[D + 1];

        for (int i = 0; i < D + 1; i++) {
            dp[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (to - from > weight && to <= D) {
                graph.add(new Node(from, to, weight));
            }
        }

        graph.sort(Comparator.comparingInt(o -> o.from));

        int index = 0;
        int nowLocation = 0;

        while (nowLocation < D) {
            while (index < graph.size()) {
                if (graph.get(index).from != nowLocation) break; // 아까 위에서 시작점을 기준으로 오름차순으로 정리했기에 아니면 다음번에 나올 것이다.

                int dest = graph.get(index).to;
                int nowWeight = graph.get(index).weight;

                if (dest <= D) {
                    int nowToMin = dp[nowLocation] + nowWeight; // 현재까지온 거리 + dest 까지 갈 가리
                    if (nowToMin < dp[dest]) { // dp[dest] : 0 → dest 거리
                        dp[dest] = nowToMin;
                    }
                }
                index++;
            }

            if (dp[nowLocation] + 1 < dp[nowLocation + 1]) {
                dp[nowLocation + 1] = dp[nowLocation] + 1;
            }

            nowLocation++;
        }

        System.out.println(dp[D]);
    }


    static class Node {
        int from;
        int to;
        int weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
