package programmers.wooteco;

public class Sol5 {
    static boolean[][] visited;
    static int N, M;

    public static int[][] solution(int rows, int columns) {
        N = rows;
        M = columns;
        int[][] answer = new int[rows][columns];
        visited = new boolean[rows][columns];

        function(0, answer);
        return answer;
    }

    private static void function(int seq, int[][] answer) {
        boolean keepGo = true;
        int x = 0;
        int y = 0;

        while (keepGo) {
            if (!visited[x][y]) {
                visited[x][y] = true;
                answer[x][y] = ++seq;
            } else {
                if (!checkStop()) break; // check All first visited/ true:no stop
                else {
                    int nextX = (seq + 1) % 2 == 0 ? (x + 1) % N : x;
                    int nextY = (seq + 1) % 2 == 0 ? y : (y + 1) % M;
                    if (!visited[nextX][nextY]) {
                        answer[x][y] = ++seq;
                    } else break;

                }
            }
            x = seq % 2 == 0 ? (x + 1) % N : x;
            y = seq % 2 == 0 ? y : (y + 1) % M;
        }
    }

    static boolean checkStop() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) return true; // keep Going
            }
        }
        return false;
    }





    public static void main(String[] args) {
        extracted(5, 3);
    }

    private static void extracted(int r, int c) {
        System.out.println("row:" + r + " col:" + c);
        for (int[] ints : solution(r, c)) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println("");
        }
    }
}
