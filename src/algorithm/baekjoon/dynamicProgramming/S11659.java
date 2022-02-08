package dynamicProgramming;

import java.io.*;
import java.util.StringTokenizer;

// baekJoon 11659 silver3 구간 합 구하기4
public class S11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] sums = new int[N + 1];
        sums[1] = Integer.parseInt(st.nextToken());
        int sum = sums[0];
        for (int i = 2; i <= N; i++) {
            sums[i] = sums[i - 1] + Integer.parseInt(st.nextToken());
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bw.write((sums[end] - sums[start - 1]) + "\n");
        }
        bw.flush();
        bw.close();
    }
}
