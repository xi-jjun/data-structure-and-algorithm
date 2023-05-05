package algorithm.programmers.level3;

import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class WordTransForm {

    static final int VALID_POSSIBLE_PROCESS_CNT = 1;

    static int countDiff(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) count++;
        }

        return count;
    }

    public int solution(String begin, String target, String[] words) {
        PriorityQueue<Process> pq = new PriorityQueue<>();
        Set<String> dup = new HashSet<>();
        pq.offer(new Process(0, begin));

        while (!pq.isEmpty()) {
            Process process = pq.poll();
            int nowStep = process.step;
            String nowWord = process.word;

            if (nowWord.equals(target)) return nowStep;

            for (String word : words) {
                Process nextProcess = new Process(nowStep + 1, word);
                if (countDiff(word, nowWord) == VALID_POSSIBLE_PROCESS_CNT && !dup.contains(word)) {
                    dup.add(word);
                    pq.offer(nextProcess);
                }
            }
        }

        return 0;
    }

    static class Process implements Comparable<Process> {
        int step;
        String word;

        public Process(int step, String word) {
            this.step = step;
            this.word = word;
        }

        @Override
        public int hashCode() {
            return Objects.hash(step, word);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (Objects.isNull(o) || o.getClass() != this.getClass()) return false;

            Process other = (Process) o;
            return this.step == other.step && this.word.equals(other.word);
        }

        @Override
        public int compareTo(Process other) {
            return this.step - other.step;
        }
    }
}
