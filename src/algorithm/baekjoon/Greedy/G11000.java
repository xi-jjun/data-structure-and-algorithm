package Greedy;

import java.io.*;
import java.util.*;

// baekJoon 11000 Gold5 강의실 배정
// 해결 못 함..
public class G11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<List<Integer>> times = new ArrayList<>();
        List<List<Integer>> classRoom = new ArrayList<>();

        int result = 0;
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            times.add(new ArrayList<>());
            times.get(i).add(Integer.parseInt(st.nextToken()));
            times.get(i).add(Integer.parseInt(st.nextToken()));
        }

        times.sort((o1, o2) -> o1.get(0) - o2.get(1));

        int nowEnd = times.get(0).get(1);
        int nextStart;
        int index = 0;

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < 2; k++) bw.write(times.get(i).get(k) + " ");
            bw.write("\n");
        }

        for (int i = 1; i < N; i++) {
            nextStart = times.get(i).get(0);

            if (nowEnd <= nextStart) {
                nowEnd = times.get(i).get(1);
            } else {
                result++;
//                classRoom.add(new ArrayList<>());
//                classRoom.get(index++).
            }
        }

        bw.write((result + 1) + "");

        bw.flush();
        bw.close();
    }
}
