package programmers.level1;

import java.util.*;

// programmers 2019 KAKAO BLIND RECRUITMENT > 실패율
public class Fail {
    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int totalFail = 0;
        int userNumber = stages.length;
        List<FailRate> failRates = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            int failNumber = 0;
            for (int stage : stages) {
                if (stage == i) failNumber++;
            }
            if (userNumber - totalFail == 0) failRates.add(new FailRate(0.0, i));
            else failRates.add(new FailRate(((double) failNumber / (userNumber - totalFail)), i));
            totalFail += failNumber;
        }

        failRates.sort(((o1, o2) -> {
            if (Double.compare(o2.failRate, o1.failRate) == 0) return o1.index - o2.index;
            return Double.compare(o2.failRate, o1.failRate);
        }));

        int idx = 0;
        for (FailRate failRate : failRates) {
            answer[idx++] = failRate.index;
            System.out.println("failRate.failRate = " + failRate.failRate);
        }

        return answer;
    }

    public static void main(String[] args) {
//        int N = 5;
//        int[] s = {2, 1, 2, 6, 2, 4, 3, 3};
        int N = 5;
        int[] s = {4, 4, 4, 4, 4};
//        int N = 1;
//        int[] s = {1,2 ,2,1};
        for (int i : solution(N, s)) {
            System.out.println("i = " + i);
        }
    }

    static class FailRate {
        private final double failRate;
        private final int index;

        public FailRate(double failRate, int index) {
            this.failRate = failRate;
            this.index = index;
        }
    }
}
