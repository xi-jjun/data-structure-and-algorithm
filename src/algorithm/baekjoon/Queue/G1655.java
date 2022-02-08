package Queue;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

// baekJoon 1655 Gold2 가운데를 말해요
// https://seokjin2.tistory.com/37 참고.
public class G1655 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        maxHeap.add(Integer.parseInt(br.readLine()));
        if (N == 1) bw.write(maxHeap.peek() + "\n");
        bw.write(maxHeap.peek() + "\n");

        for (int i = 1; i < N; i++) {
            int data = Integer.parseInt(br.readLine());
            int mid = maxHeap.peek();

            if (data < mid) maxHeap.add(data);
            else minHeap.add(data);

            if (maxHeap.size() >= minHeap.size() + 2) minHeap.add(maxHeap.poll());
            else if (minHeap.size() > maxHeap.size()) maxHeap.add(minHeap.poll());

            bw.write(maxHeap.peek() + "\n");
        }

        bw.flush();
        bw.close();
    }
}
