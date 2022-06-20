package algorithm.baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://st-lab.tistory.com/281
// baekJoon 1300 Gold2 K번째 수
public class G1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long start = 1;
        long end = k;

        long answer = 1;

        while (start < end) {
            long std = (start + end) / 2;

            long count = 0;
            // std 보다 작은 값 개수 구하기
            for (int i = 1; i <= N; i++) {
                count += Math.min(std / i, N); // i 행에서 std 보다 작은 값의 개수를 count 에 더한다.
            }

            // 1. count(std 보다 작은 값 개수)가 원하는 k 보다 작을 때
            //      → 최소 경계를 std + 1 로 이동시킨다.
            if (count < k) {
                start = std + 1;
            }


            // 2. count 가 원하는 k 보다 같거나 클 때,
            //      → 최대 경계를 std 로 이동.
            // 정답이 될 수 있는 조건을 충족. while 이 start > end 인 순간 종료되기에 여기서 answer 갱신.
            else {
                end = std;
            }
        }
        System.out.println(end);

        br.close();
    }
}
