package algorithm.baekjoon.Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class G2981 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        List<Integer> numbers = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());
            numbers.add(number);
            min = Math.min(min, number);
        }

        numbers.sort(Comparator.comparingInt(o -> o));

        for (int i = 0; i < numbers.size(); i++) {
            numbers.set(i, numbers.get(i) - min);
        }

        int now = gcd(numbers.get(0), numbers.get(1));
        for (int i = 2; i < numbers.size(); i++) {
            now = gcd(numbers.get(i), now);
        }

        for (int i = 2; i <= now; i++) {
            if (now % i == 0) bw.write(i + " ");
        }

        bw.flush();
        bw.close();
    }

    private static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }
}
