package algorithm.baekjoon.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// https://yabmoons.tistory.com/291
// dfs 의 종료조건이 주어지지 않았을 때의 케이스
// 이건 나중에 한번 더 다시 풀어봐야할 듯 RE
// baekJoon 17471 Gold4 게리멘더링
public class G17471 {
    static int N, answer, total;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] selectedArea;
    static int[] population;

    public static void main(String[] args) throws IOException {
        getInput();

        solution(1, 0);

        if (answer == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(answer);
        }
    }

    private static void solution(int now, int count) {
        if (count >= 1) {
            if (valid()) {
                answer = Math.min(answer, getResult());
            }
        }

        for (int city = now; city <= N; city++) {
            if (!selectedArea[city]) {
                selectedArea[city] = true;
                solution(city, count + 1);
                selectedArea[city] = false;
            }
        }
    }

    private static int getResult() {
        int sum = 0;
        for (int city = 1; city <= N; city++) {
            if (selectedArea[city]) sum += population[city];
        }
        return Math.abs(total - 2 * sum);
    }

    /**
     * 각 선거구가 다음과 같은 조건을 만족할 경우, valid 하다고 할 수 있다.
     * 1. 각 선거구는 적어도 1개 이상의 구역을 보유
     * 2. 각 선거구의 구역들은 모두 이어져 있다.
     *
     * @return 조건 모두 만족할 경우에만 true
     */
    private static boolean valid() {
        List<Integer> electionDistrictA = new ArrayList<>();
        List<Integer> electionDistrictB = new ArrayList<>();
        for (int city = 1; city <= N; city++) {
            if (selectedArea[city]) electionDistrictA.add(city);
            else electionDistrictB.add(city);
        }

        // 1. 각 선거구는 적어도 1개 이상의 구역을 보유해야 한다.
        if (electionDistrictA.size() == 0 || electionDistrictB.size() == 0) {
            return false;
        }

        return isConnected(electionDistrictA, true) && isConnected(electionDistrictB, false);
    }

    private static boolean isConnected(List<Integer> district, boolean type) {
        Set<Integer> isDistrict = new HashSet<>(district);
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(district.get(0));
        visited[district.get(0)] = true;
        int connectedCnt = 1;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 1; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i);

                // selected == true => A, else B
                if (!visited[next] && selectedArea[next] == type && isDistrict.contains(next)) {
                    q.offer(next);
                    visited[next] = true;
                    connectedCnt++;
                }
            }
        }

        return connectedCnt == district.size();
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        selectedArea = new boolean[N + 1];
        answer = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        total = 0;
        for (int i = 1; st.hasMoreTokens(); i++) {
            population[i] = Integer.parseInt(st.nextToken());
            total += population[i];
        }

        for (int area = 0; area <= N; area++) {
            graph.add(new ArrayList<>());
        }

        for (int area = 1; area <= N; area++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            for (int i = 0; i < K; i++) {
                int adjacentArea = Integer.parseInt(st.nextToken());
                graph.get(area).add(adjacentArea);
                graph.get(adjacentArea).add(area);
            }
        }
    }
}
