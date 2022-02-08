package Sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// baekJoon 1181 CLASS2 위한 silver5 단어정렬
public class S1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<String> words = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (!words.contains(word)) words.add(word);
        }

        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() - o2.length() == 0) return o1.compareTo(o2);
                else return o1.length() - o2.length();
            }
        });

        for (String w : words) bw.write(w + "\n");
        bw.flush();
        bw.close();
    }
}
