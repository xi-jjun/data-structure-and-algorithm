package Stack;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

// baekJoon 2493 Gold5 탑
// https://www.acmicpc.net/board/view/70952 참고
public class G2493 {
    static int N;
    static StringBuilder sb;
    static int[] tower;

    static class Tower {
        int index;
        int height;

        public Tower(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 0< N < 50만

        tower = new int[N + 1];
        int[] ans = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) tower[i] = Integer.parseInt(st.nextToken());

        Stack<Tower> stack = new Stack<>();

        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty()) {
                if (stack.peek().height > tower[i]) {
                    ans[i] = stack.peek().index;
                    break;
                } else stack.pop();
            }

            stack.push(new Tower(i, tower[i]));
        }

        for (int i = 1; i <= N; i++) bw.write(ans[i] + " ");

        bw.flush();
        bw.close();
    }
}
