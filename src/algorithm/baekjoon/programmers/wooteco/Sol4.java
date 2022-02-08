package programmers.wooteco;

import java.util.*;

public class Sol4 {
    public static int[] solution(String s) {
        int[] answer;
        int stopCount =0;
        Deque<Character> seq = new ArrayDeque<>();
        for (String ss : s.split("")) {
            seq.offer(ss.charAt(0));
        }

        while (seq.peekFirst() == seq.peekLast()) {
            seq.offer(seq.pollFirst());
            if(stopCount++ > s.length()) break;
        }

        int count = 0;
        char ex = seq.getFirst();
        List<Integer> cnt = new ArrayList<>();
        for (Character c : seq) {
            if (ex != c) {
                cnt.add(count);
                count = 1;
            } else {
                count++;
            }
            ex = c;
        }
        cnt.add(count);

        cnt.sort((Comparator.comparingInt(o -> o)));
        answer = new int[cnt.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = cnt.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        String s = "aeabceea";
        String s2 = "wowwow";
        for (int i : solution(s)) {
            System.out.println("i = " + i);
        }

//        System.out.println("dsdasdasdasdsad");
//        for (int i : solution(s2)) {
//            System.out.println("i = " + i);
//        }
    }
}
