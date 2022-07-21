package algorithm.baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class G16637 {
    static int N;
    static long answer = Long.MIN_VALUE;
    static char[] EQUATION;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        EQUATION = br.readLine().toCharArray();

        // () 연산에 관계없이 무조건 최대값이 1개로 고정된 경우 처리
        if (N == 1) {
            System.out.println(Integer.parseInt(String.valueOf(EQUATION[0])));
            return;
        } else if (N == 3) {
            System.out.println(calculate(EQUATION));
            return;
        }

        bfs();

        System.out.println(answer);
    }

    private static int calculate(char[] equation) {
        int num1 = Integer.parseInt(String.valueOf(equation[0]));
        int num2 = Integer.parseInt(String.valueOf(equation[2]));
        char operator = equation[1];

        return operation(num1, num2, operator);
    }

    private static int operation(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }

        return -9999;
    }

    private static void bfs() {
        int start = 2;
        PriorityQueue<Result> q = new PriorityQueue<>();
        int startResult = Integer.parseInt(String.valueOf(EQUATION[0]));
        StringBuilder initEq = new StringBuilder();
        initEq.append(startResult).append(EQUATION[1]);
        q.offer(new Result(startResult, start, initEq.toString()));

        while (!q.isEmpty()) {
            Result now = q.poll();
            int nowOperatingIndex = now.lastOperatedIndex;

            int nowResult = calculateResult(now);
            if (nowOperatingIndex < N - 1) {
                StringBuilder temp = new StringBuilder();
                temp.append(nowResult).append(EQUATION[nowOperatingIndex + 1]);
                q.offer(new Result(nowResult,
                        nowOperatingIndex + 2,
                        temp.toString()));
            } else if (nowOperatingIndex == N - 1){
                answer = Math.max(answer, nowResult);
            }

            if (nowOperatingIndex == N - 1) continue;

            int nowBracketResult = calculateResultWithBracket(now);
            if (nowOperatingIndex < N - 3) {
                StringBuilder temp = new StringBuilder();
                temp.append(nowBracketResult).append(EQUATION[nowOperatingIndex + 3]);
                q.offer(new Result(nowBracketResult,
                        nowOperatingIndex + 4,
                        temp.toString()));
            } else if (nowOperatingIndex == N - 3){
                answer = Math.max(answer, nowBracketResult);
            }
        }
    }

    private static int calculateResultWithBracket(Result result) {
        int lastOperatedIndex = result.lastOperatedIndex; // 얘부터 다음 숫자까지 미리 계산 후 exResult 랑 합쳐야 함
        char[] bracketEq = getBracketedEquation(lastOperatedIndex);

        int exResult = result.result;
        int bracketedEqResult = calculate(bracketEq);
        int ret = operation(exResult, bracketedEqResult, EQUATION[lastOperatedIndex - 1]);

        return ret;
    }

    private static char[] getBracketedEquation(int lastOperatedIndex) {
        char[] bracketEq = new char[3];
        bracketEq[0] = EQUATION[lastOperatedIndex];
        bracketEq[1] = EQUATION[lastOperatedIndex + 1];
        bracketEq[2] = EQUATION[lastOperatedIndex + 2];
        return bracketEq;
    }

    private static int calculateResult(Result result) {
        String exEquation = result.exEquation;

        int nowOperatedIndex = result.lastOperatedIndex;

        int first = Integer.parseInt(exEquation.substring(0, exEquation.length() - 1));
        int second = Integer.parseInt(String.valueOf(EQUATION[nowOperatedIndex]));
        char op = exEquation.charAt(exEquation.length() - 1);

        return operation(first, second, op);
    }

    static class Result implements Comparable<Result> {
        int result;
        int lastOperatedIndex;
        String exEquation;

        public Result(int result, int lastOperatedIndex, String exEquation) {
            this.result = result;
            this.lastOperatedIndex = lastOperatedIndex;
            this.exEquation = exEquation;
        }

        @Override
        public int compareTo(Result o) {
            return o.result - this.result;
        }
    }
}
