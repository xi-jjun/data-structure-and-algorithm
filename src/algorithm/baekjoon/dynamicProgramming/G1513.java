package dynamicProgramming;

import java.io.*;
import java.util.StringTokenizer;
// 일단 푸는 중
public class G1513 {
    static final int[] dx = {0, 1};
    static final int[] dy = {1, 0};
    static int N, M, C;
    static int[][] map;
    static int[] count;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        count = new int[C + 1];

        getFunLocation(br);

        bw.flush();
        bw.close();
    }

    private static void getFunLocation(BufferedReader br) throws IOException {
        for (int i = 1; i <= C; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = i;
        }
    }
}
