package Greedy;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

// baekJoon 1339 Gold4 단어 수학
public class G1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> weight = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String[] letters = br.readLine().split("");

            for (int k = letters.length - 1; k >= 0; k--) {
                int add = (int) Math.pow(10, letters.length - k - 1);

                if (weight.get(letters[k]) == null) weight.put(letters[k], add);
                else weight.replace(letters[k], weight.get(letters[k]) + add);
            }
        }

        LinkedHashMap<String, Integer> reverseSorted = new LinkedHashMap<>();

        weight.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSorted.put(x.getKey(), x.getValue()));

        AtomicInteger result = new AtomicInteger();
        AtomicInteger num = new AtomicInteger(9);

        reverseSorted.forEach((key, value) -> {
            result.addAndGet(num.get() * value);
            num.getAndDecrement();
        });

        bw.write(result + "");

        bw.flush();
        bw.close();
    }
}
