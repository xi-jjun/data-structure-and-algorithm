package BinarySearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// baekJoon 1920 silver4 수 찾기
public class S1920 {

    static int N, M;
    static int[] A;

    static int binarySearch(int checkNum, int start, int end) {
        if (start > end) return -1;

        int mid = (start + end) / 2;
        if (A[mid] == checkNum) return mid;
        else if (A[mid] < checkNum) return binarySearch(checkNum, mid + 1, end);
        else return binarySearch(checkNum, start, mid - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st1.nextToken());
        Arrays.sort(A);

        M = Integer.parseInt(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int check = Integer.parseInt(st2.nextToken());
            int result = binarySearch(check, 0, A.length - 1) == -1 ? 0 : 1;
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }
}
