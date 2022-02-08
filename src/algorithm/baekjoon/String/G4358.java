package String;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// baekJoon 4358 Gold5 생태학
// 출력형식은 무조건 소수점 4번째까지...
public class G4358 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, Integer> info = new HashMap<>();
        double total = 0;

        while (true) {
            String tree = br.readLine();
            if (tree == null || tree.length() == 0) break;
            total++;

            info.put(tree, info.getOrDefault(tree, 0) + 1);
        }

        Object[] keySort = info.keySet().toArray();
        Arrays.sort(keySort);

        for (Object key : keySort)
            bw.write(key + " " + String.format("%.4f",(double) Math.round(info.get((String) key) / total * 1000000) / 10000) + "\n");

        bw.flush();
        bw.close();
    }
}
