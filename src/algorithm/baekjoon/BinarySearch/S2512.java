package algorithm.baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 2512 silver3 예산
public class S2512 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int min = 1;
        int max = 0;

        int N = Integer.parseInt(br.readLine());
        int[] m = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; st.hasMoreTokens(); i++) {
            m[i] = Integer.parseInt(st.nextToken());
            max = Math.max(m[i], max);
        }

        int M = Integer.parseInt(br.readLine());

        int answer = 0;

        while (min <= max) {
            int limit = (min + max) / 2;

            long sum = 0;
            for (int i = 0; i < N; i++) {
                sum += Math.min(m[i], limit);
            }

            if (sum > M) {
                max = limit - 1;
            } else if (sum < M) {
                min = limit + 1;
                answer = Math.max(answer, limit);
            } else {
                answer = Math.max(answer, limit);
                break;
            }
        }

        System.out.println(answer);

        br.close();
    }
}
