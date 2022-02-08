package Sort;

import java.io.*;
import java.util.*;

// baekJoon 11650 silver5 좌표 정렬하기 - class2+
public class S11650 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Point> coords = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coords.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        coords.sort((o1, o2) -> {
            if (o1.x == o2.x) return o1.y - o2.y;
            else return o1.x - o2.x;
        });

        for (Point coord : coords) {
            bw.write(coord.x + " " + coord.y + "\n");
        }

        bw.flush();
        bw.close();
    }

    static class Point {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
