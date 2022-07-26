package algorithm.baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// two pointer + binary search 진짜 코테에서 많이 본 문제
// https://imnotabear.tistory.com/382
// baekJoon 1253 Gold4 좋다
public class G1253 {
    static int N, answer;
    static int[] a;

    public static void main(String[] args) throws IOException {
        getInput();

        solution();

        System.out.println(answer);
    }

    private static void solution() {
        Arrays.sort(a);

        for (int i = 0; i < N; i++) {
            int nowSearchNumber = a[i];
            search(i, nowSearchNumber);
        }
    }

    private static void search(int i, int nowSearchNumber) {
        int left = 0;
        int right = N - 1;
        while (left < right) { // 같아도 안됨
            int compare = a[left] + a[right];
            if (nowSearchNumber == compare) {
                if (i != left && i != right) {
                    answer++;
                    break;
                }

                left = i == left ? left + 1 : left;
                right = i == right ? right - 1 : right;
            } else if (nowSearchNumber < compare) {
                right--;
            } else {
                left++;
            }
        }
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; st.hasMoreTokens(); i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
    }
}
