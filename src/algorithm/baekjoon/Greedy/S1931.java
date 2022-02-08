package Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class S1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // meeting numbers
        int[][] meetingTimes = new int[N][2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetingTimes[i][0] = start;
            meetingTimes[i][1] = end;
        }

        if (N == 1) {
            System.out.println(1);
            return;
        }

        // Arrays.sort(meetingTimes, (a, b) -> a[1] - b[1]); // end 빠른 순 정렬
        // Arrays.sort(meetingTimes, (a, b) -> a[0] - b[0]);
        Arrays.sort(meetingTimes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });


        int endTime = 0;
        int count = 0;

        for (int[] i : meetingTimes) {
            if (i[0] >= endTime) {
                count++;
                endTime = i[1];
            }
        }

        bw.write(count + "");

        bw.flush();
        bw.close();
    }
}
