package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 1463 silver3 1로 만들기
// https://www.youtube.com/watch?v=5Lu34WIx2Us&list=PLRx0vPvlEmdAghTr5mXQxGpHjWqSz0dgC&index=6 참고
public class S1463 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] d = new int[100001];
        if (N == 1) {
            System.out.println("0");
            return;
        }

        for (int i = 2; i <= N; i++) {
            d[i] = d[i - 1] + 1;
            if (i % 3 == 0) d[i] = Math.min(d[i], d[i / 3] + 1);
            if (i % 2 == 0) d[i] = Math.min(d[i], d[i / 2] + 1);
        }

        System.out.println(d[N]);
    }
}
