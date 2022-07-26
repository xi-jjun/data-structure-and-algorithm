package algorithm.baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G17406 {
    static int N, M, K, A, answer = Integer.MAX_VALUE;
    static int[][] map, ORIGINAL;
    static int[][] ROTATE_METHODS, methods;
    static boolean[] isChecked;

    public static void main(String[] args) throws IOException {
        getInput();

        chooseRotateSequence(0);

        System.out.println(answer);
    }

    private static void chooseRotateSequence(int depth) {
        if (depth == K) {
            A = Integer.MAX_VALUE;
//            copyMapToOriginal();
            simulation();
            calculate();
            copyOriginalToMap();
            answer = Math.min(answer, A);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!isChecked[i]) {
                isChecked[i] = true;
                methods[depth] = ROTATE_METHODS[i].clone();
                chooseRotateSequence(depth + 1);
                isChecked[i] = false;
            }
        }
    }

    private static void calculate() {
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += map[i][j];
            }
            A = Math.min(A, sum);
        }
    }

    private static void simulation() {
        for (int i = 0; i < K; i++) {
            int[] method = methods[i];
            rotate(method);
        }
    }

    private static void rotate(int[] method) {
        int startX = method[0] - method[2];
        int startY = method[1] - method[2];
        int endX = method[0] + method[2];
        int endY = method[1] + method[2];
        Pos start = new Pos(startX, startY);
        Pos end = new Pos(endX, endY);

        while (Pos.isSquare(start, end)) {
            operate(start, end);
            start.moveStartToNext();
            end.moveEndToNext();
        }
    }

    private static void operate(Pos start, Pos end) {
        int rightUp = moveUpside(start, end);

        int rightDown = moveRightSide(start, end, rightUp);

        int leftDown = moveBottomSide(start, end, rightDown);

        moveLeftSide(start, end, leftDown);
    }

    private static void moveLeftSide(Pos start, Pos end, int leftDown) {
        for (int row = start.x; row < end.x - 1; row++) {
            map[row][start.y] = map[row + 1][start.y];
        }
        map[end.x - 1][start.y] = leftDown;
    }

    private static int moveBottomSide(Pos start, Pos end, int rightDown) {
        int leftDown = map[end.x][start.y];
        for (int col = start.y; col < end.y - 1; col++) {
            map[end.x][col] = map[end.x][col + 1];
        }
        map[end.x][end.y - 1] = rightDown;
        return leftDown;
    }

    private static int moveRightSide(Pos start, Pos end, int rightUp) {
        int rightDown = map[end.x][end.y];
        for (int row = end.x; row > start.x + 1; row--) {
            map[row][end.y] = map[row - 1][end.y];
        }
        map[start.x + 1][end.y] = rightUp;
        return rightDown;
    }

    private static int moveUpside(Pos start, Pos end) {
        int rightUp = map[start.x][end.y];
        for (int col = end.y; col > start.y; col--) {
            map[start.x][col] = map[start.x][col - 1];
        }
        return rightUp;
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ORIGINAL = new int[N][M];
        ROTATE_METHODS = new int[K][3];
        methods = new int[K][3];
        isChecked = new boolean[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                ORIGINAL[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            ROTATE_METHODS[i][0] = Integer.parseInt(st.nextToken()) - 1;
            ROTATE_METHODS[i][1] = Integer.parseInt(st.nextToken()) - 1;
            ROTATE_METHODS[i][2] = Integer.parseInt(st.nextToken());
        }
    }

    private static void copyMapToOriginal() {
        for (int i = 0; i < N; i++) {
            ORIGINAL[i] = map[i].clone();
        }
    }

    private static void copyOriginalToMap() {
        for (int i = 0; i < N; i++) {
            map[i] = ORIGINAL[i].clone();
        }
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void moveStartToNext() {
            this.x++;
            this.y++;
        }

        public void moveEndToNext() {
            this.x--;
            this.y--;
        }

        public static boolean isSquare(Pos start, Pos end) {
            return start.x < end.x && start.y < end.y;
        }
    }
}
