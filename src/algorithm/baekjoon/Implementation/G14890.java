package algorithm.baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 14890 Gold3 경사로
public class G14890 {
    static int N, L, answer;
    static int[][] map;
    static boolean[][] rowUp, rowDown, colUp, colDown;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        rowUp = new boolean[N][N]; // 가로 오르막길 경사로 설치여부
        rowDown = new boolean[N][N]; // 가로 내리막길 경사로 설치여부
        colUp = new boolean[N][N];
        colDown = new boolean[N][N];

        map = new int[N][N];
        makeMap(br);

        solution();

        System.out.println(answer);
    }

    private static void solution() {
        /**
         * 1. 가로들 먼저 탐색
         * 2. 세로 탐색
         */

        // 1. 가로
        rowSearching();

        // 2. 세로 탐색
        colSearching();
    }

    private static void colSearching() {
        for (int col = 0; col < N; col++) {
            boolean thisColOk = true;

            for (int row = 1; row < N; row++) {
                if (map[row - 1][col] == map[row][col]) {
                    continue;
                }

                if (map[row - 1][col] - map[row][col] == 1) { // 내리막길 발생
                    int nowHeight = map[row][col];
                    int length = 0;

                    for (int loc = row; loc < N; loc++) {
                        if (colDown[loc][col] || nowHeight != map[loc][col]) {
                            break;
                        }
                        length++;
                        if (length >= L) break;
                    }

                    if (length >= L) {
                        int count = L;
                        int idx = row;
                        while (count-- > 0) {
                            colDown[idx++][col] = true;
                        }
                        row = idx - 1;
                    } else {
                        thisColOk = false;
                        break;
                    }
                } else if (map[row - 1][col] - map[row][col] == -1) { // 세로 오르막길
                    int nowHeight = map[row - 1][col];
                    int length = 0;

                    for (int loc = row - 1; loc >= 0; loc--) {
                        if (colUp[loc][col] || colDown[loc][col] || nowHeight != map[loc][col]) {
                            break;
                        }
                        length++;
                        if (length >= L) break;
                    }

                    if (length >= L) {
                        int count = L;
                        int idx = row - 1;
                        while (count-- > 0) {
                            colUp[idx--][col] = true;
                        }
                    } else {
                        thisColOk = false;
                        break;
                    }
                } else {
                    thisColOk = false;
                    break;
                }
            }

            if (thisColOk) {
                answer++;
            }
        }
    }

    private static void rowSearching() {
        for (int row = 0; row < N; row++) {
            boolean thisRowOk = true;

            for (int col = 1; col < N; col++) {
                if (map[row][col - 1] == map[row][col]) { // 같으면 경사로 설치 필요 없음
                    continue;
                }

                if (map[row][col - 1] - map[row][col] == 1) { // 경사로 설치 가능한 높이의 내리막길 발생
                    int nowHeight = map[row][col]; // 현재 설치를 시작할 높이와 다음 블럭의 높이가 다르면 설치를 못한다.
                    int length = 0;

                    for (int loc = col; loc < N; loc++) {
                        if (rowDown[row][loc] || nowHeight != map[row][loc]) {
                            break;
                        }
                        length++;
                        if (length >= L) break;
                    }

                    if (length >= L) {
                        int count = L;
                        int idx = col;
                        while (count-- > 0) {
                            rowDown[row][idx++] = true;
                        }
                        col = idx - 1;
                    } else {
                        thisRowOk = false;
                        break;
                    }
                } else if (map[row][col - 1] - map[row][col] == -1) { // 경사로 설치 가능한 높이의 오르막길 발생
                    int nowHeight = map[row][col - 1];
                    int length = 0; // 현재 설치가 가능한 길이를 구하기 위한 acc 변수

                    for (int loc = col - 1; loc >= 0; loc--) {
                        // 지도 범위 지켜야 함 + 경사로 설치 돼있으면 안됨 + 설치할 칸의 높이가 같아야 함
                        if (rowUp[row][loc] || rowDown[row][loc] || nowHeight != map[row][loc]) {
                            break;
                        }
                        length++;
                        if (length >= L) break; // 충분히 설치 가능한 자리 확보
                    }

                    if (length >= L) { // 경사로가 설치가능한 길이가 확보됐다면,
                        int count = L;
                        int idx = col - 1;
                        while (count-- > 0) { // 주어진 경사로 길이만큼
                            rowUp[row][idx--] = true; // 오르막길용 경사로 설치 표시
                        }
                    } else { // 경사로 설치를 못하면 바로 out
                        thisRowOk = false;
                        break;
                    }
                } else {
                    thisRowOk = false;
                    break;
                }
            }

            if (thisRowOk) {
                answer++;
            }
        }
    }

    private static void makeMap(BufferedReader br) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
