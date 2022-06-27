package algorithm.baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1072 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        long z0 = (Y * 100L) / X;
        long std = z0 + 1;

        long min = 0;
        long max = Integer.MAX_VALUE;
        long answer = Integer.MAX_VALUE;

        while (min < max) {
            long mid = (min + max) / 2;
            long z = ((Y + mid) * 100L) / (X + mid);

            if (z < std) {
                min = mid + 1;
            } else {
                max = mid;
                answer = Math.min(answer, mid);
            }
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(answer);
        }
    }
}
