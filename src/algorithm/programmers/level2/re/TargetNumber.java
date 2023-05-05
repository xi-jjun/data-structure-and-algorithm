package algorithm.programmers.level2.re;

import java.util.Objects;
import java.util.Queue;
import java.util.ArrayDeque;

public class TargetNumber {

    public static int solution(int[] numbers, int target) {
        int answer = 0;
        Queue<Element> q = new ArrayDeque<>();
        q.offer(new Element(-1, 0));

        while (!q.isEmpty()) {
            Element e = q.poll();
            int index = e.index;
            int acc = e.acc;

            if (index + 1 == numbers.length && acc == target) {
                answer++;
            } else if (index + 1 < numbers.length) {
                q.offer(new Element(index + 1, acc + numbers[index + 1]));
                q.offer(new Element(index + 1, acc - numbers[index + 1]));
            }
        }
        return answer;
    }

    public static void main(String[] args) {
//        int[] numbers = new int[]{4, 1, 2, 1}; // target : 2 ==> answer : 2
        int[] numbers = new int[]{1, 1, 1, 1, 1}; // target : 3 ==> answer : 5
        int targetNumber = 3;

        int answer = solution(numbers, targetNumber);
        System.out.println("answer = " + answer);
    }

    static class Element {

        int index; // 다음에 더해야 하는 index
        int acc; // 현재까지 합

        Element(int index, int acc) {
            this.index = index;
            this.acc = acc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (Objects.isNull(o) || this.getClass() != o.getClass()) {
                return false;
            }

            Element other = (Element) o;
            return this.index == other.index && this.acc == other.acc;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, acc);
        }

        @Override
        public String toString() {
            return "index : " + index + ", acc : " + acc;
        }
    }
}
