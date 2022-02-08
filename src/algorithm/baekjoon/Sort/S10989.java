package Sort;

import java.io.*;

// baekJoon 10989 silver5
public class S10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] sort = new int[10001];

        for (int i = 1; i <= N; i++) sort[Integer.parseInt(br.readLine())]++;

        for (int i = 1; i <= 10000; i++) {
            while (sort[i]-- != 0) bw.write(i + "\n");
        }

        bw.flush();
        bw.close();
    }
}
