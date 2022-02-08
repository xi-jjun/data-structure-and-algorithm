package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// baekJoon 16928 silver1 뱀과 사다리 게임
public class S16928 {
    static int[] LADDER = new int[101];
    static int[] SNAKE = new int[101];
    static boolean[] visited = new boolean[101];
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        getMapInfo(br, N, LADDER);
        getMapInfo(br, M, SNAKE);

        bfs();
        System.out.println(answer);
    }

    private static void getMapInfo(BufferedReader br, int n, int[] additional) throws IOException {
        while (n-- > 0) {
            String[] temp = br.readLine().split(" ");
            additional[Integer.parseInt(temp[0])] = Integer.parseInt(temp[1]);
        }
    }

    private static void bfs() {
        Queue<Location> location = new ArrayDeque<>();
        location.offer(new Location(1, 1)); // start!!

        while (!location.isEmpty()) {
            int nowLocation = location.peek().location;
            int stage = location.peek().count;
            location.poll();
            visited[nowLocation] = true;

            for (int i = 1; i < 7; i++) {
                int nextLocation = nowLocation + i;
                int nextStage = stage + 1;
                if (nextLocation == 100) {
                    answer = stage;
                    return;
                }

                if (!visited[nextLocation]) {
                    visited[nextLocation] = true;
                    if (nextLocation > 100) break;

                    if (SNAKE[nextLocation] != 0) {
                        visited[nextLocation] = true;
                        location.offer(new Location(SNAKE[nextLocation], nextStage));
                    } else if (LADDER[nextLocation] != 0) {
                        visited[nextLocation] = true;
                        location.offer(new Location(LADDER[nextLocation], nextStage));
                    } else {
                        location.offer(new Location(nextLocation, nextStage));
                    }
                }
            }
        }
    }

    static class Location {
        private int location;
        private int count;

        public Location(int location, int count) {
            this.location = location;
            this.count = count;
        }
    }
}
