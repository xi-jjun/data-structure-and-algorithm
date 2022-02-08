package Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// baekJoon 2164 silver4 카드2
public class S2164 {
    public static void main(String[] args) {
        Queue<Integer> cards = new LinkedList<>();
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int i = 1; i <= N; i++) cards.offer(i);
        int size = cards.size();

        while (size-- != 1) {
            cards.poll();
            cards.offer(cards.poll());
        }

        System.out.println(cards.poll());
    }
}
