package Sort;

import java.io.*;

// baekJoon 1427 silver5
public class S1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String num = br.readLine();
        int[] numArr = new int[10];
        int index = 0;

        for (String n : num.split("")) numArr[Integer.parseInt(n)]++;

        for (int i = 9; i >= 0; i--) {
            while (numArr[i]-- != 0) bw.write(i + "");
        }

        bw.flush();
        bw.close();
    }
}
