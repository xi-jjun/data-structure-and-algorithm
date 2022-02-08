package Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// baekJoon 14499 Gold4 주사위 굴리기
public class G14499 {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static final int DICE_BOTTOM_SIDE_X = 1;
	static final int DICE_BOTTOM_SIDE_Y = 1;
	static final int DICE_UPPER_SIDE_X = 3;
	static final int DICE_UPPER_SIDE_Y = 1;

	static int[][] dice = new int[4][3];
	static int[][] map;
	static int[] dx = {99, 0, 0, -1, 1};
	static int[] dy = {99, 1, -1, 0, 0};

	static int N, M, K, x, y;

	static int currMapX, currMapY;

	public static void main(String[] args) throws IOException {
		init();

		getMap();

		String[] commands = br.readLine().split(" ");
		for (String cmd : commands) {
			int command = Integer.parseInt(cmd);

			if (move(command)) {
				if (map[currMapX][currMapY] == 0) {
					map[currMapX][currMapY] = dice[DICE_BOTTOM_SIDE_X][DICE_BOTTOM_SIDE_Y];
				} else {
					dice[DICE_BOTTOM_SIDE_X][DICE_BOTTOM_SIDE_Y] = map[currMapX][currMapY];
					map[currMapX][currMapY] = 0;
				}

				bw.write(dice[DICE_UPPER_SIDE_X][DICE_UPPER_SIDE_Y] + "\n");
			}
		}


		bw.flush();
		bw.close();
	}

	private static boolean move(int direction) {
		if (currMapX + dx[direction] < 0 ||
				currMapX + dx[direction] >= N ||
				currMapY + dy[direction] < 0 ||
				currMapY + dy[direction] >= M) {
			return false;
		}
		moveDice(direction);
		currMapX = currMapX + dx[direction];
		currMapY = currMapY + dy[direction];
		return true;
	}

	private static void moveDice(int direction) {
		switch (direction) {
			case 1:
				diceToEast();
				break;
			case 2:
				diceToWest();
				break;
			case 3:
				diceToNorth();
				break;
			case 4:
				diceToSouth();
				break;
		}
	}

	private static void diceToSouth() {
		int temp = dice[1][1];
		dice[1][1] = dice[2][1];
		dice[2][1] = dice[3][1];
		dice[3][1] = dice[0][1];
		dice[0][1] = temp;
	}

	private static void diceToNorth() {
		int temp = dice[1][1];
		dice[1][1] = dice[0][1];
		dice[0][1] = dice[3][1];
		dice[3][1] = dice[2][1];
		dice[2][1] = temp;
	}

	private static void diceToWest() {
		int temp = dice[1][1];
		dice[1][1] = dice[1][0];
		dice[1][0] = dice[3][1];
		dice[3][1] = dice[1][2];
		dice[1][2] = temp;
	}

	private static void diceToEast() {
		int temp = dice[1][1];
		dice[1][1] = dice[1][2];
		dice[1][2] = dice[3][1];
		dice[3][1] = dice[1][0];
		dice[1][0] = temp;
	}

	private static void init() throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		x = Integer.parseInt(line[2]);
		y = Integer.parseInt(line[3]);
		K = Integer.parseInt(line[4]);

		map = new int[N][M];
		currMapX = x;
		currMapY = y;
	}

	private static void getMap() throws IOException {
		for (int i = 0; i < N; i++) {
			String[] m = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(m[j]);
			}
		}
	}

}
