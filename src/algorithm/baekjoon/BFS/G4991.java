package BFS;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// baekJoon 4991 Gold2 로봇청소기
// 10/06 포기...
public class G4991 {
    static final String CLEAN = ".";
    static final String VISITED = "V";
    static final String DIRTY = "*";
    static final String FURNITURE = "x";
    static final String START_LOCATION = "o";
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int w, h;
    static String[][] map;
    static int[][] distance;

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int bfs(int startX, int startY, int depth) {
        Queue<Point> q = new ArrayDeque<>();
        map[startX][startY] = VISITED;
        q.offer(new Point(startX, startY));
        int endCount = 0;
        int dirtyCount = 0;

        while (!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;

            q.poll();

            for(int i=0;i<4;i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if(nextX >= 0 && nextX < h && nextY >= 0 && nextY < w) {
                    if(!map[nextX][nextY].equals(FURNITURE)) {
                        distance[nextX][nextY] = distance[x][y]+1;
                        map[nextX][nextY] = VISITED;
                        if (map[nextX][nextY].equals(DIRTY)) {
                            dirtyCount++;
                            if (dirtyCount == depth) return distance[nextX][nextY];
                        }
                        if (endCount == 400) return -1;
                        endCount++;
                        q.offer(new Point(nextX, nextY));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = 0, startY = 0;
            int dirtyCount = 0;
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w + h == 0) break;

            map = new String[h][w];
            distance = new int[h][w];

            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                int index = 0;
                for (String s : line.split("")) {
                    map[i][index++] = s;
                    if (s.equals(START_LOCATION)) {
                        startX = i;
                        startY = index - 1;
                    } else if (s.equals(DIRTY)) dirtyCount++;
                }
            }

            int result = bfs(startX, startY, dirtyCount);
            bw.write(result+"\n");
            for (String[] s1 : map) {
                for (String s0 : s1) {
                    bw.write(s0+"");
                }
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
