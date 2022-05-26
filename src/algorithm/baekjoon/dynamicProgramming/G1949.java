package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G1949 {
    static int N;
    static int[] people;
    static int[][] dp;
    static boolean[] visited;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        people = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; st.hasMoreTokens(); i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        visited = new boolean[N + 1];
        dp = new int[N + 1][2];

        find(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void find(int now) {
        visited[now] = true;
        dp[now][0] = people[now];

        for (Integer child : graph.get(now)) {
            if (visited[child]) continue;
            find(child);

            dp[now][0] += dp[child][1];
            dp[now][1] += Math.max(dp[child][0], dp[child][1]);
        }
    }
}
