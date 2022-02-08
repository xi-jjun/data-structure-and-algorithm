package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 1254 silver1 팰린드롬 만들기
public class S1254 {
    static String[] str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().split("");
        int len = str.length;
        int result = 0;

        if (len == 1) {
            System.out.println("1");
            return;
        }

        int a = len / 2 - 1;
        int b = len % 2 == 0 ? len / 2 : len / 2 + 1;

        for (int i = 0; i < len / 2; i++) {
            if (b >= str.length) {
                result = a + 1 + str.length;
                System.out.println(result);
                return;
            }
            if (!str[a].equals(str[b])) {
                i = 0;
                len++;
                a = len / 2 - 1;
                b = len % 2 == 0 ? len / 2 : len / 2 + 1;
            } else {
                --a;
                ++b;
            }
        }

        System.out.println(len);
    }
}
