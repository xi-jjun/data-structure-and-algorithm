package algorithm.swexpert.d3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// sw expert 13428 D3 숫자조작
public class P13428 {
    static int T;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int[] pick = new int[2];
    static boolean[] isUsed;
    static char[] number, ORIGINAL;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            number = br.readLine().toCharArray();
            reset();

            solution(0, 0);

            bw.write("#" + tc + " " + min + " " + max + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void reset() {
        ORIGINAL = number.clone();
        min = Integer.parseInt(String.valueOf(number));
        max = Integer.parseInt(String.valueOf(number));
        isUsed = new boolean[number.length];
    }

    private static void solution(int depth, int at) {
        if (depth == 2) {
            ORIGINAL = number.clone();
            swap();

            if (number[0] != '0') {
                int madeNumber = Integer.parseInt(String.valueOf(number));
                min = Math.min(min, madeNumber);
                max = Math.max(max, madeNumber);
            }

            number = ORIGINAL.clone();
            return;
        }

        for (int i = at; i < number.length; i++) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                pick[depth] = i;
                solution(depth + 1, i);
                isUsed[i] = false;
            }
        }
    }

    private static void swap() {
        int firstIndex = pick[0];
        int secondIndex = pick[1];
        char temp = number[firstIndex];
        number[firstIndex] = number[secondIndex];
        number[secondIndex] = temp;
    }
}
