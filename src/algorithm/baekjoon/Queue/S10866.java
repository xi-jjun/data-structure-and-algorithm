package Queue;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

// baekJoon 10866 silver4 덱 - class2+
public class S10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> dq = new ArrayDeque<>();
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            String[] line = br.readLine().split(" ");
            if (line.length == 1) {
                String cmd = line[0];
                if (cmd.equals("front")) {
                    if (dq.isEmpty()) bw.write("-1\n");
                    else bw.write(dq.peekFirst() + "\n");
                } else if (cmd.equals("back")) {
                    if (dq.isEmpty()) bw.write("-1\n");
                    else bw.write(dq.peekLast() + "\n");
                } else if (cmd.equals("size")) bw.write(dq.size() + "\n");
                else if (cmd.equals("empty")) bw.write((dq.isEmpty() ? 1 : 0) + "\n");
                else if (cmd.equals("pop_front")) {
                    if (dq.isEmpty()) bw.write("-1\n");
                    else bw.write(dq.pollFirst() + "\n");
                } else if (cmd.equals("pop_back")) {
                    if (dq.isEmpty()) bw.write("-1\n");
                    else bw.write(dq.pollLast() + "\n");
                }
            } else {
                String cmd = line[0];
                int value = Integer.parseInt(line[1]);

                if (cmd.equals("push_back")) dq.offerLast(value);
                else dq.offerFirst(value);
            }
        }

        bw.flush();
        bw.close();
    }
}
