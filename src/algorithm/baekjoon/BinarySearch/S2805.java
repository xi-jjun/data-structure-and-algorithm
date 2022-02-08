package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// baekJoon 2805 silver3 나무자르기
// https://www.youtube.com/watch?v=94RC-DsGMLo&list=PLRx0vPvlEmdAghTr5mXQxGpHjWqSz0dgC&index=5 참고
public class S2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int M = Integer.parseInt(st1.nextToken());
        int[] len = new int[N];

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) len[i] = Integer.parseInt(st2.nextToken());

        int start = 0; // length start
        int end = Arrays.stream(len).max().getAsInt(); // length end
        long total = 0;
        int result = 0;

        while (start <= end) {
            total = 0;

            int mid = (start + end) / 2;
            for (int l : len) {
                if (l > mid)
                    total += l - mid;
            }

            if (total < M) end = mid - 1;
            else {
                start = mid + 1;
                result = mid;
            }
        }
        System.out.println(result);
    }
}
