package programmers.level1;
// programmers 월간 코드 챌린지 시즌2 > 음양 더하기
public class NegPosAdd {
    public static int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for (int i = 0; i < signs.length; i++) answer = signs[i] ? answer + absolutes[i] : answer - absolutes[i];
        return answer;
    }

    public static void main(String[] args) {
        int[] a = {4,7,12};
        boolean[] b = {true,false,true};
        System.out.println("solution(a,b) = " + solution(a, b));
    }
}
