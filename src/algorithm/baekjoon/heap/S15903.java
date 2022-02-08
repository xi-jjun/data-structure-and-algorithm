package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.PriorityQueue;

// baekJoon 15903 silver2 카드합체놀이-스터디(Heap)
// 한줄평 : 정신나감
public class S15903 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int m = Integer.parseInt(line[1]);

        PriorityQueue<BigInteger> cards = new PriorityQueue<>();
        String[] data = br.readLine().split(" ");

        for (String d : data) cards.offer(new BigInteger(Integer.parseInt(d) + ""));

        while (m-- > 0) {
            BigInteger a = new BigInteger(cards.poll() + "");
            BigInteger b = new BigInteger(cards.poll() + "");
            cards.offer(a.add(b));
            cards.offer(a.add(b));
        }

        BigInteger result = new BigInteger("0"); // MAX 10^10
        while (!cards.isEmpty()) result = result.add(new BigInteger(cards.poll() + ""));
        System.out.println(result);
    }
}
