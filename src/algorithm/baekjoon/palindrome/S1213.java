package algorithm.baekjoon.palindrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 1213 Silver3 팰린드롬 만들기
public class S1213 {
    static int oddIndex = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] line = br.readLine().toCharArray();
        int[] count = new int[26];

        for (char c : line) {
            count[c - 'A']++;
        }

        if (!valid(line, count)) {
            System.out.println("I'm Sorry Hansoo");
        } else if (line.length % 2 == 0) {
            StringBuilder answer = new StringBuilder();
            makeHead(count, answer);

            makeLast(count, answer);
            System.out.println(answer);
        } else {
            StringBuilder answer = new StringBuilder();
            makeHead(count, answer);

            answer.append((char) (oddIndex + 'A'));
            count[oddIndex]--;

            makeLast(count, answer);
            System.out.println(answer);
        }
    }

    private static void makeHead(int[] count, StringBuilder answer) {
        for (int i = 0; i <= 25; i++) {
            answer.append(
                    String.valueOf((char) (i + 'A')).repeat(count[i] / 2)
            );

            count[i] = count[i] % 2 == 0 ? count[i] / 2 : count[i] / 2 + 1;
        }
    }

    private static void makeLast(int[] count, StringBuilder answer) {
        for (int i = 25; i >= 0; i--) {
            answer.append(
                    String.valueOf((char) (i + 'A')).repeat(count[i])
            );
        }
    }

    private static boolean valid(char[] line, int[] count) {
        if (line.length % 2 == 0) {
            return validateWhenEven(count);
        }

        return validateWhenOdd(count);
    }

    private static boolean validateWhenOdd(int[] count) {
        int oddCount = 0;
        int seq = 0;
        for (int cnt : count) {
            if (cnt % 2 == 1) {
                oddCount++;
                oddIndex = seq;
            }
            seq++;
        }
        return oddCount == 1;
    }

    private static boolean validateWhenEven(int[] count) {
        for (int cnt : count) {
            if (cnt % 2 == 1) {
                return false;
            }
        }

        return true;
    }
}
