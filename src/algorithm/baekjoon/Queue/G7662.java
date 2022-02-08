package Queue;

import java.io.*;
import java.util.*;

// baekJoon 7662 Gold5 이중 우선순위 큐
// Java 기본기가 부족해서 푸는데 오래걸린 문제
public class G7662 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());

            while (k-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String op = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if (op.charAt(0) == 'I') {
                    map.put(n, map.getOrDefault(n, 0) + 1);
                } else if (op.charAt(0) == 'D') {
                    if (map.isEmpty()) continue;

                    if (n == 1) {
                        Map.Entry<Integer, Integer> lastEntry = map.lastEntry();
                        if (lastEntry.getValue() == 1) map.pollLastEntry();
                        else map.replace(lastEntry.getKey(), lastEntry.getValue() - 1);
                    } else {
                        Map.Entry<Integer, Integer> firstEntry = map.firstEntry();
                        if (firstEntry.getValue() == 1) map.pollFirstEntry();
                        else map.replace(firstEntry.getKey(), firstEntry.getValue() - 1);
                    }
                }
            }


            if (map.size() == 0) bw.write("EMPTY\n");
            else bw.write(map.lastKey() + " " + map.firstKey() + "\n");
        }

        bw.flush();
        bw.close();
    }
}
