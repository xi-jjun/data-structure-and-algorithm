package BruteForcing;

import java.util.Scanner;

// baekJoon 2231 Bronze3 분해합 - class2++
public class B2231 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            int constructor = i;
            while (constructor != 0) {
                sum += constructor % 10;
                constructor /= 10;
            }

            if (sum + i == N) {
                System.out.println(i);
                return;
            }
        }

        System.out.println("0");
    }
}
