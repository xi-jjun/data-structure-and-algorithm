package Queue;

import java.io.*;
import java.util.*;

// baekJoon 3190 Gold5 뱀
public class G3190 {
    static final int EMPTY = 0;
    static final int APPLE = 1;
    static int[][] board;
    static int N, K, L;
    static boolean[][] body;
    static int[] dx = {-1, 0, 1, 0}; // N E S W
    static int[] dy = {0, 1, 0, -1};
    static Deque<Point> dq;
    static int countSeconds = 1;

    static class Point {
        int x;
        int y;
        int dir; // 0~3 N E S W

        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static boolean checkApple(Point p) {
        return board[p.x][p.y] == APPLE;
    }

    static boolean moveSnack(Point p) {
        int nextX = p.x + dx[p.dir];
        int nextY = p.y + dy[p.dir];

        if (nextX < 0 || nextY >= N || nextX >= N || nextY < 0 || body[nextX][nextY]) {
            return false;
        }

        dq.add(new Point(nextX, nextY, p.dir));
        body[nextX][nextY] = true;

        return true;
    }

    static int changeDirection(int dir, String d) {
        int dd = d.equals("L") ? dir - 1 : dir + 1;
        if (dd == -1) return 3;
        else if (dd == 4) return 0;
        return dd;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // board SIZE
        K = Integer.parseInt(br.readLine()); // Apple

        board = new int[N][N];
        body = new boolean[N][N];

        for (int i = 0; i < K; i++) {
            String[] appleCoord = br.readLine().split(" ");
            int x = Integer.parseInt(appleCoord[0]) - 1;
            int y = Integer.parseInt(appleCoord[1]) - 1;

            board[x][y] = APPLE;
        }

        L = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<String>> moveInfo = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < L; i++) moveInfo.add(new ArrayList<>());

        for (int i = 0; i < L; i++) {
            String[] move = br.readLine().split(" ");
            int sec = Integer.parseInt(move[0]);
            String direction = move[1];

            moveInfo.get(i).add(sec + "");
            moveInfo.get(i).add(direction);
        }

        int infoIndex = 0;

        // START!
        dq = new ArrayDeque<>();
        dq.add(new Point(0, 0, 1));
        body[0][0] = true; // Body is existed in (0, 0)
        if (infoIndex != moveInfo.size() && moveInfo.get(infoIndex).get(0).equals(countSeconds + "")) {
            dq.peekLast().dir = changeDirection(dq.peekLast().dir, moveInfo.get(infoIndex).get(1));
            infoIndex++;
        }

//        int tempSeq = 1;

        while (true) {
            Point head = dq.peekLast();
            boolean cc = moveSnack(head);

            if (!cc) break;
            else {
                Point movedHead = dq.peekLast();
                if (!checkApple(movedHead)) {
                    Point rm = dq.removeFirst();
                    body[rm.x][rm.y] = false;
                } else { // Apple is existed
                    board[movedHead.x][movedHead.y] = EMPTY;
                }
            }

            if (infoIndex != moveInfo.size() && moveInfo.get(infoIndex).get(0).equals(countSeconds + "")) {
                dq.peekLast().dir = changeDirection(dq.peekLast().dir, moveInfo.get(infoIndex).get(1));
                infoIndex++;
            }
            countSeconds++;

//            bw.write("START " + (tempSeq++) + "\n");
//            for (boolean[] a : body) {
//                for (boolean b : a) {
//                    if (b) bw.write("* ");
//                    else bw.write("0 ");
//                }
//                bw.write("\n");
//            }
//            bw.write("\n\n");
        }
        bw.write(countSeconds + "");

        bw.flush();
        bw.close();
    }
}
