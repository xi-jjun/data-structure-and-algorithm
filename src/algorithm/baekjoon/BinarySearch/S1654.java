package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// baekJoon 1654 Silver3 랜선 자르기
public class S1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        int K = Integer.parseInt(line[0]);
        int N = Integer.parseInt(line[1]);

        int[] LAN = new int[K];

        for (int i = 0; i < K; i++) LAN[i] = Integer.parseInt(br.readLine());

        long result = 0;
        long start = 0;
        long end = Arrays.stream(LAN).max().getAsInt();

        while (start <= end) {
            long total = 0;
            long mid = (start + end) / 2;

            if (mid == 0) {
                System.out.println("1");
                return;
            }

            for (int l : LAN) total += l / mid;

            if (total < N) {
                end = mid - 1;
            } else {
                start = mid + 1;
                result = mid;
            }
        }

        System.out.println(result);
    }
}
