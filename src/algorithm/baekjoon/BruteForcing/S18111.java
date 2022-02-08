package BruteForcing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// baekJoon 18111 silver3 마인크래프트-class2++
// https://codecollector.tistory.com/678 참고
public class S18111 {
    static int[][] gnd;
    static int N, M, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        List<Case> time = new ArrayList<>();
        gnd = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                gnd[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 257; i++) {
            int thisStore = B;
            int thisTime = 0;

            for (int[] ints : gnd) {
                for (int nowBlock : ints) {
                    if (i > nowBlock) {
                        int haveToFillBlocks = i - nowBlock; // 채워야할 블럭 수
                        thisTime += haveToFillBlocks; // 시간은 블럭 당 1초씩 걸림.
                        thisStore -= haveToFillBlocks; // 갖고 있던 블럭에서 빼야함
                    } else {
                        int haveToTakeOutBlocks = nowBlock - i; // 파내야할 블럭 수
                        thisTime += 2 * haveToTakeOutBlocks;
                        thisStore += haveToTakeOutBlocks;
                    }
                }
            }

            if (thisStore >= 0) time.add(new Case(thisTime, i));
        }

        time.sort((o1, o2) -> o1.time == o2.time ? o2.height - o1.height : o1.time - o2.time);

        System.out.println(time.get(0).time + " " + time.get(0).height);
    }

    static class Case {
        private final int time, height;

        public Case(int time, int height) {
            this.time = time;
            this.height = height;
        }
    }
}
