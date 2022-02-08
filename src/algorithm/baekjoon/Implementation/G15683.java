package Implementation;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// baekJoon 15683 Gold5 감시
// 포기
public class G15683 {
    static final int EMPTY = 0;
    static final int WALL = 6;
    static final int OBSERVED = 7;
    static int[][] officeRoom;
    static int N, M;

    static int find1(int x, int y, int dir) {
        int limit = -999;
        int count = 0;
        boolean move = false; // false:move--, true:move++
        boolean change = false; // false:move X, true:move Y
        switch (dir) {
            case 0: // move X // X--
                limit = -1; // 0이면 종료
                break;
            case 1:
                change = true; // move Y
                move = true; // Y++
                limit = M; // M-1이면 종료
                break;
            case 2: // move X
                move = true; // X++
                limit = N; // N-1이면 종료
                break;
            case 3: // left
                change = true; // move Y // Y--
                limit = -1; // 0이면 종료
                break;
        }
        int num = change ? y : x;
        while (num != limit) {
            if (change) count = officeRoom[x][num] == EMPTY ? count + 1 : count;
            else count = officeRoom[num][y] == EMPTY ? count + 1 : count;

            num = move ? num + 1 : num - 1;
        }

        return count;
    }

    static int find2(int x, int y, int dir) {
        int limit;
        int count = 0;
        boolean change = false; // false:move X, true:move Y

        switch (dir) {
            case 0: // horizon
                // x-- and x++
                change = false;
                break;
            case 1: // vertical:
                // y-- and y++
                change = true;
                break;
        }

        limit = change ? M : N;

        for (int i = 0; i < limit; i++) {
            if (change) count = officeRoom[x][i] == EMPTY ? count + 1 : count;
            else count = officeRoom[i][y] == EMPTY ? count + 1 : count;
        }

        return count;
    }

    static int findMax(int x, int y, int cctv) { // dir 0,1,2,3 => up, right, down, left
        // upside x---- 'y'
        // right 'x' y++++
        // down x++++ 'y'
        // left 'x' y----
        if (cctv == 1) {
            Map<Integer, Integer> cctvMax = new HashMap<>(); // dir, total count
            cctvMax.put(0, find1(x, y, 0));
            cctvMax.put(1, find1(x, y, 1));
            cctvMax.put(2, find1(x, y, 2));
            cctvMax.put(3, find1(x, y, 3));

            int max = -9999;
            int direction = -1;
            for (Integer integer : cctvMax.keySet()) {
                if(max < cctvMax.get(integer)) {
                    max = cctvMax.get(integer);
                    direction = integer;
                }
            }

            checkObserved(x, y, direction, cctv);


            return 1;
        } else if (cctv == 2) {
            return Math.max(find2(x, y, 0), find2(x, y, 1));
        }
//        else if (cctv == 3) {
//            int a = Math.max(find3(x, y, 0), find3(x, y, 1));
//            int b = Math.max(find3(x, y, 2), find3(x, y, 3));
//            return Math.max(a, b);
//        } else if (cctv == 4) {
//            int a = Math.max(find4(x, y, 0), find4(x, y, 1));
//            int b = Math.max(find4(x, y, 2), find4(x, y, 3));
//            return Math.max(a, b);
//        } else return find5(x, y);
        return -999;
    }

    private static void checkObserved(int x, int y, int direction, int cctv) {
        if(cctv == 1) {

        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        officeRoom = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < M; k++) {
                officeRoom[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < M; k++) {
                if (officeRoom[i][k] >= 1 && officeRoom[i][k] <= 5) { // CCTV
                    int a = findMax(i, k, officeRoom[i][k]);
                    System.out.println("a = " + a);
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
