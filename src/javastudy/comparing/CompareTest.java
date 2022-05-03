package javastudy.comparing;

import java.util.PriorityQueue;

public class CompareTest {
    public static void main(String[] args) {
        PriorityQueue<Foo> pq = new PriorityQueue<>();
        pq.offer(new Foo(4));
        pq.offer(new Foo(95));
        pq.offer(new Foo(49));
        pq.offer(new Foo(19));
        pq.offer(new Foo(91));
        pq.offer(new Foo(5));

        while (!pq.isEmpty()) {
            System.out.println("pq.poll().x = " + pq.poll().x);
        }
        /**
         * pq.poll().x = 4
         * pq.poll().x = 5
         * pq.poll().x = 19
         * pq.poll().x = 49
         * pq.poll().x = 91
         * pq.poll().x = 95
         */
    }

    /**
     * https://www.tutorialspoint.com/java-integer-compareto-method 참고
     */
    static class Foo implements Comparable<Foo> {
        int x;

        public Foo(int x) {
            this.x = x;
        }

        /**
         *
         * @param o : Other Object
         * @return - 0 : equals
         *         - -1 : this.value < o.value
         *         - 1 : this.value > o.value
         */
        @Override
        public int compareTo(Foo o) {
//            return this.x - o.x;
            return this.x > o.x ? 1 : -1; // x 를 기준으로 오름차순 정렬
        }
    }
}
