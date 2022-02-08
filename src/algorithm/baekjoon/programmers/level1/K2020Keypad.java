package programmers.level1;

// programmers 2020 카카오 인턴십 > 키패드 누르기
public class K2020Keypad {
    static int getDistance(Hand hand, int number) {
        int loc = number == 0 ? 3 : number / 3;
        Hand pushLocation = new Hand(loc, 1);
        return Math.abs(hand.x - pushLocation.x) + Math.abs(hand.y - pushLocation.y);
    }

    public static String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        boolean mode = hand.equals("right"); // true:right hand, false:left hand
        Hand leftLocation = new Hand(3, 0);
        Hand rightLocation = new Hand(3, 2);

        for (int number : numbers) {
            switch (number) {
                case 1: // Left-hand use
                case 4:
                case 7:
                    answer.append("L");
                    leftLocation.x = number / 3;
                    leftLocation.y = 0;
                    break;
                case 3: // Right-hand use
                case 6:
                case 9:
                    answer.append("R");
                    rightLocation.x = number / 3 - 1;
                    rightLocation.y = 2;
                    break;
                default:
                    int l2num = getDistance(leftLocation, number);
                    int r2num = getDistance(rightLocation, number);
                    if (l2num < r2num) {
                        answer.append("L");
                        leftLocation.x = number == 0 ? 3 : number / 3;
                        leftLocation.y = 1;
                    } else if (l2num > r2num) {
                        answer.append("R");
                        rightLocation.x = number == 0 ? 3 : number / 3;
                        rightLocation.y = 1;
                    } else if (mode) {
                        answer.append("R");
                        rightLocation.x = number == 0 ? 3 : number / 3;
                        rightLocation.y = 1;
                    } else {
                        answer.append("L");
                        leftLocation.x = number == 0 ? 3 : number / 3;
                        leftLocation.y = 1;
                    }
                    break;
            }
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        int[] n = new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        System.out.println("solution(n, hand) = " + solution(n, hand));
    }

    static class Hand {
        public int x, y;

        public Hand(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
