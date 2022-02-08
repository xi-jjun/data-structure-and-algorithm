package BackTracking;

import java.util.Scanner;

// baekJoon 15649 silver3 N과 M - 1
// https://www.youtube.com/watch?v=Enz2csssTCs 참고함
public class S15649 {
    static int N, M;
    static boolean[] visited = new boolean[10];
    static int[] arr = new int[10];

    static void backTracking(int start) {
        if (start == M) {
            for (int i = 0; i < M; i++) System.out.print(arr[i] + " ");
            System.out.println("");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                arr[start] = i;
                visited[i] = true;
                backTracking(start + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        backTracking(0);
    }
}
