package algorithm.baekjoon.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class G1701 {
    static String str;
    static Set<String> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        int answer = 0;
        for (int len = 0; len < str.length(); len++) {
            String checker = str.substring(len);
            int[] p = makeTable(checker.toCharArray());

            for (int number : p) {
                answer = Math.max(answer, number);
            }
        }
        System.out.println(answer);
    }

    private static int[] makeTable(char[] checker) {
        int[] ret = new int[checker.length];
        int i = 0;
        for (int j = 1; j < checker.length; j++) {
            while (i > 0 && checker[i] != checker[j]) {
                i = ret[i - 1];
            }

            if (checker[i] == checker[j]) {
                ret[j] = ++i;
            }
        }
        return ret;
    }
}
