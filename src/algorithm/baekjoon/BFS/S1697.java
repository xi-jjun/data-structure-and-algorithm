package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// baekJoon 1697 silver1 숨박꼭질
// https://chanhuiseok.github.io/posts/baek-14/ 참고
public class S1697 {
    static int result = 0;
    static int N, M;
    static boolean[] visited = new boolean[100001];

    static boolean valid(int location) {
        return location >= 0 && location <= 100000 && !visited[location];
    }

    static void bfs(int start) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start, 0});

        while (!q.isEmpty()) {
            int location = q.peek()[0];
            int depth = q.peek()[1];
            q.poll();

            if (location == M) {
                result = depth;
                return;
            }

            if (valid(location)) {
                visited[location] = true;

                q.offer(new int[]{location - 1, depth + 1});
                q.offer(new int[]{location + 1, depth + 1});
                q.offer(new int[]{location * 2, depth + 1});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        if (N > M) {
            System.out.println((N - M));
            return;
        } else {
            bfs(N);
        }

        System.out.println(result);
    }
}
