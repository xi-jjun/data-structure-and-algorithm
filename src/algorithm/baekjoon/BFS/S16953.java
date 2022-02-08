package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// baekJoon 16953 silver1 A->B
public class S16953 {
    static long A, B;
    static int result = 0;

    static void bfs(long start, int count) {
        Queue<Long> q = new LinkedList<>();
        if (start > B) return;

        if (start == B) {
            result = count;
            return;
        }

        q.offer(start);

        while (!q.isEmpty()) {
            long x = q.poll();
            bfs(x * 2, count + 1);
            bfs(x * 10 + 1, count + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        A = Long.parseLong(line[0]);
        B = Long.parseLong(line[1]);

        bfs(A, 1);
        result = result == 0 ? -1 : result;
        System.out.println(result);
    }
}
