package String;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

// baekJoon 1764 silver4
public class S1764 {
    public static void main(String[] args) throws IOException {
        HashSet<String> A = new HashSet<>();
        HashSet<String> B = new HashSet<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] num = br.readLine().split(" ");
        int N = Integer.parseInt(num[0]);
        int M = Integer.parseInt(num[1]);

        for (int i = 0; i < N; i++) A.add(br.readLine());

        for (int i = 0; i < M; i++) B.add(br.readLine());

        A.retainAll(B);

        ArrayList<String> result = new ArrayList<>(A);
        Collections.sort(result);

        bw.write(A.size() + "\n");

        for (String s : result) bw.write(s + "\n");

        bw.flush();
        bw.close();
    }
}
