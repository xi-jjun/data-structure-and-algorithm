package algorithm.baekjoon.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class G6597 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        while (true) {
            String line = br.readLine();
            if (line == null || line.length() == 0) break;
            String[] sets = line.split(" ");

            char[] pre = sets[0].toCharArray();
            char[] in = sets[1].toCharArray();

            solve(pre, in);

            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void solve(char[] pre, char[] in) throws IOException {
        if (pre.length <= 2) {
            for (int i = pre.length - 1; i >= 0; i--) {
                bw.write(pre[i] + "");
            }
            return;
        }

        char root = pre[0];
        int rootIdx = findIdx(root, in);

        char[] leftIn = Arrays.copyOfRange(in, 0, rootIdx);
        char[] leftPre = Arrays.copyOfRange(pre, 1, 1 + leftIn.length);
        solve(leftPre, leftIn);

        char[] rightIn = Arrays.copyOfRange(in, rootIdx + 1, in.length);
        char[] rightPre = Arrays.copyOfRange(pre, 1 + leftIn.length, pre.length);
        solve(rightPre, rightIn);

        bw.write(pre[0] + "");
    }

    private static int findIdx(char target, char[] arr) {
        int ret = 0;
        for (char data : arr) {
            if (data == target) return ret;
            ret++;
        }
        return -1;
    }
}
