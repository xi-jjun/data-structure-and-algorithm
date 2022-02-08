package programmers.Test1015;

import java.util.ArrayList;
import java.util.List;

public class Sol2 {
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int expectedSize = brown + yellow;
        List<Integer> getNum = new ArrayList<>();

//        for (int i = (int) (Math.sqrt(expectedSize)) + 2; i >= 1; i--) {
//            System.out.println("i = " + i);
//            if (expectedSize % i == 0) {
//                if (i * i == expectedSize) {
//                    getNum.add(i);
//                    getNum.add(i);
//                    break;
//                } else getNum.add(i);
//            }
//            if (getNum.size() == 2) break;
//        }
        for (int i = 1; i <= Math.sqrt(expectedSize); i++) {
            if(Math.sqrt(expectedSize)/2 < i) break;
            if (expectedSize % i == 0) {
                if (i * i == expectedSize) {
                    return new int[]{i, i};
                } else getNum.add(i);
            }
        }

        answer[0] = getNum.get(getNum.size() - 2);
        answer[1] = getNum.get(getNum.size() - 1);

        return answer;
    }

    public static void main(String[] args) {
        int b = 120;
        int y = 70;
        System.out.println("solution(d, y) = " + solution(b, y)[0] + " " + solution(b, y)[1]);
    }
}
