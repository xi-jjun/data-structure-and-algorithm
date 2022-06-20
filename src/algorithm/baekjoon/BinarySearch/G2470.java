package algorithm.baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://ansohxxn.github.io/boj/2470/ 참고
// baekJoon 2470 Gold5 두 용액
public class G2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] S = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; st.hasMoreTokens(); i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(S);

        int min = Integer.MAX_VALUE;
        int minStart = 0;
        int minEnd = 0;

        int start = 0;
        int end = N - 1;

        while (start < end) {
            int temp = S[start] + S[end];
            if (Math.abs(min) > Math.abs(temp)) {
                minStart = start;
                minEnd = end;
                min = temp;
            }
            if (temp > 0) {
                --end;
            } else if (temp < 0) {
                ++start;
            } else {
                break;
            }
        }

        System.out.println(S[minStart] + " " + S[minEnd]);

        br.close();
    }
}
