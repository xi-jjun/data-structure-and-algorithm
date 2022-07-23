package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class G17070 {
    static final int WALL = 1;
    static final int[] dx = {0, 1, 1}; // → ↘ ↓
    static final int[] dy = {1, 1, 0};
    static int N, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        getInput();

//        bfs(); // 시간 초과 발생
        Pos startTail = new Pos(0, 0);
        Pos startHead = new Pos(0, 1);
        dfs(new Pipe(startTail, startHead));

        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void dfs(Pipe now) {
        Pos head = now.head;
        if (isEnd(head)) {
            answer++;
            return;
        }

        int cannot = getCannotGoInfo(now);

        for (int d = 0; d < 3; d++) {
            if (d == cannot) continue;

            int nextHeadX = head.x + dx[d];
            int nextHeadY = head.y + dy[d];
            Pos nextHead = new Pos(nextHeadX, nextHeadY);

            if (inRange(nextHeadX, nextHeadY) && enoughSpace(nextHead, d)) {
                dfs(new Pipe(head, nextHead));
            }
        }
    }

    private static int getCannotGoInfo(Pipe now) {
        if (now.isRow()) {
            return 2;
        } else if (now.isCol()) {
            return 0;
        }

        return -1;
    }

    private static void bfs() {
        Pos startTail = new Pos(0, 0);
        Pos startHead = new Pos(0, 1);
        Queue<Pipe> q = new ArrayDeque<>();
        q.offer(new Pipe(startTail, startHead));

        while (!q.isEmpty()) {
            Pipe curr = q.poll();
            Pos head = curr.head;

            if (isEnd(head)) {
                answer++;
                continue;
            }

            for (int d = 0; d < 3; d++) {
                if (curr.isRow()) {
                    if (d == 2) continue;
                } else if (curr.isCol()) {
                    if (d == 0) continue;
                }
                goNextPosition(q, head, d);
            }
        }
    }

    // → ↘ ↓
    private static void goNextPosition(Queue<Pipe> q, Pos head, int d) {
        int nextHeadX = head.x + dx[d];
        int nextHeadY = head.y + dy[d];
        Pos nextHead = new Pos(nextHeadX, nextHeadY);

        if (inRange(nextHeadX, nextHeadY) && enoughSpace(nextHead, d)) {
            q.offer(new Pipe(head, nextHead));
        }
    }

    private static boolean enoughSpace(Pos next, int direction) {
        if (direction == 1) {
            int beforeX = next.x - dx[direction];
            int beforeY = next.y - dy[direction];
            return map[next.x][beforeY] == 0 && map[beforeX][next.y] == 0 && map[next.x][next.y] == 0;
        }

        return map[next.x][next.y] != WALL;
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= N);
    }

    private static boolean isEnd(Pos pos) {
        return pos.x == N - 1 && pos.y == N - 1;
    }

    static class Pipe {
        Pos tail;
        Pos head;

        public Pipe(Pos tail, Pos head) {
            this.tail = tail;
            this.head = head;
        }

        public boolean isRow() {
            return tail.x == head.x && tail.y != head.y;
        }

        public boolean isCol() {
            return tail.x != head.x && tail.y == head.y;
        }
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
