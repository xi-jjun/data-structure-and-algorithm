package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// baekJoon 1449 silver3 수리공 항승
public class S1449 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int L = Integer.parseInt(st1.nextToken());
        int[] fix = new int[N];

        StringTokenizer line = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) fix[i] = Integer.parseInt(line.nextToken());
        Arrays.sort(fix);

        int count = 1;
        double location = fix[0] - 0.5 + L;
        for (int i = 1; i < N; i++) {
            if (location < fix[i] + 0.5) {
                location = fix[i] - 0.5 + L;
                count++;
            }
        }

        System.out.println(count);
    }
}
