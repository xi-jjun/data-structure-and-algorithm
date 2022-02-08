package BruteForcing;

import java.io.*;

// baekJoon 1018 Silver5 체스판 다시 칠하기
public class S1018 {
    static final boolean BLACK = false;
    static final boolean WHITE = true;
    static boolean[][] map;

    static int countFunction(boolean nextColor, int startX, int startY){
        int count=0;
        for (int i = startX; i < startX + 8; i++) {
            for (int k = startY; k < startY + 8; k++) {
                if (startX != i && startY != k) {
                    if (map[i][k] != nextColor) count++;
                    nextColor = !nextColor;
                }
            }
            nextColor = !nextColor;
        }
        return count;
    }

    static int countColor(boolean firstColor, int startX, int startY) {
        int countMany1 = 0;
        int countMany2 = 0;
        boolean nextColor = firstColor ? BLACK : WHITE;

        countMany1 = countFunction(nextColor, startX, startY);
        countMany2 = countFunction(!nextColor, startX, startY);

        return Math.min(countMany1, countMany2 + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] t = br.readLine().split(" ");
        int N = Integer.parseInt(t[0]);
        int M = Integer.parseInt(t[1]);
        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            int index = 0;
            for (int k = 0; k < M; k++) map[i][k] = line[index++].equals("B") ? BLACK : WHITE;
        }

        // Brute Force START!
        int min = 99999;
        for (int i = 0; i < N - 7; i++) {
            for (int k = 0; k < M - 7; k++) {
                boolean firstColor = map[i][k];
                int count = countColor(firstColor, i, k);
                min = Math.min(count, min);
            }
        }

        System.out.println(min);
    }
}
