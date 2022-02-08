package Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 1188 Gold5 - 음식평론가
public class G1188 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]); // sso si G
        int M = Integer.parseInt(line[1]); // pyeong loan ga

        int answer = 0;
        if (N % M != 0) {
            int g = gcd(N, M);
            answer = M - g;
        }
        System.out.println(answer);
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
