package dynamicProgramming;

import java.io.*;

public class S9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        Long[] arr = new Long[101];
        arr[1] = arr[2] = arr[3] = 1L;
        arr[4] = arr[5] = 2L;

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            if (arr[N] == null) {
                for (int i = 6; i <= N; i++) {
                    arr[i] = arr[i - 1] + arr[i - 5];
                }
            }
            bw.write(arr[N] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
