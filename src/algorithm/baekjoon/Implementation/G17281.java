package algorithm.baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

// baekJoon 17281 Gold4 ⚾️
public class G17281 {
    /**
     * 선수번호 : 선수들의 고유 번호. 변경x
     */
    static final int OUT = 0;
    static final int SINGLE = 1;
    static final int DOUBLE = 2;
    static final int TRIPLE = 3;
    static final int HOME_RUN = 4;
    static int N, answer, score;
    static int[] gamePlayers = new int[10]; // gamePlayers[타자순서] = 타자순서에 해당하는 선수번호
    static int[][] scores; // scores[이닝][선수번호] = 해당 이닝에 선수번호가 낼 수 있는 결과
    static boolean[] isPlaced = new boolean[10]; // isPlaced[선수번호]

    public static void main(String[] args) throws IOException {
        getInput();

        choosePlayerEntries(1);
        System.out.println(answer);
    }

    private static void choosePlayerEntries(int playerOrder) {
        if (playerOrder == 10) {
            gameStart();
            answer = Math.max(answer, score);
            return;
        }

        for (int player = 1; player <= 9; player++) {
            if (!isPlaced[player]) {
                isPlaced[player] = true;
                if (playerOrder == 4) {
                    ++playerOrder;
                }
                gamePlayers[playerOrder] = player;
                choosePlayerEntries(playerOrder + 1);
                isPlaced[player] = false;
            }
        }
    }

    private static void gameStart() {
        score = 0;
        int hitter = 1; // 1번 타자부터 시작
        for (int inning = 1; inning <= N; inning++) {
            hitter = startInning(inning, hitter);
        }
    }

    private static int startInning(int inning, int nowHitter) {
        int out = 0;
        int[] runners = new int[4]; // 1,2,3 루에 있는 타자 순서 runners[base] = hitter

        int hitter = nowHitter; // hitter : 타자순서
        while (out < 3) {
            int player = gamePlayers[hitter]; // player : 실제 선수번호
            int result = scores[inning][player]; // scores[이닝][선수번호] = 해당 선수가 낼 수 있는 결과

            if (result == OUT) {
                ++out;
            } else if (result == HOME_RUN) {
                addRunnersScores(runners, HOME_RUN);
                Arrays.fill(runners, 0); // 나가있는 주자 존재x
            } else {
                addRunnersScores(runners, result);
                moveRunners(runners, hitter, result);
            }

            /**
             * 타자가 base 에 머무르게 되면, 해당 타자는 hitter 불가능
             */
            hitter = getNextHitter(runners, hitter);
        }

        return hitter;
    }

    private static int getNextHitter(int[] runners, int nowHitter) {
        int nextHitter = nowHitter >= 9 ? 1 : nowHitter + 1; // 다음 타자번호
        while (isRunner(runners, nextHitter)) {
            nextHitter = nextHitter >= 9 ? 1 : nextHitter + 1;
        }
        return nextHitter;
    }

    /**
     * 해당 hitter 가 이미 runners 라면, false 반환.
     *
     * @param runners 현재 존재하는 주자들 runners[base] = hitter 번호
     * @param hitter  다음 주자가 될 타자 번호
     * @return 다음 주자(hitter 번호) 가 이미 runners 라면, false 반환.
     */
    private static boolean isRunner(int[] runners, int hitter) {
        for (int base = 1; base <= 3; base++) {
            if (runners[base] == hitter) {
                // 다른 hitter 찾아야 함
                return true;
            }
        }

        return false;
    }

    private static void moveRunners(int[] runners, int hitter, int hitType) {
        for (int base = 3; base > hitType; base -= hitType) {
            runners[base] = runners[base - hitType];
            runners[base - hitType] = 0;
        }
        runners[hitType] = hitter; // hitType 에 hitter 번째의 타자가 있다.
        cleanRunnersWhenTriple(runners, hitType);
    }

    private static void cleanRunnersWhenTriple(int[] runners, int hitType) {
        if (hitType == TRIPLE) {
            for (int base = 1; base <= 2; base++) {
                runners[base] = 0;
            }
        }
    }

    private static void addRunnersScores(int[] runners, int hitType) {
        if (hitType == HOME_RUN) ++score;
        for (int base = 3; hitType-- > 0; base--) {
            if (base <= 0) return;
            if (runners[base] != 0) ++score; // 0번이 아니면 hitter 번호가 존재하는 것 == 주자 존재
        }
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        scores = new int[N + 1][10];
        for (int inning = 1; inning <= N; inning++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int player = 1; player <= 9; player++) {
                scores[inning][player] = Integer.parseInt(st.nextToken());
            }
        }

        isPlaced[1] = true; // 1번 선수는 4번타자로 고정
        gamePlayers[4] = 1; // 4번 타자는 1번 선수로 고정
    }
}
