package hash;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// baekJoon 9357 silver3 - 패션왕 신해빈
public class S9357 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            Map<String, Integer> list = new HashMap<>();
            while (n-- > 0) {
                String[] line = br.readLine().split(" ");
                list.put(line[1], list.getOrDefault(line[1], 0) + 1);
            }
            int[] arr = new int[list.size()];
            int[] datas = new int[list.size()];

            int idx = 0;
            for (Map.Entry<String, Integer> entry : list.entrySet()) {
                arr[idx++] = entry.getValue();
            }

            int answer = 1;
            for (int i = 0; i < datas.length; i++) {
                answer *= arr[i] + 1;
            }

            bw.write((answer - 1) + "\n");
        }

        bw.flush();
        bw.close();
    }
}
