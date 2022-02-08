package Math;

import java.io.*;

// baekJoon 2609 silver5 최소/최대 공배수 공약수
public class S2609 {
    static int gcd(int a, int b) {
        if (a % b == 0) return b;
        else return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(" ");
        int A = Integer.parseInt(line[0]);
        int B = Integer.parseInt(line[1]);

        int result = gcd(A, B);
        int result2 = (A * B) / result;

        bw.write(result + "\n" + result2 + "\n");

        bw.flush();
        bw.close();
    }
}
