package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

// baekJoon 10610 silver5 30
public class S10610 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] N = br.readLine().split("");
        StringBuilder sb = new StringBuilder();

        Arrays.sort(N, Collections.reverseOrder());
        if (!N[N.length - 1].equals("0")) System.out.println("-1");
        else {
            int sum = 0;
            for (String nn : N) {
                sum += Integer.parseInt(nn);
                sb.append(nn);
            }
            if (sum % 3 == 0) System.out.println(sb);
            else System.out.println("-1");
        }
    }
}
