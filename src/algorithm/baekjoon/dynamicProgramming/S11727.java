package dynamicProgramming;

import java.util.Scanner;

// baekJoon 11727 silver3 - 2xn 타일링2
// https://www.youtube.com/watch?v=HTgcW34AnVQ 참고
public class S11727 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
        }
        System.out.println(dp[N]);
    }
}
