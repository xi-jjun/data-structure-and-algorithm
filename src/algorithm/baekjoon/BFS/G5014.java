package algorithm.baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class G5014 {
    static int F, S, G, U, D;
    static int[] button = new int[2];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        F = Integer.parseInt(input[0]);
        S = Integer.parseInt(input[1]);
        G = Integer.parseInt(input[2]);
        U = Integer.parseInt(input[3]);
        D = Integer.parseInt(input[4]);

        button[0] = U;
        button[1] = -D;
        visited = new boolean[F + 1];

        int answer = bfs(S);

        if (answer == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(answer);
        }
    }

    private static int bfs(int start) {
        visited[start] = true;
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));

        while (!pq.isEmpty()) {
            Pos curr = pq.poll();
            int nowFloor = curr.floor;
            int nowCnt = curr.count;

            if (nowFloor == G) {
                return nowCnt;
            }

            for (int i = 0; i < 2; i++) {
                int nextFloor = nowFloor + button[i];

                if (valid(nextFloor)) {
                    visited[nextFloor] = true;
                    pq.offer(new Pos(nextFloor, nowCnt + 1));
                }
            }
        }
        return -1;
    }

    private static boolean valid(int floor) {
        return inRange(floor) && !visited[floor];
    }

    private static boolean inRange(int floor) {
        return 1 <= floor && floor <= F;
    }

    static class Pos implements Comparable<Pos> {
        int floor;
        int count;

        public Pos(int floor, int count) {
            this.floor = floor;
            this.count = count;
        }

        @Override
        public int compareTo(Pos o) {
            return this.count - o.count;
        }
    }

}
