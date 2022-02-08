package Stack;

import java.io.*;
import java.util.Stack;

// baekJoon 1874 silver3 스택 수열
// https://st-lab.tistory.com/182 코드를 참고.
// 주석처리된 코드는 100% 찍고 틀렸음.
public class S1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
//        int[] data = new int[n];
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder("");

        int start = 0;
        while (n-- > 0) {
            int value = Integer.parseInt(br.readLine());
            boolean check = false;

            if (value > start) {
                for (int i = start + 1; i <= value; i++) {
                    stack.push(i);
                    sb.append("+\n");
                }
                start = value;
            } else if (stack.peek() != value) {
                System.out.println("NO");
                return;
            }

            stack.pop();
            sb.append("-\n");
        }

        System.out.println(sb);

//        if (n == 1) {
//            bw.write("+\n-");
//            return;
//        }

//        for (int i = 1; i <= n; i++) data[i - 1] = Integer.parseInt(br.readLine());


//        StringBuilder result = new StringBuilder("+\n");
//        Stack<Integer> st = new Stack<>();
//        st.push(1);
//        int k = 2;
//        boolean ccc = false;
//
//        for (int i = 1; i <= n; i++) {
//            int num = ans[i - 1];
//
//            while (true) {
//                if (k > n + 1) {
//                    ccc = true;
//                    break;
//                }
//                if (st.isEmpty()) {
//                    st.push(k++);
//                    result.append("+\n");
//                }
//                if (num > st.peek()) {
//                    st.push(k++);
//                    result.append("+\n");
//                } else if (num < st.peek()) {
//                    st.pop();
//                    result.append("-\n");
//                } else {
//                    st.pop();
//                    result.append("-\n");
//                    break;
//                }
//            }
//        }
//        if (!ccc) System.out.println(result);
//        else bw.write("NO");

//        bw.flush();
//        bw.close();
    }
}
