package Implementation;

import java.io.*;
import java.util.*;

// baekJoon 1966 silver3
public class S1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            Queue<Integer> q = new LinkedList<>();
            Stack<Integer> st = new Stack<>();
            String[] line1 = br.readLine().split(" ");
            String[] line2 = br.readLine().split(" ");

            int N = Integer.parseInt(line1[0]);
            int index = Integer.parseInt(line1[1]);
            int value = Integer.parseInt(line2[index]);

            for (String s : line2) q.offer(Integer.parseInt(s));
            Arrays.sort(line2);
            for (String s : line2) st.push(Integer.parseInt(s));

            int location = index;
            int result = 0;
            while (!q.isEmpty()) {
                if (st.peek().equals(q.peek())) {
                    result++;
                    if (q.poll() == value && location == 0) break;
                    location--;
                    st.pop();
                } else {
                    location = location == 0 ? q.size() - 1 : location - 1;
                    q.offer(q.poll());
                }
            }
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }
}
