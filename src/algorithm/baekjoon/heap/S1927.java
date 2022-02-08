package heap;

import java.io.*;
import java.util.PriorityQueue;

// baekJoon 1927 silver1 최소 힙
public class S1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0 && minHeap.isEmpty()) bw.write("0\n");
            else if (x == 0) bw.write(minHeap.poll() + "\n");
            else minHeap.offer(x);
        }
        bw.flush();
        bw.close();
    }
}
