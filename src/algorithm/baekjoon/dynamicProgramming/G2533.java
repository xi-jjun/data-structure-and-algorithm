package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://hqjang.tistory.com/104 참고
// baekJoon 2533 Gold3 - 사회장 서비스(SNS)
public class G2533 {
    static int N;
    static int[][] dp;
    static boolean[] visited;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        find(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void find(int node) {
        visited[node] = true;
        dp[node][0] = 1;

        for (Integer child : graph.get(node)) {
            if (visited[child]) continue;
            find(child);

            dp[node][0] += Math.min(dp[child][0], dp[child][1]);
            dp[node][1] += dp[child][0];
        }
    }
}
