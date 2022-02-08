package Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

// baekJoon 1676 Silver4 팩토리얼 0 개수
public class S1676 {
    static BigInteger factorial(int n) {
        if (n <= 1) return new BigInteger("1");
        else {
            BigInteger nn = new BigInteger(String.valueOf(n));
            BigInteger fact = new BigInteger(String.valueOf(factorial(n-1)));
            return nn.multiply(fact);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 0;
        String answer = factorial(N) + "";
        for (int i = answer.length()-1; i >= 0; i--) {
            if(answer.charAt(i) == '0')count++;
            else break;
        }

        System.out.println(count);
    }
}
