package Greedy;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

// baekJoon 11497 silver1 통나무 건너뛰기
public class S11497 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] log = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            Deque<Integer> dq = new ArrayDeque<>();

            for (int k = 0; k < N; k++) log[k] = Integer.parseInt(st.nextToken());
            Arrays.sort(log);

            for (int k = N - 1; k >= 0; k--) {
                if (k % 2 == 1) dq.addFirst(log[k]);
                else dq.addLast(log[k]);
            }

            int max = Math.abs(dq.peekFirst() - dq.peekLast());

            while (!dq.isEmpty()) {
                int first = dq.removeFirst();
                if (dq.isEmpty()) break;
                int second = dq.peekFirst();

                max = Math.max(max, Math.abs(first - second));
            }

            bw.write(max + "\n");
        }

        bw.flush();
        bw.close();
    }
}
