package divide_and_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 1074 silver1 Z
public class S1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        int N = Integer.parseInt(line[0]);
        int r = Integer.parseInt(line[1]);
        int c = Integer.parseInt(line[2]);

        Long answer = binaryConquer(N, r, c, 0L);
        System.out.println(answer);
    }

    private static Long binaryConquer(int n, int r, int c, Long start) {
        int len = (int) Math.pow(2, n);

        if (n == 1) {
            if (r == 0 && c == 0) return start;
            if (r == 0 && c == 1) return start + 1L;
            if (r == 1 && c == 0) return start + 2L;
            if (r == 1 && c == 1) return start + 3L;
        }

        if (r < len / 2 && c < len / 2) {
            int rr = r;
            int cc = c;
            Long newStart = start + 0;
            return binaryConquer(n - 1, rr, cc, newStart);
        } else if (r < len / 2 && c >= len / 2) {
            int rr = r;
            int cc = c - len / 2;
            Long newStart = start + (int) Math.pow(len / 2, 2);
            return binaryConquer(n - 1, rr, cc, newStart);
        } else if (r >= len / 2 && c < len / 2) {
            int rr = r - len / 2;
            int cc = c;
            Long newStart = start + 2L * (int) Math.pow(len / 2, 2);
            return binaryConquer(n - 1, rr, cc, newStart);
        } else {
            int rr = r - len / 2;
            int cc = c - len / 2;
            Long newStart = start + 3L * (int) Math.pow(len / 2, 2);
            return binaryConquer(n - 1, rr, cc, newStart);
        }
    }
}
