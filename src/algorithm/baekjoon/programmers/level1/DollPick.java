package programmers.level1;

import java.util.Stack;

// programmers 2019 카카오 개발자 겨울 인턴십 > 크레인 인형뽑기 게임
public class DollPick {
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> basket = new Stack<>();
        int height = board.length;

        for (int move : moves) {
            int h = 0;

            while (h < height) {
                int now = board[h++][move - 1];
                board[h - 1][move - 1] = 0;

                if (now != 0) {
                    System.out.println("now = " + now + " move = " + move);
                    if (basket.isEmpty()) basket.push(now);
                    else {
                        if (basket.peek() == now) {
                            basket.pop();
                            answer += 2;
                        } else basket.push(now);
                    }
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] b = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        System.out.println("solution(b, moves) = " + solution(b, moves));
    }
}
