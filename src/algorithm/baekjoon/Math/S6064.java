package Math;

import java.io.*;
import java.util.StringTokenizer;

// baekJoon 6064 silver1 카잉 달력
// 정신나가버리게 만든 문제
public class S6064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int maxCount = getMaxCount(M, N);
            int answer = getAnswer(x, y, M, N, maxCount);

            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int getAnswer(int x, int y, int M, int N, int count) {
        int startY = x;
        int answer = x;

        for (int i = 0; i < N; i++) {
            int yy = startY % N == 0 ? N : startY % N;
            if (yy == y) break;
            startY = yy + M;
            answer += M;
        }

        if (answer > count) return -1;

        return answer;
    }

    private static int getMaxCount(int m, int n) {
        return m * n / gcd(m, n);
    }

    private static int gcd(int m, int n) {
        if (n == 0) return m;
        else return gcd(n, m % n);
    }
}
