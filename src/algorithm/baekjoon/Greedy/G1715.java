package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// baekJoon 1715 Gold4 카드 정렬하기
public class G1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        while (N-- > 0) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        if (pq.size() == 1) {
            System.out.println("0");
            return;
        }

        int answer = 0;
        while (pq.size() != 1) {
            int added = pq.poll() + pq.poll();
            pq.offer(added);
            answer += added;
        }

        System.out.println(answer);
    }
}
