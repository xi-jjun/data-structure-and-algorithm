package algorithm.baekjoon.tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// baekJoon 2263 Gold2 - 트리의 순회
public class G2263 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[] in, post, inIdx;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        in = new int[N + 1];
        post = new int[N + 1];
        inIdx = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; st.hasMoreTokens(); i++) {
            in[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; st.hasMoreTokens(); i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            inIdx[in[i]] = i;
        }

        solve(0, N, 0, N);

        bw.flush();
        bw.close();
        br.close();
    }

//    private static void solve(int[] in, int[] post) throws IOException {
    private static void solve(int inStart, int inEnd, int postStart, int postEnd) throws IOException {
//        if (post.length == 0 || in.length == 0) return;
        if (postEnd - postStart <= 0 || inEnd - inStart <= 0) return;
//        int root = post[post.length - 1];
        int root = post[postEnd - 1];
        bw.write(root + " ");
//        if (post.length <= 1 || in.length <= 1) return;

        int rootIdx = inIdx[root];
//        int[] leftIn = Arrays.copyOfRange(in, 0, rootIdx); // 0~rootIdx
//        int[] leftPost = Arrays.copyOfRange(post, 0, leftIn.length); // 0~rootIdx
        int leftLength = rootIdx - inStart;
        solve(inStart, rootIdx, postStart, postStart + leftLength);
//        solve(leftIn, leftPost);

//        int[] rightIn = Arrays.copyOfRange(in, rootIdx + 1, in.length); // rootIdx + 1 ~ in.length
//        int[] rightPost = Arrays.copyOfRange(post, leftIn.length, post.length - 1); // rootIdx ~ post.length - 1
        solve(rootIdx + 1, inEnd, postStart + leftLength, postEnd - 1);
//        solve(rightIn, rightPost);
    }
}
