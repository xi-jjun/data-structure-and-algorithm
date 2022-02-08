package Sort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// baekJoon 11651 silver5 좌표 정렬하기2 - class2++
public class S11651 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        List<Point> list = new ArrayList<>();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        list.sort((o1, o2) -> o1.y == o2.y ? o1.x - o2.x : o1.y - o2.y);
        for (Point point : list) {
            bw.write(point.x + " " + point.y + "\n");
        }
        bw.flush();
        bw.close();
    }

    static class Point {
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
