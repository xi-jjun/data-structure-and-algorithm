package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// baekJoon 15686 Gold5 치킨 배달
// 0918 아직 해결 못 함.
// 0925 해결. 백 트래킹.
public class G15686 {
    static class ChickenPoint {
        int x, y;

        ChickenPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int WHITE_SPACE = 0;
    static final int HOME = 1;
    static final int CHICKEN = 2;

    static int[][] map;
    static ChickenPoint[] selectedChick = new ChickenPoint[13];
    static ArrayList<ChickenPoint> chick = new ArrayList<>();
    static boolean[] visited = new boolean[13];
    static int N, M, min = 9999999;

    static int findMinDistance(int startX, int startY) {
        ArrayList<Integer> distance = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int chickX = selectedChick[i].x;
            int chickY = selectedChick[i].y;
            distance.add(Math.abs(startX - chickX) + Math.abs(startY - chickY));
        }

        Collections.sort(distance);
        return distance.get(0);
    }

    static void backTracking(int depth, int at) {
        if (depth == M) {
            int totalDistance = 0;
            for (int i = 0; i < N; i++) {
                for (int k = 0; k < N; k++) {
                    if (checkHome(i, k)) totalDistance += findMinDistance(i, k);
                }
            }
            min = Math.min(min, totalDistance);
            return;
        }

        for (int i = at; i < chick.size(); i++) {
            if (!visited[i]) {
                selectedChick[depth] = chick.get(i);
                visited[i] = true;
                backTracking(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    static boolean checkHome(int x, int y) {
        return map[x][y] == HOME;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int k = 0; k < N; k++) {
                map[i][k] = Integer.parseInt(st2.nextToken());
                if (map[i][k] == CHICKEN) chick.add(new ChickenPoint(i, k));
            }
        }

        backTracking(0, 0);

        System.out.println(min);

    }
}
