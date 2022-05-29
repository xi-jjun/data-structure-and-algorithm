package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 암만 생각해도 문제 헷갈림..;;
 * 늑대는 '일생동안 한 마리의 양을 먹는다'...
 * https://loosie.tistory.com/492
 * baekJoon 16437 Gold2 - 양 구출 작전
 */
public class G16437 {
    static int N;
    static int[] animals;
    static long[] dp;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        animals = new int[N + 1];
        dp = new long[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 2; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            int count = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            graph[dest].add(i);
            dp[i] = type == 'W' ? -count : count;
        }

        dfs(1, -1);

        System.out.println(dp[1]);
    }

    private static void dfs(int now, int ex) {
        for (Integer next : graph[now]) {
            dfs(next, now);
        }

        if (ex != -1) {
            if (dp[now] > 0) {
                dp[ex] += dp[now];
            }
        }
    }
}
