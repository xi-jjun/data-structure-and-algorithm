package algorithm.baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// baekJoon 10815 silver5 숫자 카드
public class S10815 {
    static int N, M;
    static int[] sang;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        sang = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; st.hasMoreTokens(); i++) {
            sang[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sang);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int target = Integer.parseInt(st.nextToken());
            if (binarySearch(0, N, target)) {
                bw.write("1 ");
            } else {
                bw.write("0 ");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean binarySearch(int start, int end, int target) {
        if (start > end) {
            return false;
        }

        int mid = (start + end) / 2;
        if (mid >= N) return false;

        if (sang[mid] == target) {
            return true;
        } else if (sang[mid] > target) {
            return binarySearch(start, mid - 1, target);
        } else {
            return binarySearch(mid + 1, end, target);
        }
    }
}
