package javastudy.collectionframework.queueInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int randomNumber = (int) (Math.random() * 50);
            pq.offer(randomNumber);
            list.add(randomNumber);
        }

        Collections.sort(list);

        System.out.println("=== pq ===");
        for (Integer integer : pq) {
            System.out.println("integer = " + integer);
        }

        System.out.println("=== list ===");
        for (Integer integer : list) {
            System.out.println("integer = " + integer);
        }
        /**
         * PriorityQueue 는 root 의 데이터가 최소 or 최대 임을 보장해주는 자료구조이지,
         * 순회했을 때 순서대로 데이터를 갖고있는게 아니다.
         * === pq ===
         * integer = 8
         * integer = 15
         * integer = 19
         * integer = 16
         * integer = 46
         * integer = 47
         * integer = 30
         * integer = 40
         * integer = 36
         * integer = 46
         *
         * === list ===
         * integer = 8
         * integer = 15
         * integer = 16
         * integer = 19
         * integer = 30
         * integer = 36
         * integer = 40
         * integer = 46
         * integer = 46
         * integer = 47
         */
    }
}
