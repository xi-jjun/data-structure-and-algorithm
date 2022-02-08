package hash;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

// baekJoon 1620 silver4 나는야 포켓몬 마스터 이다솜
public class S1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> doGams = new HashMap<>();
        DoGam[] poketmons = new DoGam[N + 1];

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            doGams.put(name, i);
            poketmons[i] = new DoGam(name, i);
        }
        while (M-- > 0) {
            String question = br.readLine();
            if (question.charAt(0) >= 48 && question.charAt(0) <= 57) {
                int q = Integer.parseInt(question);
                bw.write(poketmons[q].name + "\n");
            } else {
                bw.write(doGams.get(question) + "\n");
            }
        }

        bw.flush();
        bw.close();

    }

    static class DoGam {
        private final String name;
        private final int index;

        public DoGam(String name, int index) {
            this.name = name;
            this.index = index;
        }
    }
}
