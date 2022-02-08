package Implementation;

import java.io.*;

// baekJoon 10250 Bronze3 ACM호- class2+
public class B10250 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringBuilder sb = new StringBuilder();
            String[] line = br.readLine().split(" ");
            int H = Integer.parseInt(line[0]);
            int W = Integer.parseInt(line[1]);
            int N = Integer.parseInt(line[2]);

            int first = N % H == 0 ? H : N % H;
            int third = N % H == 0 ? N / H : N / H + 1;

            String result = third >= 10 ? sb.append(first).append(third).toString() : sb.append(first).append(0).append(third).toString();

            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
}
