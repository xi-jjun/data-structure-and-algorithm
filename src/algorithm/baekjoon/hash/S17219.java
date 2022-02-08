package hash;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// baekJoon 17219 silver4
public class S17219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, String> password = new HashMap<>();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            password.put(st.nextToken(), st.nextToken());
        }

        while (M-- > 0) {
            bw.write(password.get(br.readLine()) + "\n");
        }

        bw.flush();
        bw.close();
    }
}
