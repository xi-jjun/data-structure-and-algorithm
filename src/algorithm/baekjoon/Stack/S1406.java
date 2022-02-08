package Stack;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// baekJoon 1406 silver3 에디터-스터디(Stack)
public class S1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        Deque<Character> front = new ArrayDeque<>();
        Stack<Character> back = new Stack<>();

        for (String s : str.split("")) {
            front.push(s.charAt(0));
        }

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            String[] cmdLine = br.readLine().split(" ");
            char cmd = cmdLine[0].charAt(0);

            switch (cmd) {
                case 'L':
                    if (!front.isEmpty()) back.push(front.pop());
                    break;
                case 'D':
                    if (!back.isEmpty()) front.push(back.pop());
                    break;
                case 'B':
                    if (!front.isEmpty()) front.pop();
                    break;
                case 'P':
                    front.push(cmdLine[1].charAt(0));
                    break;
            }
        }

        while (!front.isEmpty()) bw.write(front.pollLast());
        while (!back.isEmpty()) bw.write(back.pop());

        bw.flush();
        bw.close();
    }
}
