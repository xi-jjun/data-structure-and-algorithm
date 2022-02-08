package Stack;

import java.io.*;

// baekJoon 9935 Gold4 문자열 폭⭐발️
// https://steady-coding.tistory.com/49 참고
public class G9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        String erase = br.readLine();

        int index = 0;
        char[] result = new char[line.length()];

        for (String l : line.split("")) {
            if (index < erase.length() - 1) {
                result[index++] = l.charAt(0);
            } else {
                if (l.charAt(0) == erase.charAt(erase.length() - 1)) {
                    int presentIndex = index;
                    boolean t = true;

                    for (int k = erase.length() - 2; k >= 0; k--, presentIndex--) {
                        if (result[presentIndex - 1] != erase.charAt(k)) {
                            result[index++] = l.charAt(0);
                            t = false;
                            break;
                        }
                    }
                    if (t) index = presentIndex;
                } else result[index++] = l.charAt(0);
            }
        }

        if (index <= 0) bw.write("FRULA");
        else for (int i = 0; i < index; i++) bw.write(result[i] + "");

        bw.flush();
        bw.close();
    }
}
