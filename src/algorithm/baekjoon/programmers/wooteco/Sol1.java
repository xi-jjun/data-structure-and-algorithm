package programmers.wooteco;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Sol1 {
    public static int[] solution(int[] arr) {
        int[] answer = new int[3];

        int maxCount = -1;
        int[] count = new int[4];
        for (int data : arr) {
            count[data]++;
            maxCount = Math.max(maxCount, count[data]);
        }

        for (int i : count) {
            System.out.println("i = " + i);
        }
        for (int i = 0; i < 3; i++) {
            answer[i] = maxCount - count[i+1];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] a = {3, 3, 3, 3, 3, 3};
        int[] b = {1,1,1,1,1,1,1,1,1,1,2,3,2,3,1,2,3,2,3,1,3,2,3,3,3,2,3,1,3,2,3,1,3,2,3,1,3,3,3,3,3,2,1,3,2,3,1,3,2,3,1,3,2,3,1,3,3,3,2,1,3,1,3,3,1,1,1,1};
        int[] c = {1};
        for (int i : solution(c)) {
            System.out.println(i);
        }
    }
}
