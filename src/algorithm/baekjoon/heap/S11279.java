package heap;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class S11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        while (N-- > 0) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                if (pq.isEmpty()) bw.write("0\n");
                else bw.write(pq.poll() + "\n");
            } else pq.offer(n);
        }

        bw.flush();
        bw.close();
    }
}
