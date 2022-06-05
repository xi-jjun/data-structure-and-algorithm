package algorithm.programmers.leveltest.test220605;

import java.util.Arrays;

public class Solution2 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static final char PARTITION = 'X';
    static int answerIdx;
    static int[] answer;
    static char[][] map;
    static boolean[][] visited;
    static int stdX, stdY;
    static boolean isFinished = false;

    public static void main(String[] args) {
        String[][] places = {
//                {
//                        "POOOP",
//                        "OXXOX",
//                        "OPXPX",
//                        "OOXOX",
//                        "POXXP"
//                },
//                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
//                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
//                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
//                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"},
                {
                        "PXPOO",
                        "XXXXX",
                        "POPXO",
                        "OXPXO",
                        "PXXPX"
                }
        };

        int[] answer = solution(places); // [1, 0, 1, 1, 1]
        for (int i : answer) {
            System.out.println("i = " + i);
        }
    }

    public static int[] solution(String[][] places) {
        answerIdx = 0;
        answer = new int[places.length];
        Arrays.fill(answer, 1);
        for (String[] room : places) {
            map = new char[5][5];
            for (int i = 0; i < 5; i++) {
                map[i] = room[i].toCharArray();
            }

            isFinished = false;
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    if (map[x][y] == 'P') {
                        stdX = x;
                        stdY = y;
                        visited = new boolean[5][5];
                        visited[x][y] =true;
                        dfs(x, y);
//                        System.out.println("(" + x + ", " + y + ") is " + isFinished);
                        if (isFinished) break;
                    }
                }
                if (isFinished) break;
            }

            answerIdx++;
        }
        return answer;
    }

    private static void dfs(int x, int y) {
        if (map[x][y] == PARTITION || !distance(x, y) || isFinished) {
            return;
        }

        if (map[x][y] == 'P' && (stdX != x || stdY != y)) {
            answer[answerIdx] = 0;
            isFinished = true;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (inRange(nextX, nextY) && !visited[nextX][nextY]) {
                visited[nextX][nextY] = true;
                dfs(nextX, nextY);
                visited[nextX][nextY] = false;
            }
        }
    }

    private static boolean distance(int x, int y) {
        int distance = Math.abs(stdX - x) + Math.abs(stdY - y);
        return distance <= 2;
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= 5 || y < 0 || y >= 5);
    }
}
