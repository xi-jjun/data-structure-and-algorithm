package Implementation;

// baekJoon 7568 silver5

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S7568 {
    static boolean compare(int a, int b) {
        return a < b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<ArrayList<Integer>> weight = new ArrayList<ArrayList<Integer>>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) weight.add(new ArrayList<Integer>());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            weight.get(i).add(first);
            weight.get(i).add(second);
        }

        for (int i = 0; i < N; i++) {

            int j = -1;
            int sum = 1;

            while (j++ < N-1) {
                if (compare(weight.get(i).get(0), weight.get(j).get(0)) && compare(weight.get(i).get(1), weight.get(j).get(1)))
                    sum++;
            }

            weight.get(i).add(sum);
        }

        for (int i = 0; i < N; i++) {
            bw.write(weight.get(i).get(2) + " ");
        }

        bw.flush();
        bw.close();
    }
}
