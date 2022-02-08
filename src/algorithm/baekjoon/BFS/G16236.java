package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// baekJoon 16236 Gold4 아기상어
// 반례만 참고. 혼자 품
public class G16236 {
    static final int SHARK = 9;
    static int N;
    static int[][] map;
    static int level;
    static int sharkSize;
    static int sharkX, sharkY;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        sharkSize = 2;
        level = 0;
        int answer = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == SHARK) {
                    sharkX = i;
                    sharkY = j;
                }
            }
        }

        // START!!
        while (true) {
            List<Location> checkList = new LinkedList<>(); // can eat check list
            List<Location> resultList;

            // 1. find fish which can be eaten || Is shark can go?
            if (!findFish(checkList) || !canGo(sharkX, sharkY)) break;

            // 2. check list 에 대한 최단길이 각각 구하기
            resultList = findMinPath(checkList);
            if(resultList.size() == 0) break;

            // 3. 최단 길이 sorting
            resultList.sort((o1, o2) -> {
                if (o1.time == o2.time) {
                    if (o1.x == o2.x) return o1.y - o2.y;
                    else return o1.x - o2.x;
                } else return o1.time - o2.time;
            });

            // 4. baby shark move!
            Location movedShark = resultList.get(0);
            sharkMove(movedShark);

            // 5. time
            answer += movedShark.time;
        }

        System.out.println(answer);

    }

    private static boolean canGo(int x, int y) {
        boolean[][] ignore = new boolean[N][N];

        for (int i = 0; i < 4; i++) {
            int probeX = x + dx[i];
            int probeY = y + dy[i];

            if (isValid(probeX, probeY, ignore)) return true;
        }

        return false;
    }

    private static void sharkMove(Location movedShark) {
        if (movedShark.time != 0) {
            map[sharkX][sharkY] = 0;
            map[movedShark.x][movedShark.y] = SHARK;
            sharkX = movedShark.x;
            sharkY = movedShark.y;

            level++;
            if (level == sharkSize) {
                level = 0;
                sharkSize++;
            }
        }
    }

    private static List<Location> findMinPath(List<Location> checkList) {
        boolean[][] visited = new boolean[N][N];
        Queue<Location> locations = new ArrayDeque<>();
        locations.offer(new Location(sharkX, sharkY, 9));
        int[][] distance = new int[N][N];

        while (!locations.isEmpty()) {
            int x = locations.peek().x;
            int y = locations.peek().y;
            visited[x][y] = true;
            locations.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (isValid(nextX, nextY, visited)) {
                    distance[nextX][nextY] = distance[x][y] + 1;
                    visited[nextX][nextY] = true;
                    locations.offer(new Location(nextX, nextY, map[nextX][nextY]));
                }
            }
        }

        List<Location> result = new ArrayList<>();

        for (Location location : checkList) {
            location.time = distance[location.x][location.y];
            if (location.time != 0) {
                Location temp = new Location(location.x, location.y, map[location.x][location.y]);
                temp.time = location.time;
                result.add(temp);
            }
        }

        return result;
    }

    private static boolean isValid(int x, int y, boolean[][] v) {
        return !(x < 0 || x >= N || y < 0 || y >= N) && (map[x][y] <= sharkSize) && !v[x][y];
    }

    private static boolean findFish(List<Location> checkList) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int size = map[i][j];
                if (size < sharkSize && size != 0 && size != 9) checkList.add(new Location(i, j, map[i][j]));
            }
        }

        return checkList.size() != 0;
    }

    static class Location {
        int x, y;
        int size;
        int time;

        public Location(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
//
//        public String toString() {
//            return "x:" + x + "  y:" + y + "\ntime: " + time + "  size: " + map[x][y];
//        }
    }
}
