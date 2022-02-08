package Stack;

import java.io.*;
import java.util.Stack;

// baekJoon 4949 silver4 균형잡힌 세상
public class S4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String line = br.readLine();
            if (line.equals(".")) break;
            boolean mode = true;

            Stack<String> stack = new Stack<>();

            for (String l : line.split("")) {
                if (l.equals("(") || l.equals("[")) stack.push(l);
                else if (l.equals(")") || l.equals("]")) {
                    if (stack.isEmpty()) {
                        mode = false;
                        break;
                    }
                    else if (l.equals(")") && stack.peek().equals("(")) stack.pop();
                    else if (l.equals("]") && stack.peek().equals("[")) stack.pop();
                    else {
                        mode = false;
                        break;
                    }
                }
            }
            if (mode && stack.isEmpty()) bw.write("yes\n");
            else bw.write("no\n");
        }

        bw.flush();
        bw.close();
    }
}
