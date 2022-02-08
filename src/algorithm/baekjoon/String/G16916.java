package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 16916 Gold4 부분 문자열
// https://www.youtube.com/watch?v=yWWbLrV4PZ8 참고.
public class G16916 {
    static int[] makeTable(String pattern) { // failure function
        int patternSize = pattern.length();
        int[] table = new int[patternSize];
        int j = 0;

        for (int i = 1; i < patternSize; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) j = table[j - 1];
            if (pattern.charAt(i) == pattern.charAt(j)) table[i] = ++j;
        }

        return table;
    }

    static boolean findTarget(String parent, String target) {
        int[] table = makeTable(target);
        int parentSize = parent.length();
        int targetSize = target.length();
        int k = 0;

        for (int i = 0; i < parentSize; i++) {
            while (k > 0 && parent.charAt(i) != target.charAt(k)) k = table[k - 1];
            if (parent.charAt(i) == target.charAt(k)) {
                if (k == targetSize - 1) return true;
                else k++;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String search = br.readLine();
        final String TARGET = br.readLine();

        if (findTarget(search, TARGET)) System.out.println("1");
        else System.out.println("0");
    }
}
