package Stack;

import java.io.*;
import java.util.Stack;

// baekJoon 10828 silver4
public class S10828 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void func(String line, Stack<Integer> s) throws IOException {
        if (line.contains("push")) {
            String[] l = line.split(" ");
            s.push(Integer.parseInt(l[1]));
        } else if (line.contains("pop")) {
            if (s.isEmpty()) bw.write("-1\n");
            else bw.write(s.pop() + "\n");
        } else if (line.contains("size")) {
            bw.write(s.size() + "\n");
        } else if (line.contains("empty")) {
            if (s.isEmpty()) bw.write("1\n");
            else bw.write("0\n");
        } else if (line.contains("top")) {
            if (s.isEmpty()) bw.write("-1\n");
            else bw.write(s.peek() + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        Stack<Integer> s = new Stack<>();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            func(line, s);
        }

        bw.flush();
        bw.close();
    }
}
