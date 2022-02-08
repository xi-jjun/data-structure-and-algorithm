package programmers.Test1105;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// FAIL
public class Sol2 {
    static boolean[] visited;
    static int[][] dataLine = new int[2][3];
    static List<Star> stars = new ArrayList<>();

    public static String[] solution(int[][] line) {
        String[] answer;
        visited = new boolean[line.length];
        backTracking(0, 0, line); // get star(x,y)
        stars.sort(((o1, o2) -> o2.x - o1.x));
        int maxX = stars.get(0).x;
        int minX = stars.get(stars.size() - 1).x;
        int maxY = stars.get(0).y;
        int minY = stars.get(stars.size() - 1).y;
        answer = new String[maxX - minX];
        for (int i = 0; i < answer.length; i++) {
            for (int j = minY; j <= maxY; j++) {
                boolean ok = false;
                for (Star star : stars) {
                    int x = star.x;
                    int y = star.y;
                    if (x + (answer.length / 2) == i && y == j) {
                        answer[i] += "*";
                        ok = true;
                        break;
                    }
                }
                if (!ok) answer[i] += ".";
                System.out.print(answer[i]);
            }
            System.out.println("");
        }
        for (Star star : stars) {
            System.out.println(star.x + " " +star.y);
        }
        return answer;
    }

    private static void backTracking(int depth, int at, int[][] line) {
        if (depth == 2) {
            check(dataLine[0], dataLine[1]);
            return;
        }

        for (int i = at; i < line.length; i++) {
            if (!visited[i]) {
                dataLine[depth] = line[i];
                visited[i] = true;
                backTracking(depth + 1, at + 1, line);
                visited[i] = false;
            }
        }
    }

    private static void check(int[] line1, int[] line2) {
        int x, y;
        int A = line1[0];
        int B = line1[1];
        int E = line1[2];
        int C = line2[0];
        int D = line2[1];
        int F = line2[2];
        int upper1 = B * F - E * D;
        int upper2 = E * C - A * F;
        int lower = A * D - B * C;
        if (lower == 0) return;
        if (upper1 % lower == 0 && upper2 % lower == 0) {
            x = upper1 / lower;
            y = upper2 / lower;
            stars.add(new Star(x, y));
        }
    }

    public static void main(String[] args) {
        int[][] l = {
                {2, -1, 4},
                {-2, -1, 4},
                {0, -1, 1},
                {5, -8, -12},
                {5, 8, 12}
        };

        for (String s : solution(l)) {
            System.out.println(s);
        }
    }

    static class Star {
        private final int x, y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
