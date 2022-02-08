package Queue;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

// baekJoon 1158 silver5
public class S1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nums = br.readLine().split(" ");
        int N = Integer.parseInt(nums[0]);
        int K = Integer.parseInt(nums[1]);

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) q.offer(i);

        bw.write("<");
        for (int i = 1; i <= N; i++) {
            if (q.isEmpty()) break;

            if (i == K) {
                i = 0;
                bw.write(q.poll() + "");
            } else q.offer(q.poll());

            if (!q.isEmpty() && i == 0) bw.write(", ");
        }
        bw.write(">");

        bw.flush();
        bw.close();
    }
}
