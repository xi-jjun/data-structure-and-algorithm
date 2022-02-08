package BackTracking;

import java.util.Scanner;

// baekJoon 15650 silver3 N과 M - 2
// https://st-lab.tistory.com/115 참고해서 품
public class S15650 {
    static int N, M;
    static boolean[] visited = new boolean[10];
    static int[] arr = new int[10];

    static void backTracking(int depth, int at) {
        if (depth == M) {
            for (int i = 0; i < M; i++) System.out.print(arr[i] + " ");
            System.out.println("");
            return;
        }

        for (int i = at; i <= N; i++) {
            if (!visited[i]) {
                arr[depth] = i;
                visited[i] = true;
                backTracking(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        backTracking(0, 1);
    }
}
