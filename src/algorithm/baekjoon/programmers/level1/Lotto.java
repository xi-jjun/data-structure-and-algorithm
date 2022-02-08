package programmers.level1;

import java.util.HashSet;
import java.util.Set;

// 로또의 최고 순위와 최저 순위
public class Lotto {
    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int count0 = 0;
        Set<Integer> lotto = new HashSet<>();
        Set<Integer> win = new HashSet<>();
        for (int i : lottos) {
            if (i != 0) lotto.add(i);
            else count0++;
        }
        for (int win_num : win_nums) win.add(win_num);

        lotto.removeAll(win);

        answer[0] = lotto.size() <= 4 ? lotto.size() + 1 : 6;
        answer[1] = Math.min(answer[0] + count0, 6);
        return answer;
    }

    public static void main(String[] args) {
//        int[] l = {0, 0, 0, 0, 0, 0};
        int[] l = {45, 4, 35, 20, 3, 9};
        int[] w = {20, 9, 3, 45, 4, 35};
//        int[] w = {38, 19, 20, 40, 15, 25};
        System.out.println("solution(l, w) = " + solution(l, w)[0] + " " + solution(l, w)[1]);
    }
}
