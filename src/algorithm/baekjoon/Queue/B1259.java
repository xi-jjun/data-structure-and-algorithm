package Queue;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

// baekJoon 1259 Bronze1 펠린드롬  - class2+
public class B1259 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            boolean ok = true;
            String num = br.readLine();
            if (num.equals("0")) break;
            Deque<Integer> numbers = new ArrayDeque<>();

            for (String n : num.split("")) {
                numbers.offerLast(Integer.parseInt(n));
            }

            while (!numbers.isEmpty() && numbers.size() != 1) {
                if (numbers.pollFirst() != numbers.pollLast()) ok = false;
            }

            if (ok) bw.write("yes\n");
            else bw.write("no\n");
        }

        bw.flush();
        bw.close();
    }
}
