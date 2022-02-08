package Queue;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

// baekJoon 10845 silver4 큐 - class 2+
public class S10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> q = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            String[] line = br.readLine().split(" ");
            String cmd;
            int pushValue = -1;
            if (line.length == 1) {
                cmd = line[0];
            } else {
                cmd = line[0];
                pushValue = Integer.parseInt(line[1]);
            }

            if (cmd.equals("push")) {
                q.offer(pushValue);
            } else if (cmd.equals("front")) {
                if (!q.isEmpty()) bw.write(q.peekFirst() + "\n");
                else bw.write("-1\n");
            } else if (cmd.equals("back")) {
                if (!q.isEmpty()) bw.write(q.peekLast() + "\n");
                else bw.write("-1\n");
            } else if (cmd.equals("size")) bw.write(q.size() + "\n");
            else if (cmd.equals("empty")) bw.write((q.isEmpty() ? 1 : 0) + "\n");
            else if (cmd.equals("pop")) {
                if (!q.isEmpty()) bw.write(q.removeFirst() + "\n");
                else bw.write("-1\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
