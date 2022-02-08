package Queue;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

// baekJoon 11286 silver1 절댓값 힙
public class S11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        TreeMap<Integer, Integer> pos = new TreeMap<>();
        TreeMap<Integer, Integer> neg = new TreeMap<>(Collections.reverseOrder());

        while (N-- > 0) {
            int x = Integer.parseInt(br.readLine());

            if (x != 0) {
                if (x < 0) neg.put(x, neg.getOrDefault(x, 0) + 1);
                else pos.put(x, pos.getOrDefault(x, 0) + 1);
            } else {
                if (neg.isEmpty() && pos.isEmpty()) bw.write("0\n");
                else if (neg.isEmpty()) {
                    Map.Entry<Integer, Integer> min = getEntry(pos);
                    bw.write(min.getKey() + "\n");
                } else if (pos.isEmpty()) {
                    Map.Entry<Integer, Integer> min = getEntry(neg);
                    bw.write(min.getKey() + "\n");
                } else {
                    Map.Entry<Integer, Integer> min1 = pos.firstEntry();
                    Map.Entry<Integer, Integer> min2 = neg.firstEntry();
                    boolean b = Math.abs(min1.getKey()) < Math.abs(min2.getKey()); // false: delete pos

                    if (!b) {
                        if (min2.getValue() - 1 == 0) neg.pollFirstEntry();
                        else neg.replace(min2.getKey(), min2.getValue() - 1);
                        bw.write(min2.getKey() + "\n");
                    } else {
                        if (min1.getValue() - 1 == 0) pos.pollFirstEntry();
                        else pos.replace(min1.getKey(), min1.getValue() - 1);
                        bw.write(min1.getKey() + "\n");
                    }
                }
            }

        }
        bw.flush();
        bw.close();
    }

    private static Map.Entry<Integer, Integer> getEntry(TreeMap<Integer, Integer> map) {
        Map.Entry<Integer, Integer> min = map.firstEntry();
        if (min.getValue() - 1 == 0) map.pollFirstEntry();
        else map.replace(min.getKey(), min.getValue() - 1);
        return min;
    }
}
