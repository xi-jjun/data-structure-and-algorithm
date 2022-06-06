package algorithm.baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G2110 {
    static int N, C;
    static int[] home;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        home = new int[N];
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);

        int low = 1;
        int high = home[N - 1] - home[0] + 1;

        while (low < high) {
            int mid = (low + high) / 2;

            int installedCount = countInstallable(mid);
            if (installedCount < C) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(high - 1);
    }

    private static int countInstallable(int distance) {
        int count = 1;
        int exLocation = home[0];

        for (int i = 1; i < N; i++) {
            int nowLocation = home[i];

            if (nowLocation - exLocation >= distance) {
                exLocation = nowLocation;
                count++;
            }
        }

        return count;
    }
}
