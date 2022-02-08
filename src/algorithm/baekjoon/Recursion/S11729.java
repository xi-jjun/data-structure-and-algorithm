package Recursion;

import java.io.*;

// baekJoon 11729 silver2 하노이탑 이동순서
public class S11729 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void hanoi(int from, int m, int to, int plateNum) throws IOException {
        if (plateNum == 0) return;
        hanoi(from, to, m, plateNum - 1);
        bw.write(from + " " + to + "\n");
        hanoi(m, from, to, plateNum - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        bw.write(((int) Math.pow(2, N) - 1) + "\n");

        hanoi(1, 2, 3, N);

        bw.flush();
        bw.close();
    }
}
