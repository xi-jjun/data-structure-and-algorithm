package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 16236 Gold4 아기 상어
// STOP
public class G16236 {
    static final int EMPTY = 0; // 1~6 fish size
    static final int BABY_SHARK = 9; // location
    static int[][] map;
    static int sharkX, sharkY, sharkSize = 2;

//    static boolean checkClear() {
//        for (int[] a : map) {
//            for (int b : a) {
//                if(b!=0 ||)
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int k = 0; k < N; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());
                if (map[i][k] == BABY_SHARK) {
                    sharkX = i;
                    sharkY = k;
                }
            }
        }

//        while (true) {
//            if ()
//        }
    }
}
