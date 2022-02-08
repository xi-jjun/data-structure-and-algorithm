package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 2579 silver3 계단 오르기
// backTracking time out
public class TIMEOUTS2579 {
    static int N;
    static int max = -1;
    static int[] stairs;
    static int[] score;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stairs = new int[N + 1];
        score = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        backTracking(1, 0, 1);

        System.out.println(max);
    }

    static boolean isValid(int loc) {
        return loc<=N && !visited[loc];
    }

    private static void backTracking(int location, int seqCount, int index) {
        if (location == N) {
            score[index] = stairs[N];
            int sum = 0;
            for (int i = 1; i <= index; i++) {
                if(score[i]==0) return;
                sum += score[i];
            }
            max = Math.max(sum, max);
            return;
        }

        score[index] = stairs[location];

        if (isValid(location + 1) && seqCount < 1) {
            visited[location + 1] = true;
            backTracking(location + 1, seqCount + 1, index + 1);
            visited[location + 1] = false;
        }

        if (isValid(location + 2)) {
            visited[location + 2] = true;
            backTracking(location + 2, 0, index + 1);
            visited[location + 2] = false;
        }
    }
}
