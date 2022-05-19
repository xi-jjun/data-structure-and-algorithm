package algorithm.baekjoon.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// baekJoon 4256 Gold3 - 트리
public class G4256 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] pre = new int[N];
            for (int i = 0; i < N; i++) {
                pre[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int[] in = new int[N];
            for (int i = 0; i < N; i++) {
                in[i] = Integer.parseInt(st.nextToken());
            }

            solve(pre, in);
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void solve(int[] pre, int[] in) throws IOException {
        if (pre.length <= 2) {
            for (int i = pre.length - 1; i >= 0; i--) {
                bw.write(pre[i] + " ");
            }
            return;
        }

        int root = pre[0];
        int rootIndex = getIndex(root, in); // in 배열에서 pre[0](root) 에 해당하는 index 구하기

        // left subtree
        int[] leftIn = Arrays.copyOfRange(in, 0, rootIndex);
        int[] leftPre = Arrays.copyOfRange(pre, 1, 1 + leftIn.length);
        solve(leftPre, leftIn);

        // right subtree
        int[] rightIn = Arrays.copyOfRange(in, rootIndex + 1, in.length);
        int[] rightPre = Arrays.copyOfRange(pre, 1 + leftIn.length, pre.length);
        solve(rightPre, rightIn);

        bw.write(pre[0] + " ");
    }

    private static int getIndex(int target, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (target == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
