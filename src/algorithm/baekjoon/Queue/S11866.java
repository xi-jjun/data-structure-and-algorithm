package Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// baekJoon 11866 silver4 요세푸스 문제 0 - class2+
public class S11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] line = br.readLine().split(" ");
        sb.append("<");
        Queue<Integer> q = new ArrayDeque<>();

        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);

        for (int i = 1; i <= N; i++) q.offer(i);

        while (!q.isEmpty()) {
            int count = 0;

            while (count != K - 1) {
                count++;
                q.offer(q.poll());
            }
            sb.append(q.poll());
            if (q.isEmpty()) break;
            sb.append(", ");
        }
        sb.append(">");
        System.out.println(sb.toString());
    }
}
