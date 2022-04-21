package algorithm.baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G14891 {
    static final char N = '0';
    static final int CLOCKWISE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Wheel[] wheels = new Wheel[5];
        for (int i = 1; i < 5; i++) {
            wheels[i] = new Wheel(br.readLine().toCharArray());
        }

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            int rotateNumber = Integer.parseInt(st.nextToken());
            boolean direction = Integer.parseInt(st.nextToken()) == CLOCKWISE;

            int left = rotateNumber;
            int right = rotateNumber;
            while (right < 4 && !wheels[right].isSameFromRight(wheels[right + 1])) {
                right++;
            }
            while (left > 1 && !wheels[left].isSameFromLeft(wheels[left - 1])) {
                left--;
            }

            wheels[rotateNumber].rotate(direction);

            boolean leftDirection = !direction;
            boolean rightDirection = !direction;
            for (int i = rotateNumber - 1; i >= left; i--) {
                wheels[i].rotate(leftDirection);
                leftDirection = !leftDirection;
            }

            for (int i = rotateNumber + 1; i <= right; i++) {
                wheels[i].rotate(rightDirection);
                rightDirection = !rightDirection;
            }
        }

        int answer = 0;
        for (int i = 1; i < 5; i++) {
            answer += wheels[i].getStdDetail() == N ? 0 : (int) Math.pow(2, i - 1);
        }
        System.out.println(answer);
    }

    static class Wheel {
        private int std = 0;
        char[] details;

        public Wheel(char[] details) {
            this.details = details;
        }

        char getStdDetail() {
            return this.details[std];
        }

        char getRightPolarity() {
            return details[(std + 2) % 8];
        }

        char getLeftPolarity() {
            return details[(std + 6) % 8];
        }

        boolean isSameFromLeft(Wheel left) {
            return left.getRightPolarity() == this.getLeftPolarity();
        }

        boolean isSameFromRight(Wheel right) {
            return right.getLeftPolarity() == this.getRightPolarity();
        }

        void rotateToClockwise() {
            if (this.std == 0) {
                this.std = 7;
            } else {
                --this.std;
            }
        }

        void rotateToCounterClockwise() {
            this.std = (this.std + 1) % 8;
        }

        void rotate(boolean direction) {
            if (direction) rotateToClockwise();
            else rotateToCounterClockwise();
        }
    }
}
