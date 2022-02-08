package Implementation;

import java.io.*;
import java.util.*;

// baekJoon 2108 silver4 통계학
public class S2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] avg = new int[N];
        Map<Integer, Integer> count = new HashMap<>(); // 최빈값

        for (int i = -4000; i <= 4000; i++) count.put(i, 0); // -4000:0개 , -3999:0개 ...

        double sum = 0;
        for (int i = 0; i < N; i++) {
            avg[i] = Integer.parseInt(br.readLine());
            count.replace(avg[i], count.get(avg[i]) + 1);
            sum += avg[i];
        }

        int result1 = (int) Math.round(sum / N); // 산술평균

        Arrays.sort(avg);
        int result2 = avg[N / 2]; // 중앙값
        int result3 = -1; // 최빈값
        int result4 = avg[N - 1] - avg[0]; // 최대-최소 차이

        Integer maxValue = Collections.max(count.values());

        ArrayList<Integer> freq = new ArrayList<>();

        for (int i = -4000; i <= 4000; i++) {
            if (count.get(i).equals(maxValue)) freq.add(i);
        }

        if (freq.size() == 1) result3 = freq.get(0);
        else result3 = freq.get(1);

        bw.write(result1 + "\n");
        bw.write(result2 + "\n");
        bw.write(result3 + "\n");
        bw.write(result4 + "\n");

        bw.flush();
        bw.close();
    }
}
