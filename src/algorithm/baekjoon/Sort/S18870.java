package Sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// baekJoon 18870 silver2 좌표 압축
public class S18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Coord> re = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            re.add(new Coord(i, Integer.parseInt(st.nextToken())));
        }

        re.sort(Comparator.comparingInt(o -> o.value));
        int[] d = new int[N];
        int ex = -1;
        for (int i = 0; i < N; i++) {
            Coord temp = re.get(i);
            if (i == 0) {
                ex = temp.value;
                continue;
            }
            if (temp.value > ex) d[i] = d[i - 1] + 1;
            else d[i] = d[i - 1];
            temp.transferred = d[i];
            ex = temp.value;
        }

        re.sort(Comparator.comparingInt(o -> o.sq));

        for (Coord coord : re) {
            bw.write(coord.transferred + " ");
        }

        bw.flush();
        bw.close();
    }

    static class Coord {
        private final int sq;
        private final int value;
        public int transferred;

        public Coord(int sq, int value) {
            this.sq = sq;
            this.value = value;
        }
    }
}
