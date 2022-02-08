package programmers.Test1015;

public class Sol1 {
    static final int[] dx = {-1, 0, 1, 0}; // U R D L
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
//        String dirs = "LULLLLLLU";
        String dirs = "LURDDRULLL";
        System.out.println("solution(dirs) = " + solution(dirs));
    }

    public static boolean notValid(int x, int y) {
        return x < 0 || x >= 11 | y < 0 || y >= 11;
    }

    public static void printMap(int[][] map) {
        for (int[] i : map) {
            for (int k : i) {
                System.out.print(k + " ");
            }
            System.out.println("");
        }
    }

    public static int solution(String dirs) {
        int answer = 0;
        int[][] map = new int[11][11];
        boolean[][] visited = new boolean[11][11];

        // start location map[5][5]
        int x = 5;
        int y = 5;

        for (String dir : dirs.split("")) {
            int d = direction(dir);
            int nextX = x + dx[d];
            int nextY = y + dy[d];

            if(!notValid(nextX, nextY)) { // ok
                printMap(map);
                System.out.println("");
                if(map[x][y]+map[nextX][nextY] < 3) {
                    answer++;
                    visited[x][y] = true;
                }
                // move
                x = nextX;
                y = nextY;
                map[x][y]++;
            }
        }

        return answer;
    }

    private static int direction(String dir) {
        switch (dir.charAt(0)) {
            case 'U':
                return 0;
            case 'R':
                return 1;
            case 'D':
                return 2;
            case 'L':
                return 3;
        }
        return -1;
    }
}
