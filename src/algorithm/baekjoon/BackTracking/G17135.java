package algorithm.baekjoon.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G17135 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static final int MAX_ARCHER_NUM = 3;
    static final int KILLED = 0;
    static final int ENEMY = 1;
    static final int ARCHER = 2;
    static final int CASTLE = 3;
    static int N, M, D, answer, killCount;
    static int[] archers = new int[3];
    static boolean[] isPlaced;
    static int[][] gameMap;
    static int[][] MAP;
    static boolean[][] visited;
    static boolean[][] attack;
    static Pos archer;

    public static void main(String[] args) throws IOException {
        getInput();

        chooseArchersPlace(0, 0);

        System.out.println(answer);
    }

    private static void resetMap() {
        for (int i = 0; i < N; i++) { // N 번째 줄은 궁수 정보가 있어서 없애면 안됨
            gameMap[i] = MAP[i].clone();
        }
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        isPlaced = new boolean[M];
        gameMap = new int[N + 1][M];
        MAP = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                gameMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.fill(gameMap[N], CASTLE); // 마지막 줄은 castle 이고, 해당 줄에는 궁수가 존재한다.
        for (int i = 0; i <= N; i++) {
            MAP[i] = gameMap[i].clone();
        }
    }

    private static void chooseArchersPlace(int depth, int at) {
        if (depth == MAX_ARCHER_NUM) {
            resetMap();
            killCount = 0;
            simulation();
            answer = Math.max(killCount, answer);
            return;
        }

        for (int i = at; i < M; i++) {
            if (!isPlaced[i]) {
                isPlaced[i] = true;
                gameMap[N][i] = ARCHER;
                archers[depth] = i; // depth 번째 궁수가 성의 왼쪽으로부터 row=N col=i 번째에 위치
                chooseArchersPlace(depth + 1, i);
                gameMap[N][i] = CASTLE;
                isPlaced[i] = false;
            }
        }
    }

    private static boolean checkEnemyExisted() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (gameMap[i][j] == ENEMY) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void simulation() {
        while (checkEnemyExisted()) {
            checkToBeAttackedEnemy();

            killEnemy();

            moveDownEnemy();
        }
    }

    private static void showMap() {
        for (int[] ints : gameMap) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    private static void moveDownEnemy() {
        List<Queue<Integer>> rows = getRowsSpace();

        readyToMove(rows);

        move(rows);
    }

    private static void move(List<Queue<Integer>> rows) {
        for (int i = 0; i < N - 1; i++) {
            int col = 0;
            Queue<Integer> row = rows.get(i);
            while (!row.isEmpty()) {
                gameMap[i + 1][col++] = row.poll();
            }
        }
    }

    private static void readyToMove(List<Queue<Integer>> rows) {
        for (int i = 0; i < N - 1; i++) { // 마지막 줄은 어차피 성으로 도착하게 돼서 적이 없어짐
            for (int j = 0; j < M; j++) {
                rows.get(i).offer(gameMap[i][j]);
            }
        }

        // 1번째 줄은 적이 내려가면 아무것도 존재하지 않게 해야함
        for (int i = 0; i < M; i++) {
            gameMap[0][i] = 0;
        }
    }

    private static List<Queue<Integer>> getRowsSpace() {
        List<Queue<Integer>> rows = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            rows.add(new ArrayDeque<>());
        }
        return rows;
    }

    private static void killEnemy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (attack[i][j]) {
                    ++killCount;
                    gameMap[i][j] = KILLED;
                }
            }
        }
    }

    private static void checkToBeAttackedEnemy() {
        attack = new boolean[N][M];
        for (int place = 0; place < M; place++) {
            if (gameMap[N][place] == ARCHER) {
                visited = new boolean[N][M];
                if (gameMap[N - 1][place] == ENEMY) {
                    attack[N - 1][place] = true; // kill
                    continue;
                }
                checkAttack(N, place);
            }
        }
    }

    private static void checkAttack(int startX, int startY) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        Pos archerPos = new Pos(startX, startY, 0);
        archer = archerPos;
        pq.offer(archerPos);
        PriorityQueue<Pos> result = new PriorityQueue<>();

        while (!pq.isEmpty()) {
            Pos curr = pq.poll();
            int nowX = curr.x;
            int nowY = curr.y;
            int nowDistance = curr.distance;

            if (gameMap[nowX][nowY] == ENEMY) {
                result.offer(curr);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nextX = nowX + dx[d];
                int nextY = nowY + dy[d];

                if (inRange(nextX, nextY) && !visited[nextX][nextY] && archerPos.isAttackRanged(nextX, nextY)) {
                    visited[nextX][nextY] = true;
                    pq.offer(new Pos(nextX, nextY, nowDistance + 1));
                }
            }
        }

        if (!result.isEmpty()) {
            Pos pos = result.poll();
            attack[pos.x][pos.y] = true;
        }
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }

    static class Pos implements Comparable<Pos> {
        int x, y;
        int distance;

        public Pos(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        public boolean isAttackRanged(int x, int y) {
            return Math.abs(this.x - x) + Math.abs(this.y - y) <= D;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.distance == o.distance) {
                return this.y - o.y; // 거리가 같다면, 왼쪽에 있는 적을 우선하도록 하기
            }

            return this.distance - o.distance;
        }
    }
}
