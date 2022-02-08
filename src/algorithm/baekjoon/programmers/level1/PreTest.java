package programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// programmers 완전탐색 > 모의고사
public class PreTest {
    public static int[] solution(int[] answers) {
        int[] count = new int[4];
        int[] p1 = {1, 2, 3, 4, 5};
        int[] p2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] p3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        List<Integer> result = new ArrayList<>();
        int idx = 0;
        for (int ans : answers) {
            count[1] = ans == p1[idx % p1.length] ? count[1] + 1 : count[1];
            count[2] = ans == p2[idx % p2.length] ? count[2] + 1 : count[2];
            count[3] = ans == p3[idx++ % p3.length] ? count[3] + 1 : count[3];
        }
        int Max = Arrays.stream(count).max().getAsInt();

        for (int i = 1; i < 4; i++) if (Max == count[i]) result.add(i);
        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) answer[i] = result.get(i);

        return answer;
    }

    private static int[] getAnswer(int[] count, int num1, int num2, int num3) {
        int[] answer;
        if (count[num2] >= count[num3]) {
            answer = new int[]{num1};
        } else if (count[num3] > count[num1]) {
            answer = new int[]{num3};
        } else if (count[num1] == count[num3]) {
            answer = new int[]{num1, num3};
        } else answer = new int[]{};
        return answer;
    }

    public static void main(String[] args) {
        int[] answer = {1, 2, 3, 4, 5};
        int[] a2 = {1, 3, 2, 4, 2};
        for (int i : solution(answer)) {
            System.out.println(i);
        }
    }
}
