package dynamicProgramming;

import java.math.BigInteger;
import java.util.Scanner;

// baekJoon 11726 silver3 2xn 타일링
public class S11726 {
    static BigInteger dp(int n) {
        BigInteger[] d = new BigInteger[1001];
        d[1] = new BigInteger("1");
        d[2] = new BigInteger("2");

        for (int i = 3; i <= n; i++) {
            d[i] = d[i - 1].add(d[i - 2]);
        }

        return d[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        System.out.println(dp(n).mod(new BigInteger("10007")));
    }
}
