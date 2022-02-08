package Greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

// baekJoon 9576 Gold1 책 나눠주기
public class G9576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st1.nextToken());
            int M = Integer.parseInt(st1.nextToken());
            boolean[] visited = new boolean[N + 1];

            ArrayList<ArrayList<Integer>> application = new ArrayList<>();
            for (int k = 0; k < M; k++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st2.nextToken());
                int b = Integer.parseInt(st2.nextToken());

                application.add(new ArrayList<>());
                application.get(k).add(a);
                application.get(k).add(b);
            }

            application.sort(Comparator.comparingInt(o -> o.get(1)));

            int result = 0;
            int index = 0;

            while (index <= M - 1) {
                for (int k = 1; k <= N; k++) {
                    if (application.get(index).get(0) <= k && k <= application.get(index).get(1) && !visited[k]) {
                        result++;
                        visited[k] = true;
                        break;
                    }
                }
                index++;
            }

            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }
}
