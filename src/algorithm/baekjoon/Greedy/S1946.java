package Greedy;

// baekJoon 1946 silver1
// https://jaimemin.tistory.com/742 도움 받음

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class S1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<ArrayList<Integer>> score = new ArrayList<ArrayList<Integer>>();

            for (int j = 0; j < N; j++) score.add(new ArrayList<Integer>());

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                score.get(j).add(first);
                score.get(j).add(second);
            }

            Collections.sort(score, new Comparator<ArrayList<Integer>>() {
                @Override
                public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                    return o1.get(0) - o2.get(0);
                }
            });

            int result = 1;
            int secondRank = score.get(0).get(1);

            for (int j = 1; j < N; j++) {
                int nextRank = score.get(j).get(1);

                if (nextRank < secondRank) {
                    result++;
                    secondRank = nextRank;
                }
            }

            bw.write(result + "\n");

        }
        bw.flush();
        bw.close();
    }
}
