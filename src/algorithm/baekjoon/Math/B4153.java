package Math;

import java.io.*;

// baekJoon 4153 Bronze3 직각삼각형 - class2+
public class B4153 {
    static boolean isTriangle(int a, int b, int c) {
        int aa = (int) Math.pow(a, 2);
        int bb = (int) Math.pow(b, 2);
        int cc = (int) Math.pow(c, 2);

        return aa + bb == cc;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String[] line = br.readLine().split(" ");
            if (line[0].equals("0")) break;

            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int c = Integer.parseInt(line[2]);

            int C = a > b ? (Math.max(a, c)) : (Math.max(b, c));
            int B = C == a ? b : (C == b ? c : a);
            int A = a + b + c - C - B;

            if (isTriangle(A, B, C)) bw.write("right\n");
            else bw.write("wrong\n");
        }

        bw.flush();
        bw.close();
    }
}
