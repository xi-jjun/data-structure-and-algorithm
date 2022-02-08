package programmers.Test0923;

// test.Test PASS
public class Solution2 {
    static int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int number = i;
            int nn = n;

            while (nn > 0) {
                nn -= number++;
            }
            if (nn == 0) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(15));
    }
}
