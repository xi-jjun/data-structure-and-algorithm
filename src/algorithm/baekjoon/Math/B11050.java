package Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 11050 Bronze1 이항 계수 1 - class2+
public class B11050 {
    static long factorial(int num, int count, int depth) {
        if (num == 1) return 1;
        if (count == depth) return num;
        else return num * factorial(num - 1, count + 1, depth);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (K == 0) {
            System.out.println(N);
            return;
        }

        System.out.println(factorial(N, 1, K) / factorial(K, 1, K));
    }
}
